package com.paladin.hrms.controller.report.pojo;

import com.paladin.framework.excel.write.WriteProperty;

public class ReportPersonnelInforDTO {
	
	public String personnelId;

	public String identificationNo;

    @WriteProperty(name = "姓名",cellIndex = 0)
	public String name;
	
	public String reportState;//上报状态

    @WriteProperty(name = "上报时间",cellIndex = 1,width = 15)
	public String reportTime;

    @WriteProperty(name = "上报确认人",cellIndex = 2,width = 15)
	public String reportUser;

    @WriteProperty(name = "上报确认人",cellIndex = 3,width = 15)
	public String basicInforStatus;//基本信息规范与否

    @WriteProperty(name = "上报确认人",cellIndex = 4,width = 15)
	public String workingInforStatus;//工作信息规范与否

    @WriteProperty(name = "上报确认人",cellIndex = 5,width = 15)
	public String licenseInforStatus;//执业信息规范与否

    @WriteProperty(name = "上报确认人",cellIndex = 6,width = 15)
	public String educationInforStatus;//教育信息规范与否
	
	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReportState() {
		return reportState;
	}

	public void setReportState(String reportState) {
		this.reportState = reportState;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportUser() {
		return reportUser;
	}

	public void setReportUser(String reportUser) {
		this.reportUser = reportUser;
	}

	public String getBasicInforStatus() {
		return basicInforStatus;
	}

	public void setBasicInforStatus(String basicInforStatus) {
		this.basicInforStatus = basicInforStatus;
	}

	public String getWorkingInforStatus() {
		return workingInforStatus;
	}

	public void setWorkingInforStatus(String workingInforStatus) {
		this.workingInforStatus = workingInforStatus;
	}

	public String getLicenseInforStatus() {
		return licenseInforStatus;
	}

	public void setLicenseInforStatus(String licenseInforStatus) {
		this.licenseInforStatus = licenseInforStatus;
	}

	public String getEducationInforStatus() {
		return educationInforStatus;
	}

	public void setEducationInforStatus(String educationInforStatus) {
		this.educationInforStatus = educationInforStatus;
	}
	
}
