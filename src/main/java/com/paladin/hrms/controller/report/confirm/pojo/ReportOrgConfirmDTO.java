package com.paladin.hrms.controller.report.confirm.pojo;

public class ReportOrgConfirmDTO {
	
	private String id;
	
	private String code;//机构代码
	
	private String name;//机构名称
	
	private String confirmPeople;//上报人
	
	private String confirmTime;//上报时间
	
	private String confirmState;//上报状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfirmPeople() {
		return confirmPeople;
	}

	public void setConfirmPeople(String confirmPeople) {
		this.confirmPeople = confirmPeople;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getConfirmState() {
		return confirmState;
	}

	public void setConfirmState(String confirmState) {
		this.confirmState = confirmState;
	}
	
	
}
