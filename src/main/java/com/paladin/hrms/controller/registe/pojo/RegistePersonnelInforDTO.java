package com.paladin.hrms.controller.registe.pojo;

import java.util.Date;

public class RegistePersonnelInforDTO {
	
	private String id;
	
	private String identificationNo;//身份证号
	
	private String busiId;//业务id	

    private String changeItem;//拟变更注册事项

    private String changeReason;//申请注册变更事由

    private String oldPracticeLevel;//原执业级别

    private String oldPracticeCategory;//原执业类别

    private String oldPracticeRange;//原执业范围

    private String oldPracticeAgency;//原执业机构名称

    private String oldAgencyRegisteCode;//原机构登记编码

    private String oldOrgPhone;//单位电话

    private String oldZipCode;//邮政编码

    private String oldAddress;//地址

    private String plannedPracticeLevel;//拟执业级别

    private String plannedPracticeCategory;//拟执业类别

    private String plannedPracticeRange;//拟执业范围

    private String plannedPracticeAgency;//拟执业机构名称

    private String plannedAgencyRegisteCode;//拟机构登记编码

    private String plannedOrgPhone;//单位电话

    private String plannedZipCode;//邮政编码

    private String plannedAddress;//地址

    private Date applyDate;

    private String agencyCheckStatus;//机构审核结果

    private Date agencyCheckTime;
    
    private String agencyCheckOpinion;//机构审核意见
    
    private String agencyCheckUser;//机构审核人

    private String adminCheckStatus;//行政审核结果

    private Date adminCheckTime;
    
    private String adminCheckOpinion;//行政审核结果
    
    private String adminCheckUser;//行政审核人

    private Byte status;

    private String createUserId;

    private Date createTime;

    private String updateUserId;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    public String getIdentificationNo() {
    	return identificationNo;
    }
    
    public void setIdentificationNo(String identificationNo) {
    	this.identificationNo = identificationNo == null ? null : identificationNo.trim();
    }
    public String getBusiId() {
    	return busiId;
    }
    
    public void setBusiId(String busiId) {
    	this.busiId = busiId == null ? null : busiId.trim();
    }

