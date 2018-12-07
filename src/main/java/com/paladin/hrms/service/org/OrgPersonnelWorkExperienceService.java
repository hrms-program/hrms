package com.paladin.hrms.service.org;

import java.util.List;
import org.springframework.stereotype.Service;

import com.paladin.hrms.model.complaint.ComplaintPersonnelArchivesCheck;
import com.paladin.hrms.model.org.OrgPersonnelWorkExperience;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.common.GeneralCriteriaBuilder.Condition;

@Service
public class OrgPersonnelWorkExperienceService extends PersonnelContextServiceSupport<OrgPersonnelWorkExperience>{

    public List<OrgPersonnelWorkExperience> getPersonnelExperience(String id) {
        return searchAll(new Condition(OrgPersonnelWorkExperience.COLUMN_FIELD_PERSONNEL_ID, QueryType.EQUAL, id));
    }

	@Override
	public int getPersonnelArchivesCheckType() {
		return ComplaintPersonnelArchivesCheck.RECORD_TYPE_WORK_EXPERIENCE;
	}
}