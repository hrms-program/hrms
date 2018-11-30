package com.paladin.hrms.service.complaint.dto;

import java.util.Date;


/**
 * 个人信息申请简单显示对象
 * @author TontoZhou
 * @since 2018年11月14日
 */
public class ComplaintPersonnelSimpleVO {
	
	private String personnelId;
	// 姓名
	private String name;
	// 机构名称
	private String agencyName;

	private Integer type;

    private Integer status;

	private Date createTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
