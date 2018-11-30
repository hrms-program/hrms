package com.paladin.hrms.service.org;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paladin.hrms.model.complaint.ComplaintPersonnelArchivesCheck;
import com.paladin.hrms.model.org.OrgPersonnelEducation;
import com.paladin.framework.common.GeneralCriteriaBuilder.Condition;
import com.paladin.framework.common.QueryType;

@Service
public class OrgPersonnelEducationService extends PersonnelContextServiceSupport<OrgPersonnelEducation> {

	public List<OrgPersonnelEducation> getPersonnelEducations(String id) {
		return searchAll(new Condition(OrgPersonnelEducation.COLUMN_FIELD_PERSONNEL_ID, QueryType.EQUAL, id));
	}

	@Override
	public int getPersonnelArchivesCheckType() {
		return ComplaintPersonnelArchivesCheck.RECORD_TYPE_EDUCATION;
	}
	
}