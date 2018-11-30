package com.paladin.hrms.controller.report.pojo;

import com.paladin.framework.common.OffsetPage;

public class ReportInforQuery extends OffsetPage{

	public String area;
	
	public String org;
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

}
