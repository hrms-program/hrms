package com.paladin.hrms.model.org;

import java.util.Date;

public class OrgPersonnelCultivate extends PersonnelContextModel{	           

	private Integer cultivateType;

	private String cultivateUnit;

	private String cultivatePlace;

	private Date cultivateStartTime;

	private Date cultivateEndTime;

	private Integer endSituation;

	private String attachments;


	public Integer getCultivateType() {
		return cultivateType;
	}

	public void setCultivateType(Integer cultivateType) {
		this.cultivateType = cultivateType;
	}

	public String getCultivateUnit() {
		return cultivateUnit;
	}

	public void setCultivateUnit(String cultivateUnit) {
		this.cultivateUnit = cultivateUnit;
	}

	public String getCultivatePlace() {
		return cultivatePlace;
	}

	public void setCultivatePlace(String cultivatePlace) {
		this.cultivatePlace = cultivatePlace;
	}

	public Date getCultivateStartTime() {
		return cultivateStartTime;
	}

	public void setCultivateStartTime(Date cultivateStartTime) {
		this.cultivateStartTime = cultivateStartTime;
	}

	public Date getCultivateEndTime() {
		return cultivateEndTime;
	}

	public void setCultivateEndTime(Date cultivateEndTime) {
		this.cultivateEndTime = cultivateEndTime;
	}

	public Integer getEndSituation() {
		return endSituation;
	}

	public void setEndSituation(Integer endSituation) {
		this.endSituation = endSituation;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

}