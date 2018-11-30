package com.paladin.hrms.model.org;

import com.paladin.framework.common.BaseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OrgDistrictManager extends BaseModel {

	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private String identificationNo;

	private String name;

	private Integer districtCode;

	private String cellphone;

	private String account;

	private String sex;

	private String email;

	private String qq;

	private String departmentPosition;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getDepartmentPosition() {
		return departmentPosition;
	}

	public void setDepartmentPosition(String departmentPosition) {
		this.departmentPosition = departmentPosition;
	}

}