package com.paladin.hrms.service.org;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paladin.hrms.model.complaint.ComplaintPersonnelArchivesCheck;
import com.paladin.hrms.model.org.OrgPersonnelScienceEducation;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.common.GeneralCriteriaBuilder.Condition;

@Service
public class OrgPersonnelScienceEducationService extends PersonnelContextServiceSupport<OrgPersonnelScienceEducation>{

    public List<OrgPersonnelScienceEducation> getScienceEducation(String id) {
        return searchAll(new Condition(OrgPersonnelScienceEducation.COLUMN_FIELD_PERSONNEL_ID, QueryType.EQUAL, id));
    }
    
    public List<OrgPersonnelScienceEducation> getAppScienceEducation(Integer type){
        return searchAll(new Condition(OrgPersonnelScienceEducation.COLUMN_FIELD_EDUCATION_TYPE, QueryType.EQUAL, type));
    }

	@Override
	public int getPersonnelArchivesCheckType() {
		return ComplaintPersonnelArchivesCheck.RECORD_TYPE_SCIENCE_EDUCATION;
	}
}