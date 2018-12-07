package com.paladin.hrms.service.complaint.dto;


import com.paladin.framework.common.OffsetPage;

public class ComplaintIdentificationExchangeQueryDTO extends OffsetPage {
     
	private String name;
	private String identificationNo;
	
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
	
     
}
