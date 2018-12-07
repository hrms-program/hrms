package com.paladin.hrms.model.org;

import com.paladin.framework.common.BaseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OrgPersonnelClaimRecord extends BaseModel {

	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private String personnelId;

	private String originAgencyId;
	
	private String targetAgencyId;

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

	public String getOriginAgencyId() {
		return originAgencyId;
	}

	public void setOriginAgencyId(String originAgencyId) {
		this.originAgencyId = originAgencyId;
	}

	public String getTargetAgencyId() {
		return targetAgencyId;
	}

	public void setTargetAgencyId(String targetAgencyId) {
		this.targetAgencyId = targetAgencyId;
	}

	
}