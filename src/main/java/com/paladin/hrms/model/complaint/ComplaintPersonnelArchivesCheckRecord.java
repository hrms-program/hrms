package com.paladin.hrms.model.complaint;

import com.paladin.framework.common.BaseModel;

import tk.mybatis.mapper.annotation.IgnoreInMultipleResult;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ComplaintPersonnelArchivesCheckRecord extends BaseModel {

	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private String recordId;
	
	private Integer recordType;
	
	private String personnelId;
	
	@IgnoreInMultipleResult
	private String checkContent;
	
	private Integer result;
	
	private String illustrate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getIllustrate() {
		return illustrate;
	}

	public void setIllustrate(String illustrate) {
		this.illustrate = illustrate;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	

}