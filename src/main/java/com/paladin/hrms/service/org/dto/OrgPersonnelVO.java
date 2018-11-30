package com.paladin.hrms.service.org.dto;

public class OrgPersonnelVO {
	
	private String id;
	//身份证件种类
	private Integer identificationType;
	//身份证件号码
	private String identificationNo;
	//姓名
	private String name;
	//性别
	private Integer sex;
	//手机号码
	private String cellphone;
	//机构id
	private String agencyId;
	//机构名称
	private String agencyName;
	//城市代码
	private Integer cityCode;
	//乡镇代码
	private Integer townCode;
	//行政区划代码 
	private Integer districtCode;
	// 从事专业
	private String major;
	// 人员类型
	private Integer personnelType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getIdentificationType() {
		return identificationType;
	}
	public void setIdentificationType(Integer identificationType) {
		this.identificationType = identificationType;
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
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
	public Integer getCityCode() {
		return cityCode;
	}
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}
	public Integer getTownCode() {
		return townCode;
	}
	public void setTownCode(Integer townCode) {
		this.townCode = townCode;
	}
	public Integer getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}
	public Integer getPersonnelType() {
		return personnelType;
	}
	public void setPersonnelType(Integer personnelType) {
		this.personnelType = personnelType;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	
}
