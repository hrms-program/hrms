package com.paladin.hrms.service.registe.dto;

import java.util.Date;

public class RegisteAgencyInforDTO {

	// 
	private String id;

	// 
	private String personnelId;

	// 拟备案机构名称
	private String agencyName;

	// 机构登记编码
	private String agencyRegisterCode;

	// 单位电话
	private String phone;

	// 邮政编码
	private String postalCode;

	// 地址
	private String address;

	// 申请时间
	private Date applyTime;

	// 有效开始时间
	private Date effectiveStartTime;

	// 有效结束时间
	private Date effectiveEndTime;

	// 机构审核结果
	private Integer agencyCheckStatus;
	
	// 机构审核意思
    private String agencyCheckRemarks;

	// 行政审核结果
	private Integer adminCheckStatus;
	
	// 行政审核意思
    private String adminCheckRemarks;

	// 创建时间
	private Date createTime;

	// 创建人
	private String createUserId;

	// 修改时间
	private Date updateTime;

	// 修改人
	private String updateUserId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAgencyRegisterCode() {
		return agencyRegisterCode;
	}

	public void setAgencyRegisterCode(String agencyRegisterCode) {
		this.agencyRegisterCode = agencyRegisterCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getEffectiveStartTime() {
		return effectiveStartTime;
	}

	public void setEffectiveStartTime(Date effectiveStartTime) {
		this.effectiveStartTime = effectiveStartTime;
	}

	public Date getEffectiveEndTime() {
		return effectiveEndTime;
	}

	public void setEffectiveEndTime(Date effectiveEndTime) {
		this.effectiveEndTime = effectiveEndTime;
	}

	public Integer getAgencyCheckStatus() {
		return agencyCheckStatus;
	}

	public void setAgencyCheckStatus(Integer agencyCheckStatus) {
		this.agencyCheckStatus = agencyCheckStatus;
	}

	public Integer getAdminCheckStatus() {
		return adminCheckStatus;
	}

	public void setAdminCheckStatus(Integer adminCheckStatus) {
		this.adminCheckStatus = adminCheckStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

    public String getAgencyCheckRemarks()
    {
        return agencyCheckRemarks;
    }

    public void setAgencyCheckRemarks(String agencyCheckRemarks)
    {
        this.agencyCheckRemarks = agencyCheckRemarks;
    }

    public String getAdminCheckRemarks()
    {
        return adminCheckRemarks;
    }

    public void setAdminCheckRemarks(String adminCheckRemarks)
    {
        this.adminCheckRemarks = adminCheckRemarks;
    }

}