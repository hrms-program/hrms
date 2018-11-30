package com.paladin.hrms.model.org;

import java.util.Date;

import javax.persistence.Id;

public class OrgPersonnelPractice extends PersonnelModel {
	
	public final static int PERSONNEL_TYPE_DOCTOR = 1;
	public final static int PERSONNEL_TYPE_NURSE = 2;
	public final static int PERSONNEL_TYPE_COUNTRY_DOCTOR = 3;
	public final static int PERSONNEL_TYPE_TECHNICIAN = 4;
	public final static int PERSONNEL_TYPE_PHARMACIST = 5;
	public final static int PERSONNEL_TYPE_MANAGE = 6;
	public final static int PERSONNEL_TYPE_LABOR = 7;
	public final static int PERSONNEL_TYPE_OTHER = 8;
	
	@Id
	private String id;
	// 人员类型
	private Integer personnelType;
	// 是否全科医生
	private Integer generalPractitioner;
	// 医师执业证书编号
	private String docCertCode;
	// 医师资格证书编码
	private String docQualificationCode;
	// 是否获得国家住院医师规范化培训合格证书
	private Integer isCountryCert;
	// 住院医师规范化培训合格证书编号
	private String inpatientCertCode;
	// 是否注册为全科医学专业
	private Integer isGeneralPractitioner;
	// 执业地点
	private String practiceAddress;
	// 执业注册时间(首次注册时间)
	private Date registrationDate;
	// 医师执业类别
	private String practiceCategory;
	// 医师执业范围
	private String practiceScope;
	// 是否由乡镇卫生院或社区卫生服务机构派驻村卫生室工作
	private Integer isDispatched;
	// 是否多地点职业
	private Integer isMultisite;
	// 第2执业单位类别
	private String secondCategory;
	// 第3执业单位类别
	private String thirdCategory;
	// 专业特长
	private String expertise;
	// 全科医生取得培训合格证书情况
	private String docTrainCert;
	// 护士执业证书编号
	private String nurseCertCode;
	// 护士执业单位
	private String nurseInstitution;
	// （护士）最后注册日期
	private Date lastRegistrationDate;
	// (护士)是否免考
	private Integer isExam;
	// 护考时间
	private Date nurseExamTime;
	// 从事护士工作开始时间
	private Date startWorkDate;
	// (护士)工作类别
	private String nurseCategory;
	// 乡村医生执业证书编号
	private String vdocCertCode;
	// 注册村级卫生机构名称
	private String registVillageAgency;
	// （村医）注册时间(首次注册时间)
	private Date registDate;
	// 上年总收入
	private Integer totalIncome;
	// 是否有医疗责任保险
	private Integer isMedicalInsurance;
	// 是否有工伤保险
	private Integer isInjuryInsurance;
	// 是否有养老保险
	private String isEndowmentInsurance;
	// 从事乡村医生工作年限(年)
	private Integer workYears;
	// 高中及以下学历乡村医生是否为在职培训合格者
	private Integer isOnjobTrain;
	// 特殊岗位证书名称
	private String certName;
	// 特殊岗位证书发证单位
	private String issueUnit;
	// 特殊岗位证书发证获得时间
	private Date issueDate;
	// 特殊岗位证书有效期开始时间
	private Date startDate;
	// 特殊岗位证书有效期结束时间
	private Date endDate;
	// 是否在村卫生室工作
	private Integer isInVillageClinic;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPersonnelType() {
		return personnelType;
	}

	public void setPersonnelType(Integer personnelType) {
		this.personnelType = personnelType;
	}

	public String getDocCertCode() {
		return docCertCode;
	}

	public void setDocCertCode(String docCertCode) {
		this.docCertCode = docCertCode;
	}

	public String getDocQualificationCode() {
		return docQualificationCode;
	}

	public void setDocQualificationCode(String docQualificationCode) {
		this.docQualificationCode = docQualificationCode;
	}

	public Integer getIsCountryCert() {
		return isCountryCert;
	}

	public void setIsCountryCert(Integer isCountryCert) {
		this.isCountryCert = isCountryCert;
	}

	public String getInpatientCertCode() {
		return inpatientCertCode;
	}

	public void setInpatientCertCode(String inpatientCertCode) {
		this.inpatientCertCode = inpatientCertCode;
	}

	public Integer getIsGeneralPractitioner() {
		return isGeneralPractitioner;
	}

	public void setIsGeneralPractitioner(Integer isGeneralPractitioner) {
		this.isGeneralPractitioner = isGeneralPractitioner;
	}

	public String getPracticeAddress() {
		return practiceAddress;
	}

	public void setPracticeAddress(String practiceAddress) {
		this.practiceAddress = practiceAddress;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getPracticeCategory() {
		return practiceCategory;
	}

	public void setPracticeCategory(String practiceCategory) {
		this.practiceCategory = practiceCategory;
	}

	public String getPracticeScope() {
		return practiceScope;
	}

	public void setPracticeScope(String practiceScope) {
		this.practiceScope = practiceScope;
	}

	public Integer getIsDispatched() {
		return isDispatched;
	}

	public void setIsDispatched(Integer isDispatched) {
		this.isDispatched = isDispatched;
	}

	public Integer getIsMultisite() {
		return isMultisite;
	}

	public void setIsMultisite(Integer isMultisite) {
		this.isMultisite = isMultisite;
	}

	public String getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(String secondCategory) {
		this.secondCategory = secondCategory;
	}

	public String getThirdCategory() {
		return thirdCategory;
	}

	public void setThirdCategory(String thirdCategory) {
		this.thirdCategory = thirdCategory;
	}

	public String getDocTrainCert() {
		return docTrainCert;
	}

	public void setDocTrainCert(String docTrainCert) {
		this.docTrainCert = docTrainCert;
	}

	public String getNurseCertCode() {
		return nurseCertCode;
	}

	public void setNurseCertCode(String nurseCertCode) {
		this.nurseCertCode = nurseCertCode;
	}

	public String getNurseInstitution() {
		return nurseInstitution;
	}

	public void setNurseInstitution(String nurseInstitution) {
		this.nurseInstitution = nurseInstitution;
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

	public Date getStartWorkDate() {
		return startWorkDate;
	}

	public void setStartWorkDate(Date startWorkDate) {
		this.startWorkDate = startWorkDate;
	}

	public String getNurseCategory() {
		return nurseCategory;
	}

	public void setNurseCategory(String nurseCategory) {
		this.nurseCategory = nurseCategory;
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

	public Integer getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Integer totalIncome) {
		this.totalIncome = totalIncome;
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

	public String getIsEndowmentInsurance() {
		return isEndowmentInsurance;
	}

	public void setIsEndowmentInsurance(String isEndowmentInsurance) {
		this.isEndowmentInsurance = isEndowmentInsurance;
	}

	public Integer getWorkYears() {
		return workYears;
	}

	public void setWorkYears(Integer workYears) {
		this.workYears = workYears;
	}

	public Integer getIsOnjobTrain() {
		return isOnjobTrain;
	}

	public void setIsOnjobTrain(Integer isOnjobTrain) {
		this.isOnjobTrain = isOnjobTrain;
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

	public Integer getIsInVillageClinic() {
		return isInVillageClinic;
	}

	public void setIsInVillageClinic(Integer isInVillageClinic) {
		this.isInVillageClinic = isInVillageClinic;
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

	public Integer getGeneralPractitioner() {
		return generalPractitioner;
	}

	public void setGeneralPractitioner(Integer generalPractitioner) {
		this.generalPractitioner = generalPractitioner;
	}
}
