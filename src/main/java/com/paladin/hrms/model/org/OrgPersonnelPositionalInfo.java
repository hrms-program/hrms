package com.paladin.hrms.model.org;

import java.util.Date;


public class OrgPersonnelPositionalInfo extends PersonnelContextModel{

	private String positionalTitleName;

	private Integer positionalTitleType;

	private Integer positionalLevelLevel;

	private Date positionalTitleTime;

	private String attachments;

	public String getPositionalTitleName() {
		return positionalTitleName;
	}

	public void setPositionalTitleName(String positionalTitleName) {
		this.positionalTitleName = positionalTitleName;
	}

	public Integer getPositionalTitleType() {
		return positionalTitleType;
	}

	public void setPositionalTitleType(Integer positionalTitleType) {
		this.positionalTitleType = positionalTitleType;
	}

	public Integer getPositionalLevelLevel() {
		return positionalLevelLevel;
	}

	public void setPositionalLevelLevel(Integer positionalLevelLevel) {
		this.positionalLevelLevel = positionalLevelLevel;
	}

	public Date getPositionalTitleTime() {
		return positionalTitleTime;
	}

	public void setPositionalTitleTime(Date positionalTitleTime) {
		this.positionalTitleTime = positionalTitleTime;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

}