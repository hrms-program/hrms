package com.paladin.hrms.service.registe;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.HrmsUserSession;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.registe.RegisteAgencyInforMapper;
import com.paladin.hrms.model.registe.RegisteAgencyInfor;
import com.paladin.hrms.service.registe.dto.RegisteAgencyInforQueryDTO;
import com.paladin.hrms.service.registe.vo.RegisteAgencyInforVO;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;

@Service
public class RegisteAgencyInforService extends ServiceSupport<RegisteAgencyInfor> {
    
    @Autowired
    private RegisteAgencyInforMapper registeAgencyInforMapper;
    
    /**
     * 备案机构个人查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param query
     * @return
     * @see [类、类#方法、类#成员]
     */
    public PageResult<RegisteAgencyInforVO> findRegisteAgencyPage(RegisteAgencyInforQueryDTO query){
        Page<RegisteAgencyInforVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        query.setPersonnelId(HrmsUserSession.getCurrentUserSession().getUserId());
        registeAgencyInforMapper.findRegisteAgencyPage(query);
        return new PageResult<>(page);
    }
    
    /**
     * 备案机构变更审核
     * <一句话功能简述>
     * <功能详细描述>
     * @param query
     * @return
     * @see [类、类#方法、类#成员]
     */
    public PageResult<RegisteAgencyInforVO> findRegisteAgencyCheckPage(RegisteAgencyInforQueryDTO query){
        Page<RegisteAgencyInforVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, null);
        registeAgencyInforMapper.findRegisteAgencyCheckPage(query,permission);
        return new PageResult<>(page);
    }
    
    /**
     * 个人备案机构新增
     * <一句话功能简述>
     * <功能详细描述>
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int registeSave(RegisteAgencyInfor model){
        String personnelId = HrmsUserSession.getCurrentUserSession().getUserId();
        model.setPersonnelId(personnelId);
        model.setApplyTime(new Date());
        model.setAgencyCheckStatus(RegisteAgencyInfor.WAIT_STATUS);
        model.setAdminCheckStatus(RegisteAgencyInfor.WAIT_STATUS);
        return save(model);
    }
    
    public boolean check(String id,String agencyCheckRemarks,int status){
        RegisteAgencyInfor info = get(id);
        if (info == null) {
            throw new BusinessException("找不到审核对象");
        }
        if(HrmsUserSession.getCurrentUserSession().isAgencyManager()){
            info.setAgencyCheckStatus(status);
        }
        if(HrmsUserSession.getCurrentUserSession().isDistrictManager()){
            info.setAdminCheckStatus(status); 
        }
        info.setAgencyCheckRemarks(agencyCheckRemarks);
        if(update(info)>0){
            return true;   
        }
        return false;
    }
}