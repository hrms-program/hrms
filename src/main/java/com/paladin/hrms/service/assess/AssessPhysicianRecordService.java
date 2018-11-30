package com.paladin.hrms.service.assess;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.hrms.controller.assess.pojo.PhysicianAssessQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.assess.AssessPhysicianRecordMapper;
import com.paladin.hrms.model.assess.AssessPhysicianRecord;
import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;

@Service
public class AssessPhysicianRecordService extends ServiceSupport<AssessPhysicianRecord>{
    
    @Autowired
    AssessPhysicianRecordMapper assessPhysicianRecordMapper;
    
    public PageResult<AssessPhysicianRecord> findAssessPhysicianRecord(PhysicianAssessQuery query) {
        Page<AssessPhysicianRecord> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        assessPhysicianRecordMapper.findAssessPhysicianRecord(query, permission);
        return new PageResult<>(page);
    } 

    public int getAssessCycle(String identificationNo,String assessCycle){
        List<AssessPhysicianRecord> titles = searchAll(new GeneralCriteriaBuilder.Condition[] {
            new GeneralCriteriaBuilder.Condition(AssessPhysicianRecord.COLUMN_IDENTIFICATION_NO, QueryType.EQUAL, identificationNo),
            new GeneralCriteriaBuilder.Condition(AssessPhysicianRecord.COLUMN_ASSESS_CYCLE, QueryType.EQUAL, assessCycle)});
         return titles.size();
    }
}