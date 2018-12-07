package com.paladin.hrms.model.complaint;

import com.paladin.framework.common.BaseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ComplaintIdentificationExchange extends BaseModel {

	// 
	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	// 置换发起人员ID
	private String fromPersonnelId;

	// 置换目标人员ID
	private String toPersonnelId;


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

	public String getToPersonnelId() {
		return toPersonnelId;
	}

	public void setToPersonnelId(String toPersonnelId) {
		this.toPersonnelId = toPersonnelId;
	}

}