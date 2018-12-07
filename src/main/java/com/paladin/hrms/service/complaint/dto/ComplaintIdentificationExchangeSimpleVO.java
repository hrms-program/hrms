package com.paladin.hrms.service.complaint.dto;

public class ComplaintIdentificationExchangeSimpleVO {

	private String id;

	// 置换发起人员ID
	private String fromPersonnelId;

	// 置换发起人员姓名
	private String fromPersonnelName;

	// 置换发起人员证件号
	private String fromIdentificationNo;

	// 置换目标人员姓名
	private String toPersonnelName;

	// 置换目标人员ID
	private String toPersonnelId;

	// 置换目标人员证件号
	private String toIdentificationNo;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromPersonnelId() {
		return fromPersonnelId;
	}

	public void setFromPersonnelId(String fromPersonnelId) {
		this.fromPersonnelId = fromPersonnelId;
	}

	public String getFromPersonnelName() {
		return fromPersonnelName;
	}

	public void setFromPersonnelName(String fromPersonnelName) {
		this.fromPersonnelName = fromPersonnelName;
	}

	public String getFromIdentificationNo() {
		return fromIdentificationNo;
	}

	public void setFromIdentificationNo(String fromIdentificationNo) {
		this.fromIdentificationNo = fromIdentificationNo;
	}

	public String getToPersonnelName() {
		return toPersonnelName;
	}

	public void setToPersonnelName(String toPersonnelName) {
		this.toPersonnelName = toPersonnelName;
	}

	public String getToPersonnelId() {
		return toPersonnelId;
	}

	public void setToPersonnelId(String toPersonnelId) {
		this.toPersonnelId = toPersonnelId;
	}

	public String getToIdentificationNo() {
		return toIdentificationNo;
	}

	public void setToIdentificationNo(String toIdentificationNo) {
		this.toIdentificationNo = toIdentificationNo;
	}

}
