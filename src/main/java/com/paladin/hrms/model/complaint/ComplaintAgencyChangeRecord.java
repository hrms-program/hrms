package com.paladin.hrms.model.complaint;

import com.paladin.framework.common.BaseModel;
import javax.persistence.Id;

public class ComplaintAgencyChangeRecord extends BaseModel {

	// 
	@Id
	private String id;

	// 
	private String personnelId;

	// 原机构id
	private String agencyId;

	// 目的机构id
	private String targetAgencyId;

	// 
	private Integer result;

	// 处理说明
	private String illustrate;

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

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getTargetAgencyId() {
		return targetAgencyId;
	}

	public void setTargetAgencyId(String targetAgencyId) {
		this.targetAgencyId = targetAgencyId;
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

}