package com.paladin.hrms.service.org.dto;

import java.util.Date;

import com.paladin.framework.excel.read.ReadProperty;

public class OrgPersonnelPracticeUploadDTO {
	// 医师执业类别代码
	@ReadProperty(cellIndex = 0, enumType = "practice-category-type")
	private Integer practiceCategory;
	// 医师执业范围代码
	@ReadProperty(cellIndex = 1)
	private String practiceScope;
	// 是否注册为多地点执业医师
	@ReadProperty(cellIndex = 2, enumType = "boolean")
	private Integer isMultisite;
	// 第2执业单位类别代码
	@ReadProperty(cellIndex = 3, enumType = "practice-category")
	private Integer secondCategory;
	// 第3执业单位类别代码
	@ReadProperty(cellIndex = 4, enumType = "practice-category")
	private Integer thirdCategory;
	// 专科特长
	@ReadProperty(cellIndex = 5)
	private String expertise;
	// 身份证件号码
	@ReadProperty(cellIndex = 6)
	private String idcard;
	// 执业注册时间
	@ReadProperty(cellIndex = 7)
	private Date registrationDate;
	// 是否由乡镇卫生院或社区卫生服务机构派驻村卫生室工作
	@ReadProperty(cellIndex = 8, enumType = "boolean")
	private Integer isDispatched;
	// 上年总收入
	@ReadProperty(cellIndex = 9)
	private String totalIncome;
	// 是否有养老保障
	@ReadProperty(cellIndex = 10, enumType = "boolean")
	private Integer isEndowmentInsurance;
	// 特殊岗位证书名称
	@ReadProperty(cellIndex = 11)
	private String certName;
	// 特殊岗位证书发证单位
	@ReadProperty(cellIndex = 12)
	private String issueUnit;
	// 特殊岗位证书发证获得时间
	@ReadProperty(cellIndex = 13)
	private Date issueDate;
	// 特殊岗位证书有效期开始时间
	@ReadProperty(cellIndex = 14)
	private Date startDate;
	// 特殊岗位证书有效期结束时间
	@ReadProperty(cellIndex = 15)
	private Date endDate;
	// 护士注册机关
	@ReadProperty(cellIndex = 16)
	private String nurseIssueUnit;
	// 护士最后注册日期
	@ReadProperty(cellIndex = 17)
	private Date lastRegistrationDate;
	// 护考免考
	@ReadProperty(cellIndex = 18, enumType = "boolean")
	private Integer isExam;
	// 护考时间
	@ReadProperty(cellIndex = 19)
	private Date nurseExamTime;
	// 护龄
	@ReadProperty(cellIndex = 20)
	private String nursingAge;
	// 护士工作类别
	@ReadProperty(cellIndex = 21)
	private String nurseCategory;
	// 从事护士工作开始时间
	@ReadProperty(cellIndex = 22)
	private Date startWorkDate;
	// 执业地点
	@ReadProperty(cellIndex = 23)
	private String practiceAddress;
	@ReadProperty(cellIndex = 24)
	// 执业地点编码
	private String addressCode;
	// 乡村医生执业证书编号
	@ReadProperty(cellIndex = 25)
	private String vdocCertCode;
	// 注册村级卫生机构名称
	@ReadProperty(cellIndex = 26)
	private String registVillageAgency;
	// 首次注册时间
	@ReadProperty(cellIndex = 27)
	private Date registDate;
	// 是否有医疗责任保险
	@ReadProperty(cellIndex = 28, enumType = "boolean")
	private Integer isMedicalInsurance;
	// 是否有工伤保险
	@ReadProperty(cellIndex = 29, enumType = "boolean")
	private Integer isInjuryInsurance;

	public Integer getPracticeCategory() {
		return practiceCategory;
	}

	public void setPracticeCategory(Integer practiceCategory) {
		this.practiceCategory = practiceCategory;
	}

	public String getPracticeScope() {
		return practiceScope;
	}

	public void setPracticeScope(String practiceScope) {
		this.practiceScope = practiceScope;
	}

	public Integer getIsMultisite() {
		return isMultisite;
	}

	public void setIsMultisite(Integer isMultisite) {
		this.isMultisite = isMultisite;
	}

	public Integer getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(Integer secondCategory) {
		this.secondCategory = secondCategory;
	}

	public Integer getThirdCategory() {
		return thirdCategory;
	}

	public void setThirdCategory(Integer thirdCategory) {
		this.thirdCategory = thirdCategory;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Integer getIsDispatched() {
		return isDispatched;
	}

	public void setIsDispatched(Integer isDispatched) {
		this.isDispatched = isDispatched;
	}

	public String getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getIssueUnit() {
		return issueUnit;
	}

	public void setIssueUnit(String issueUnit) {
		this.issueUnit = issueUnit;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getNurseIssueUnit() {
		return nurseIssueUnit;
	}

	public void setNurseIssueUnit(String nurseIssueUnit) {
		this.nurseIssueUnit = nurseIssueUnit;
	}

	public Date getLastRegistrationDate() {
		return lastRegistrationDate;
	}

	public void setLastRegistrationDate(Date lastRegistrationDate) {
		this.lastRegistrationDate = lastRegistrationDate;
	}

	public Integer getIsExam() {
		return isExam;
	}

	public void setIsExam(Integer isExam) {
		this.isExam = isExam;
	}

	public String getNursingAge() {
		return nursingAge;
	}

	public void setNursingAge(String nursingAge) {
		this.nursingAge = nursingAge;
	}

	public String getNurseCategory() {
		return nurseCategory;
	}

	public void setNurseCategory(String nurseCategory) {
		this.nurseCategory = nurseCategory;
	}

	public Date getStartWorkDate() {
		return startWorkDate;
	}

	public void setStartWorkDate(Date startWorkDate) {
		this.startWorkDate = startWorkDate;
	}

	public String getPracticeAddress() {
		return practiceAddress;
	}

	public void setPracticeAddress(String practiceAddress) {
		this.practiceAddress = practiceAddress;
	}

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

	public String getVdocCertCode() {
		return vdocCertCode;
	}

	public void setVdocCertCode(String vdocCertCode) {
		this.vdocCertCode = vdocCertCode;
	}

	public String getRegistVillageAgency() {
		return registVillageAgency;
	}

	public void setRegistVillageAgency(String registVillageAgency) {
		this.registVillageAgency = registVillageAgency;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Integer getIsEndowmentInsurance() {
		return isEndowmentInsurance;
	}

	public void setIsEndowmentInsurance(Integer isEndowmentInsurance) {
		this.isEndowmentInsurance = isEndowmentInsurance;
	}

	public Integer getIsMedicalInsurance() {
		return isMedicalInsurance;
	}

	public void setIsMedicalInsurance(Integer isMedicalInsurance) {
		this.isMedicalInsurance = isMedicalInsurance;
	}

	public Integer getIsInjuryInsurance() {
		return isInjuryInsurance;
	}

	public void setIsInjuryInsurance(Integer isInjuryInsurance) {
		this.isInjuryInsurance = isInjuryInsurance;
	}

	public Date getNurseExamTime() {
		return nurseExamTime;
	}

	public void setNurseExamTime(Date nurseExamTime) {
		this.nurseExamTime = nurseExamTime;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
}
