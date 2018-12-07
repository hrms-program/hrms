package com.paladin.hrms.model.org;

import com.paladin.framework.common.UnDeleteBaseModel;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/*
 * 个人信息 OrgPersonnel
 * 
 */
public class OrgPersonnel extends UnDeleteBaseModel {
	
	public final static int STATUS_NORMAL = 0;
	public final static int STATUS_LEVEL = 2;

	
	public static final String COLUMN_FIELD_IDENTIFICATION_NO = "identificationNo";
	
	@Id
	@GeneratedValue(generator = "UUID")
	private String id;
	//身份证件种类
	private Integer identificationType;
	//身份证件号码
	private String identificationNo;
	//姓名
	private String name;
	//曾用名
	private String usedName;
	//性别
	private Integer sex;
	//手机号码
	private String cellphone;
	//办公室电话
	private String officePhone;
	//用户图片
	private String profilePhoto;
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
	//国籍
	private Integer nationality;
	//民族
	private Integer nation;
	//出生日期
	private Date birthday;
	//开始工作时间
	private Date startWorkTime;
	//入党时间
	private Date joinPartyTime;
	//政治面貌
	private Integer politicalAffiliation;
	//兴趣爱好
	private String interest;
	//籍贯
	private String nativePlace;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
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

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
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

	public Integer getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(Integer identificationType) {
		this.identificationType = identificationType;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public Integer getNationality() {
		return nationality;
	}

	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}

	public Integer getNation() {
		return nation;
	}

	public void setNation(Integer nation) {
		this.nation = nation;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getStartWorkTime() {
		return startWorkTime;
	}

	public void setStartWorkTime(Date startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	public Date getJoinPartyTime() {
		return joinPartyTime;
	}

	public void setJoinPartyTime(Date joinPartyTime) {
		this.joinPartyTime = joinPartyTime;
	}

	public Integer getPoliticalAffiliation() {
		return politicalAffiliation;
	}

	public void setPoliticalAffiliation(Integer politicalAffiliation) {
		this.politicalAffiliation = politicalAffiliation;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getUsedName() {
		return usedName;
	}

	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}

}