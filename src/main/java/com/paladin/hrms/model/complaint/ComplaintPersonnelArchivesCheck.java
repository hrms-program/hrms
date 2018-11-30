package com.paladin.hrms.model.complaint;

import com.paladin.framework.common.BaseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ComplaintPersonnelArchivesCheck extends BaseModel {
	
	public final static String COLUMN_FIELD_RECORD_ID ="recordId";
	public final static String COLUMN_FIELD_RECORD_TYPE ="recordType";

	
	public final static int RECORD_TYPE_EDUCATION = 1;
	public final static int RECORD_TYPE_POSITIONAL_INFO = 2;
	public final static int RECORD_TYPE_WORK_EXPERIENCE = 3;
	public final static int RECORD_TYPE_CULTIVATE = 4;
	public final static int RECORD_TYPE_SCIENCE_EDUCATION = 5;
	public final static int RECORD_TYPE_REWARD_INFO = 6;
	

	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private String personnelId;

	private String recordId;

	private Integer recordType;

	private Integer checkStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

}