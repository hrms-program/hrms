package com.paladin.hrms.controller.org.pojo;

import java.util.Date;

public class OrgPersonnelJobDTO {

	private String id;
	// 工号
	private String employeeNo;
	// 所在科室
	private String dept;
	// 科室具体名称
	private String deptName;
	// 机构名称
	private String agencyName;
	// 组织机构代码
	private String agencyNo;
	// 从事专业类别
	private Integer major;
	// 行政／业务管理职务
	private Integer duty;
	// 专业技术资格（评或考试）
	private String technicalQualification;
	// 专业技术资格取得时间
	private Date gainDate;
	// 专业技术职务（聘）
	private Integer techPost;
	// 专业技术聘用时间
	private Date employDate;
	// 专业技术职务聘用岗位
	private Integer employPost;
	// 编制情况
	private Integer formation;
	// 年内人员流动情况
	private Integer inorout;
	// 流入/流出时间
	private Date inoroutDate;
	// 是否从事统计信息化业务工作
	private Integer isStatistical;
	// 从事统计信息化业务工作内容
	private String workContent;

	private String attachments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAgencyNo() {
		return agencyNo;
	}

	public void setAgencyNo(String agencyNo) {
		this.agencyNo = agencyNo;
	}

	public Integer getMajor() {
		return major;
	}

	public void setMajor(Integer major) {
		this.major = major;
	}

	public Integer getDuty() {
		return duty;
	}

	public void setDuty(Integer duty) {
		this.duty = duty;
	}

	public String getTechnicalQualification() {
		return technicalQualification;
	}

	public void setTechnicalQualification(String technicalQualification) {
		this.technicalQualification = technicalQualification;
	}

	public Date getGainDate() {
		return gainDate;
	}

	public void setGainDate(Date gainDate) {
		this.gainDate = gainDate;
	}

	public Integer getTechPost() {
		return techPost;
	}

	public void setTechPost(Integer techPost) {
		this.techPost = techPost;
	}

	public Date getEmployDate() {
		return employDate;
	}

	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}

	public Integer getFormation() {
		return formation;
	}

	public void setFormation(Integer formation) {
		this.formation = formation;
	}

	public Integer getInorout() {
		return inorout;
	}

	public void setInorout(Integer inorout) {
		this.inorout = inorout;
	}

	public Date getInoroutDate() {
		return inoroutDate;
	}

	public void setInoroutDate(Date inoroutDate) {
		this.inoroutDate = inoroutDate;
	}

	public Integer getIsStatistical() {
		return isStatistical;
	}

	public void setIsStatistical(Integer isStatistical) {
		this.isStatistical = isStatistical;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public Integer getEmployPost() {
		return employPost;
	}

	public void setEmployPost(Integer employPost) {
		this.employPost = employPost;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}


}
