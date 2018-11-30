package com.paladin.hrms.model.complaint;

public class ComplaintPersonnel extends ComplaintModel {

	public final static String COLUMN_TYPE = "type";

	public final static int TYPE_IDENTIFICATION_TYPE = 1;
	public final static int TYPE_IDENTIFICATION_NO = 2;
	public final static int TYPE_NAME = 3;
	public final static int TYPE_SEX = 4;
	public final static int TYPE_ALL = 5;
	
	private Integer type;

	private Integer nowIdentificationType;

	private String nowIdentificationNo;

	private String nowName;

	private Integer nowSex;

	private String cellphone;

	private String qq;

	private String email;

	private String attachments;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getNowIdentificationType() {
		return nowIdentificationType;
	}

	public void setNowIdentificationType(Integer nowIdentificationType) {
		this.nowIdentificationType = nowIdentificationType;
	}

	public String getNowIdentificationNo() {
		return nowIdentificationNo;
	}

	public void setNowIdentificationNo(String nowIdentificationNo) {
		this.nowIdentificationNo = nowIdentificationNo;
	}

	public String getNowName() {
		return nowName;
	}

	public void setNowName(String nowName) {
		this.nowName = nowName;
	}

	public Integer getNowSex() {
		return nowSex;
	}

	public void setNowSex(Integer nowSex) {
		this.nowSex = nowSex;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
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