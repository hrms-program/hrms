package com.paladin.hrms.service.org;

import java.util.List;
import org.springframework.stereotype.Service;
import com.paladin.hrms.model.org.OrgPersonnelPracticeRegistration;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.common.GeneralCriteriaBuilder.Condition;
import com.paladin.framework.core.ServiceSupport;

@Service
public class OrgPersonnelPracticeRegistrationService extends ServiceSupport<OrgPersonnelPracticeRegistration>{

    public List<OrgPersonnelPracticeRegistration> getPracticeRegistration(String id) {
        return searchAll(new Condition(OrgPersonnelPracticeRegistration.COLUMN_FIELD_PERSONNEL_ID, QueryType.EQUAL, id));
    }
}