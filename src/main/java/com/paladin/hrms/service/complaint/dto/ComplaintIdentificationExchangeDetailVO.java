package com.paladin.hrms.service.complaint.dto;

import java.util.Date;

public class ComplaintIdentificationExchangeDetailVO {
	
	private ComplaintIdentificationExchangePersonnelVO fromPersonnel;
	
	private ComplaintIdentificationExchangePersonnelVO toPersonnel;
	
	public ComplaintIdentificationExchangePersonnelVO getFromPersonnel() {
		return fromPersonnel;
	}

	public void setFromPersonnel(ComplaintIdentificationExchangePersonnelVO fromPersonnel) {
		this.fromPersonnel = fromPersonnel;
	}

	public ComplaintIdentificationExchangePersonnelVO getToPersonnel() {
		return toPersonnel;
	}

	public void setToPersonnel(ComplaintIdentificationExchangePersonnelVO toPersonnel) {
		this.toPersonnel = toPersonnel;
	}
	
	public static class ComplaintIdentificationExchangePersonnelVO {
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
		//机构名称
		private String agencyName;
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
		
		private Integer isDelete;
		
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
		public String getAgencyName() {
			return agencyName;
		}
		public void setAgencyName(String agencyName) {
			this.agencyName = agencyName;
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
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public Integer getIsDelete() {
			return isDelete;
		}
		public void setIsDelete(Integer isDelete) {
			this.isDelete = isDelete;
		}
	}


	
}
