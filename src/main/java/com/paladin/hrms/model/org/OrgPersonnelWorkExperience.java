package com.paladin.hrms.model.org;

import java.util.Date;

public class OrgPersonnelWorkExperience extends PersonnelContextModel{
    
	private String workUnit;

	private String workPlace;

	private Date workStartTime;

	private Date workEndTime;

	private Integer workDept;

	private Integer jobSituation;

	private Integer workPost;

	private Integer majorTechnologyWork;

	private String attachments;
	
	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public Date getWorkStartTime() {
		return workStartTime;
	}

	public void setWorkStartTime(Date workStartTime) {
		this.workStartTime = workStartTime;
	}

	public Date getWorkEndTime() {
		return workEndTime;
	}

	public void setWorkEndTime(Date workEndTime) {
		this.workEndTime = workEndTime;
	}

	public Integer getWorkDept() {
		return workDept;
	}

	public void setWorkDept(Integer workDept) {
		this.workDept = workDept;
	}

	public Integer getJobSituation() {
		return jobSituation;
	}

	public void setJobSituation(Integer jobSituation) {
		this.jobSituation = jobSituation;
	}

	public Integer getWorkPost() {
		return workPost;
	}

	public void setWorkPost(Integer workPost) {
		this.workPost = workPost;
	}

	public Integer getMajorTechnologyWork() {
		return majorTechnologyWork;
	}

	public void setMajorTechnologyWork(Integer majorTechnologyWork) {
		this.majorTechnologyWork = majorTechnologyWork;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

}