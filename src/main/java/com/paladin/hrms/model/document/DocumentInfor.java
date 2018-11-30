package com.paladin.hrms.model.document;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class DocumentInfor implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7267061162221183676L;

	@Id
	@GeneratedValue(generator="UUID")
	private String id;
	
	private String busiId;
	
	private String relationId;//关联号
	
    private String identificationNo;//身份证号

    private String documentCode;//档案编码

    private String deptCode;//单位编码

    private String section;//部门or科室

    private String age;//年龄

    private String birthPlace;//出生地（具体到县）

    private String address;//联系地址

    private String personnelCompilation;//人事编制,取自数据字典personnel_compilation（人事代理，事业编制，参公事业，合同制...）
    
    private String compilationDept;//编码所在部门（按公章全称录入）

    private String personType;//人员性质,取自数据字person_type（公务员，参公干部，参公工人，事业干部，事业工人）

    private String isSecondment;//是否借出

    private String secondDeptCode;//借调（入）单位

    private String secondSection;//借调（入）部门/科室

    private String isLeader;//是否领导职务

    private String isMember;//是否班子成员

    private String isReserveCadres;//是否储备干部

    private String retiredDate;//退线时间

    private String initialEducation;//初始学历 
    
    private String initialEducationDesc;//初始学历（其他）

    private String highestEducation;//最高学历

    private String governmentTitle;//党政职务

    private String orgTime;//服务本机构时间
    
    private String jobLevel;//现职务层次
    
    private String managementLevel;//干部类别（市管干部、部管干部、委管干部、其他）

    private String health;//健康状况

    private String profession;//熟悉专业，有何特色

    private String professionalSort;//专业类别，来自数据字典professional_sort

    private String professionLevel;//专业级别

    private String healthTechnicalTitle;//卫技类专业技术职称

    private String nonhealthTechnicalTitle;//非卫技类专业技术职称

    private String technicalPosition;//个人最高级别专业技术职务
    
    private String technicalPositionDesc;//个人最高级别专业技术职务（其他）

    private String technicalTime;//获得最高专业技术职务时间

    private String hirepositionTime;//聘用最高级别专业技术职务时间

    private String isGeneral;//是否全科医生:0否1是

    private String workStatus;//在岗情况（在岗，规培。。。）
    
    private String salaryLevel;//执行工资标准

    private String salaryGrade;//执行工资档次

    private String workYear;//退休时工龄

    private String retirementPayScale;//退休时工资等级

    private String createAt;

    private String createId;

    private String updateAt;

    private String updateId;

    private String state;//状态

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}
	
	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getIdentificationNo() {
        return identificationNo;
    }

    public void setIdentificationNo(String identificationNo) {
        this.identificationNo = identificationNo == null ? null : identificationNo.trim();
    }

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode == null ? null : documentCode.trim();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section == null ? null : section.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace == null ? null : birthPlace.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPersonnelCompilation() {
        return personnelCompilation;
    }

    public void setPersonnelCompilation(String personnelCompilation) {
        this.personnelCompilation = personnelCompilation == null ? null : personnelCompilation.trim();
    }

    public String getCompilationDept() {
        return compilationDept;
    }

    public void setCompilationDept(String compilationDept) {
        this.compilationDept = compilationDept == null ? null : compilationDept.trim();
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType == null ? null : personType.trim();
    }

    public String getIsSecondment() {
        return isSecondment;
    }

    public void setIsSecondment(String isSecondment) {
        this.isSecondment = isSecondment == null ? null : isSecondment.trim();
    }

    public String getSecondDeptCode() {
        return secondDeptCode;
    }

    public void setSecondDeptCode(String secondDeptCode) {
        this.secondDeptCode = secondDeptCode == null ? null : secondDeptCode.trim();
    }

    public String getSecondSection() {
        return secondSection;
    }

    public void setSecondSection(String secondSection) {
        this.secondSection = secondSection == null ? null : secondSection.trim();
    }

    public String getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader == null ? null : isLeader.trim();
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember == null ? null : isMember.trim();
    }

    public String getIsReserveCadres() {
        return isReserveCadres;
    }

    public void setIsReserveCadres(String isReserveCadres) {
        this.isReserveCadres = isReserveCadres == null ? null : isReserveCadres.trim();
    }

    public String getRetiredDate() {
        return retiredDate;
    }

    public void setRetiredDate(String retiredDate) {
        this.retiredDate = retiredDate;
    }

    public String getInitialEducation() {
        return initialEducation;
    }

    public void setInitialEducation(String initialEducation) {
        this.initialEducation = initialEducation == null ? null : initialEducation.trim();
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(String highestEducation) {
        this.highestEducation = highestEducation == null ? null : highestEducation.trim();
    }

    public String getGovernmentTitle() {
        return governmentTitle;
    }

    public void setGovernmentTitle(String governmentTitle) {
        this.governmentTitle = governmentTitle == null ? null : governmentTitle.trim();
    }

    public String getOrgTime() {
        return orgTime;
    }

    public void setOrgTime(String orgTime) {
        this.orgTime = orgTime;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel == null ? null : jobLevel.trim();
    }

    public String getManagementLevel() {
        return managementLevel;
    }

    public void setManagementLevel(String managementLevel) {
        this.managementLevel = managementLevel == null ? null : managementLevel.trim();
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health == null ? null : health.trim();
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public String getProfessionalSort() {
        return professionalSort;
    }

    public void setProfessionalSort(String professionalSort) {
        this.professionalSort = professionalSort == null ? null : professionalSort.trim();
    }

    public String getProfessionLevel() {
        return professionLevel;
    }

    public void setProfessionLevel(String professionLevel) {
        this.professionLevel = professionLevel == null ? null : professionLevel.trim();
    }

    public String getHealthTechnicalTitle() {
        return healthTechnicalTitle;
    }

    public void setHealthTechnicalTitle(String healthTechnicalTitle) {
        this.healthTechnicalTitle = healthTechnicalTitle == null ? null : healthTechnicalTitle.trim();
    }

    public String getNonhealthTechnicalTitle() {
        return nonhealthTechnicalTitle;
    }

    public void setNonhealthTechnicalTitle(String nonhealthTechnicalTitle) {
        this.nonhealthTechnicalTitle = nonhealthTechnicalTitle == null ? null : nonhealthTechnicalTitle.trim();
    }

    public String getTechnicalPosition() {
        return technicalPosition;
    }

    public void setTechnicalPosition(String technicalPosition) {
        this.technicalPosition = technicalPosition == null ? null : technicalPosition.trim();
    }

    public String getTechnicalTime() {
        return technicalTime;
    }

    public void setTechnicalTime(String technicalTime) {
        this.technicalTime = technicalTime;
    }

    public String getHirepositionTime() {
        return hirepositionTime;
    }

    public void setHirepositionTime(String hirepositionTime) {
        this.hirepositionTime = hirepositionTime;
    }

    public String getIsGeneral() {
        return isGeneral;
    }

    public void setIsGeneral(String isGeneral) {
        this.isGeneral = isGeneral == null ? null : isGeneral.trim();
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus == null ? null : workStatus.trim();
    }

    public String getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(String salaryLevel) {
        this.salaryLevel = salaryLevel == null ? null : salaryLevel.trim();
    }

    public String getSalaryGrade() {
        return salaryGrade;
    }

    public void setSalaryGrade(String salaryGrade) {
        this.salaryGrade = salaryGrade == null ? null : salaryGrade.trim();
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear == null ? null : workYear.trim();
    }

    public String getRetirementPayScale() {
        return retirementPayScale;
    }

    public void setRetirementPayScale(String retirementPayScale) {
        this.retirementPayScale = retirementPayScale == null ? null : retirementPayScale.trim();
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getInitialEducationDesc() {
		return initialEducationDesc;
	}

	public void setInitialEducationDesc(String initialEducationDesc) {
		this.initialEducationDesc = initialEducationDesc;
	}

	public String getTechnicalPositionDesc() {
		return technicalPositionDesc;
	}

	public void setTechnicalPositionDesc(String technicalPositionDesc) {
		this.technicalPositionDesc = technicalPositionDesc;
	}
	
}
