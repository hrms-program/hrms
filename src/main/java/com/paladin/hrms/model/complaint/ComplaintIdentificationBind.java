package com.paladin.hrms.model.complaint;

import java.util.Date;

public class ComplaintIdentificationBind extends ComplaintModel {

    public final static String COLUMN_PERSONAL_ID = "personalId";

    public final static String COLUMN_STATUS = "status";



	private Integer usedIdentificationType;


	private String usedIdentificationNo;


	private String attachments;

	private String checkPeople;


	private Date checkDate;



	public Integer getUsedIdentificationType() {
		return usedIdentificationType;
	}

	public void setUsedIdentificationType(Integer usedIdentificationType) {
		this.usedIdentificationType = usedIdentificationType;
	}

	public String getUsedIdentificationNo() {
		return usedIdentificationNo;
	}

	public void setUsedIdentificationNo(String usedIdentificationNo) {
		this.usedIdentificationNo = usedIdentificationNo;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}


	public String getCheckPeople() {
		return checkPeople;
	}

	public void setCheckPeople(String checkPeople) {
		this.checkPeople = checkPeople;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}



}