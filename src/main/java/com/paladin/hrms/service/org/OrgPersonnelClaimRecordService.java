package com.paladin.hrms.service.org;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelClaimQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.org.OrgPersonnelClaimRecordMapper;
import com.paladin.hrms.model.org.OrgPersonnelClaimDetail;
import com.paladin.hrms.model.org.OrgPersonnelClaimRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrgPersonnelClaimRecordService extends ServiceSupport<OrgPersonnelClaimRecord> {
    @Autowired
    private OrgPersonnelClaimRecordMapper orgPersonnelClaimRecordMapper;

    
    /*
     * 给其增加搜索权限
     * @author jisanjie
     * 2018/11/23 13:26
     * 
     */
    public PageResult<OrgPersonnelClaimDetail> findPersonPageByQuery(OrgPersonnelClaimQuery query) {
        Page<OrgPersonnelClaimDetail> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        List<Integer> status = new ArrayList<>(2);
        Collections.addAll(status,2,3);
        orgPersonnelClaimRecordMapper.findAllPeople(query,status,permission);
        return new PageResult<>(page);
    }
}