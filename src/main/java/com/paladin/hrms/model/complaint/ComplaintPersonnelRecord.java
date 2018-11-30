package com.paladin.hrms.model.complaint;

import com.paladin.framework.common.BaseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ComplaintPersonnelRecord extends BaseModel {

	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private String personnelId;

	private Integer type;

	private Integer result;

	private Integer oldIdentificationType;

	private Integer nowIdentificationType;

	private String oldIdentificationNo;

	private String nowIdentificationNo;

	private String oldName;

	private String nowName;

	private Integer oldSex;

	private Integer nowSex;

	private String illustrate;

	private String qq;

	private String cellphone;

	private String email;

	private String attachments;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getOldIdentificationType() {
		return oldIdentificationType;
	}

	public void setOldIdentificationType(Integer oldIdentificationType) {
		this.oldIdentificationType = oldIdentificationType;
	}

	public Integer getNowIdentificationType() {
		return nowIdentificationType;
	}

	public void setNowIdentificationType(Integer nowIdentificationType) {
		this.nowIdentificationType = nowIdentificationType;
	}

	public String getOldIdentificationNo() {
		return oldIdentificationNo;
	}

	public void setOldIdentificationNo(String oldIdentificationNo) {
		this.oldIdentificationNo = oldIdentificationNo;
	}

	public String getNowIdentificationNo() {
		return nowIdentificationNo;
	}

	public void setNowIdentificationNo(String nowIdentificationNo) {
		this.nowIdentificationNo = nowIdentificationNo;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getNowName() {
		return nowName;
	}

	public void setNowName(String nowName) {
		this.nowName = nowName;
	}

	public Integer getOldSex() {
		return oldSex;
	}

	public void setOldSex(Integer oldSex) {
		this.oldSex = oldSex;
	}

	public Integer getNowSex() {
		return nowSex;
	}

	public void setNowSex(Integer nowSex) {
		this.nowSex = nowSex;
	}

	public String getIllustrate() {
		return illustrate;
	}

	public void setIllustrate(String illustrate) {
		this.illustrate = illustrate;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

}