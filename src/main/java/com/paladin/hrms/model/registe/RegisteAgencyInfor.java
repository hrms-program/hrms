package com.paladin.hrms.model.registe;

import com.paladin.framework.common.BaseModel;

import java.util.Date;

import javax.persistence.Id;

public class RegisteAgencyInfor extends BaseModel {
    
    public final static int WAIT_STATUS = 0;//待审核
    public final static int SUCCESS_STATUS = 1;//审核成功
    public final static int FAILURE_STATUS = 2;//审核失败
    public final static int REBUT_STATUS = 3;//驳回
	// 
	@Id
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