package com.paladin.hrms.controller.report.pojo;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class ReportPersonnelInforQuery extends OffsetPage{

	public String agencyId;
	
	
	public String name;
	

	@QueryCondition(type = QueryType.LIKE)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	
}
