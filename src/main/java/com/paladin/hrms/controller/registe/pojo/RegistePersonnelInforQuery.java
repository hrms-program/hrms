package com.paladin.hrms.controller.registe.pojo;

import java.util.List;

import com.paladin.framework.common.OffsetPage;

public class RegistePersonnelInforQuery extends OffsetPage{

	private String applyDate;//申请时间
	
	private List<String> agencyCheckStatus;//机构审核时间
	
	private List<String> adminCheckStatus;//行政审核时间

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public List<String> getAgencyCheckStatus() {
		return agencyCheckStatus;
	}

	public void setAgencyCheckStatus(List<String> agencyCheckStatus) {
		this.agencyCheckStatus = agencyCheckStatus;
	}

	public List<String> getAdminCheckStatus() {
		return adminCheckStatus;
	}

	public void setAdminCheckStatus(List<String> adminCheckStatus) {
		this.adminCheckStatus = adminCheckStatus;
	}
	
}
