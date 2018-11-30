package com.paladin.hrms.controller.org.pojo;

import java.util.Date;

public class OrgPersonnelEducationDTO {
	
	private String id;

	private String personnelId;

	private String schoolName;

	private String schoolAddress;

	private Integer education;

	private String diplomaNo;

	private Integer academicDegree;

	private String academicDegreeNo;

	private Integer major;

	private Integer educationType;

	private Date joinSchoolTime;

	private Date graduationTime;

	private String hasRecordStatus;

	private String otherInstructions;

	private String attachments;					

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public String getDiplomaNo() {
		return diplomaNo;
	}

	public void setDiplomaNo(String diplomaNo) {
		this.diplomaNo = diplomaNo;
	}

	public Integer getAcademicDegree() {
		return academicDegree;
	}

	public void setAcademicDegree(Integer academicDegree) {
		this.academicDegree = academicDegree;
	}

	public String getAcademicDegreeNo() {
		return academicDegreeNo;
	}

	public void setAcademicDegreeNo(String academicDegreeNo) {
		this.academicDegreeNo = academicDegreeNo;
	}

	public Integer getMajor() {
		return major;
	}

	public void setMajor(Integer major) {
		this.major = major;
	}

	public Integer getEducationType() {
		return educationType;
	}

	public void setEducationType(Integer educationType) {
		this.educationType = educationType;
	}

	public Date getJoinSchoolTime() {
		return joinSchoolTime;
	}

	public void setJoinSchoolTime(Date joinSchoolTime) {
		this.joinSchoolTime = joinSchoolTime;
	}

	public Date getGraduationTime() {
		return graduationTime;
	}

	public void setGraduationTime(Date graduationTime) {
		this.graduationTime = graduationTime;
	}

	public String getHasRecordStatus() {
		return hasRecordStatus;
	}

	public void setHasRecordStatus(String hasRecordStatus) {
		this.hasRecordStatus = hasRecordStatus;
	}

	public String getOtherInstructions() {
		return otherInstructions;
	}

	public void setOtherInstructions(String otherInstructions) {
		this.otherInstructions = otherInstructions;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}
	
}
