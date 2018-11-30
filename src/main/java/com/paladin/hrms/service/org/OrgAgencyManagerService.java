package com.paladin.hrms.service.org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.paladin.hrms.controller.org.pojo.OrgAgencyManagerQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.org.OrgAgencyManagerMapper;
import com.paladin.hrms.model.org.OrgAgencyManager;
import com.paladin.hrms.model.org.OrgAgencyManagerVo;
import com.paladin.hrms.model.syst.SysUser;
import com.paladin.hrms.service.syst.SysUserService;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.uuid.UUIDUtil;

@Service
public class OrgAgencyManagerService extends ServiceSupport<OrgAgencyManager> {

    @Autowired
    OrgAgencyManagerMapper orgAgencyManagerMapper;

    @Autowired
    SysUserService sysUserService;
    

    public PageResult<OrgAgencyManagerVo> fildAll(OrgAgencyManagerQuery query) {
        Page<OrgAgencyManagerVo> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        orgAgencyManagerMapper.findAll(query, permission);
        return new PageResult<>(page);
    }

    @Transactional
    public int addSave(OrgAgencyManager manager) {
        int effect = 0;
        if (StringUtil.isEmpty(manager.getId())) {
            String id = UUIDUtil.createUUID();
            manager.setId(id);
            save(manager);
            effect = sysUserService.createUserAccount(manager.getAccount(), id, SysUser.STATE_WAITING_ENABLED, SysUser.TYPE_AGENCY_MANAGER);
        } else {
            OrgAgencyManager agencyManager = get(manager.getId());
            if (agencyManager == null) {
                throw new BusinessException("修改的机构管理员用户不存在");
            }
            SysUser sysUser = sysUserService.getUser(agencyManager.getAccount());
            if(sysUser.getState() != SysUser.STATE_ENABLED){
                sysUser.setId(sysUser.getId());
                sysUser.setState(SysUser.STATE_ENABLED);
                sysUserService.updateSelective(sysUser);
            }
            
            effect = updateSelective(manager);
        }
        return effect;
    }

}