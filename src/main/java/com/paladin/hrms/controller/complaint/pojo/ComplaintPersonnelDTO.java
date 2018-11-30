package com.paladin.hrms.controller.complaint.pojo;

/**
 * 用于前台提交保存
 * @author TontoZhou
 * @since 2018年11月26日
 */
public class ComplaintPersonnelDTO {
	
	private String personnelId;

	private Integer nowIdentificationType;

	private String nowIdentificationNo;

	private String nowName;

	private Integer nowSex;

	private String illustrate;

	private String cellphone;

	private String qq;

	private String email;

	private String attachments;

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

	public String getIllustrate() {
		return illustrate;
	}

	public void setIllustrate(String illustrate) {
		this.illustrate = illustrate;
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

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	
}
