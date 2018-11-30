package com.paladin.hrms.service.org.dto;

import com.paladin.framework.common.OffsetPage;

public class OrgPersonnelExportQueryDTO extends OffsetPage {
	
	private String name;
	
	private String identificationNo;
	
	private String agencyId;
	
	private String agencyName;
	
	private Integer sex;
	
	private Integer major;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getMajor() {
		return major;
	}

	public void setMajor(Integer major) {
		this.major = major;
	}
	
	
	
	
}
