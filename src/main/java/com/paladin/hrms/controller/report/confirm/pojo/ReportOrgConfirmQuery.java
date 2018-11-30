package com.paladin.hrms.controller.report.confirm.pojo;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class ReportOrgConfirmQuery extends OffsetPage{

	public String name;

	@QueryCondition(type = QueryType.LIKE)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