    public String getChangeItem() {
        return changeItem;
    }

    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem == null ? null : changeItem.trim();
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason == null ? null : changeReason.trim();
    }

    public String getOldPracticeLevel() {
        return oldPracticeLevel;
    }

    public void setOldPracticeLevel(String oldPracticeLevel) {
        this.oldPracticeLevel = oldPracticeLevel == null ? null : oldPracticeLevel.trim();
    }

    public String getOldPracticeCategory() {
        return oldPracticeCategory;
    }

    public void setOldPracticeCategory(String oldPracticeCategory) {
        this.oldPracticeCategory = oldPracticeCategory == null ? null : oldPracticeCategory.trim();
    }

    public String getOldPracticeRange() {
        return oldPracticeRange;
    }

    public void setOldPracticeRange(String oldPracticeRange) {
        this.oldPracticeRange = oldPracticeRange == null ? null : oldPracticeRange.trim();
    }

    public String getOldPracticeAgency() {
        return oldPracticeAgency;
    }

    public void setOldPracticeAgency(String oldPracticeAgency) {
        this.oldPracticeAgency = oldPracticeAgency == null ? null : oldPracticeAgency.trim();
    }

    public String getOldAgencyRegisteCode() {
        return oldAgencyRegisteCode;
    }

    public void setOldAgencyRegisteCode(String oldAgencyRegisteCode) {
        this.oldAgencyRegisteCode = oldAgencyRegisteCode == null ? null : oldAgencyRegisteCode.trim();
    }

    public String getOldOrgPhone() {
        return oldOrgPhone;
    }

    public void setOldOrgPhone(String oldOrgPhone) {
        this.oldOrgPhone = oldOrgPhone == null ? null : oldOrgPhone.trim();
    }

    public String getOldZipCode() {
        return oldZipCode;
    }

    public void setOldZipCode(String oldZipCode) {
        this.oldZipCode = oldZipCode == null ? null : oldZipCode.trim();
    }

    public String getOldAddress() {
        return oldAddress;
    }

    public void setOldAddress(String oldAddress) {
        this.oldAddress = oldAddress == null ? null : oldAddress.trim();
    }

    public String getPlannedPracticeLevel() {
        return plannedPracticeLevel;
    }

    public void setPlannedPracticeLevel(String plannedPracticeLevel) {
        this.plannedPracticeLevel = plannedPracticeLevel == null ? null : plannedPracticeLevel.trim();
    }

    public String getPlannedPracticeCategory() {
        return plannedPracticeCategory;
    }

    public void setPlannedPracticeCategory(String plannedPracticeCategory) {
        this.plannedPracticeCategory = plannedPracticeCategory == null ? null : plannedPracticeCategory.trim();
    }

    public String getPlannedPracticeRange() {
        return plannedPracticeRange;
    }

    public void setPlannedPracticeRange(String plannedPracticeRange) {
        this.plannedPracticeRange = plannedPracticeRange == null ? null : plannedPracticeRange.trim();
    }

    public String getPlannedPracticeAgency() {
        return plannedPracticeAgency;
    }

    public void setPlannedPracticeAgency(String plannedPracticeAgency) {
        this.plannedPracticeAgency = plannedPracticeAgency == null ? null : plannedPracticeAgency.trim();
    }

    public String getPlannedAgencyRegisteCode() {
        return plannedAgencyRegisteCode;
    }

    public void setPlannedAgencyRegisteCode(String plannedAgencyRegisteCode) {
        this.plannedAgencyRegisteCode = plannedAgencyRegisteCode == null ? null : plannedAgencyRegisteCode.trim();
    }

    public String getPlannedOrgPhone() {
        return plannedOrgPhone;
    }

    public void setPlannedOrgPhone(String plannedOrgPhone) {
        this.plannedOrgPhone = plannedOrgPhone == null ? null : plannedOrgPhone.trim();
    }

    public String getPlannedZipCode() {
        return plannedZipCode;
    }

    public void setPlannedZipCode(String plannedZipCode) {
        this.plannedZipCode = plannedZipCode == null ? null : plannedZipCode.trim();
    }

    public String getPlannedAddress() {
        return plannedAddress;
    }

    public void setPlannedAddress(String plannedAddress) {
        this.plannedAddress = plannedAddress == null ? null : plannedAddress.trim();
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getAgencyCheckStatus() {
        return agencyCheckStatus;
    }

    public void setAgencyCheckStatus(String agencyCheckStatus) {
        this.agencyCheckStatus = agencyCheckStatus == null ? null : agencyCheckStatus.trim();
    }

    public Date getAgencyCheckTime() {
        return agencyCheckTime;
    }

    public void setAgencyCheckTime(Date agencyCheckTime) {
        this.agencyCheckTime = agencyCheckTime;
    }

    public String getAdminCheckStatus() {
        return adminCheckStatus;
    }

    public void setAdminCheckStatus(String adminCheckStatus) {
        this.adminCheckStatus = adminCheckStatus == null ? null : adminCheckStatus.trim();
    }

    public Date getAdminCheckTime() {
        return adminCheckTime;
    }

    public void setAdminCheckTime(Date adminCheckTime) {
        this.adminCheckTime = adminCheckTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getAgencyCheckOpinion() {
		return agencyCheckOpinion;
	}

	public void setAgencyCheckOpinion(String agencyCheckOpinion) {
		this.agencyCheckOpinion = agencyCheckOpinion;
	}

	public String getAdminCheckOpinion() {
		return adminCheckOpinion;
	}

	public void setAdminCheckOpinion(String adminCheckOpinion) {
		this.adminCheckOpinion = adminCheckOpinion;
	}

	public String getAgencyCheckUser() {
		return agencyCheckUser;
	}

	public void setAgencyCheckUser(String agencyCheckUser) {
		this.agencyCheckUser = agencyCheckUser;
	}

	public String getAdminCheckUser() {
		return adminCheckUser;
	}

	public void setAdminCheckUser(String adminCheckUser) {
		this.adminCheckUser = adminCheckUser;
	}
	
}
