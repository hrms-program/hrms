package com.paladin.hrms.model.complaint;

import com.paladin.framework.common.BaseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class ComplaintIdentificationBindRecord extends BaseModel {

	@Id
    @GeneratedValue(generator = "UUID")
	private String id;

	private Integer usedIdentificationType;

	private String usedIdentificationNo;

	private String attachments;

	private Integer result;

	private String illustrate;

	private String checkPeople;

	private Date checkDate;

	private String personnelId;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

    public String getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(String personnelId) {
        this.personnelId = personnelId;
    }
}