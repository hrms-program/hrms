package com.paladin.hrms.service.org;

import java.util.List;

import org.springframework.stereotype.Service;
import com.paladin.hrms.model.org.OrgPersonnelYearAssess;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.common.GeneralCriteriaBuilder.Condition;
import com.paladin.framework.core.ServiceSupport;

@Service
public class OrgPersonnelYearAssessService extends ServiceSupport<OrgPersonnelYearAssess>{

    public List<OrgPersonnelYearAssess> getPersonnelYearAssess(String id) {
        return searchAll(new Condition(OrgPersonnelYearAssess.COLUMN_FIELD_PERSONNEL_ID, QueryType.EQUAL, id));
    }
}