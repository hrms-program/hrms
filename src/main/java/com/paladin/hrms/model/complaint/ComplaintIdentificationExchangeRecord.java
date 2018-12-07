package com.paladin.hrms.model.complaint;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.paladin.framework.common.BaseModel;

public class ComplaintIdentificationExchangeRecord extends BaseModel {

	//
	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	// 置换发起人员ID
	private String fromPersonnelId;

	// 置换发起人员证件类型
	private Integer fromIdentificationType;
	
	// 置换发起人员证件号
	private String fromIdentificationNo;

	// 置换目标人员ID
	private String toPersonnelId;
	
	// 置换目标人员证件类型
	private Integer toIdentificationType;

	// 置换目标人员证件号
	private String toIdentificationNo;

	// 处理结果
	private Integer result;

	// 处理意见
	private String illustrate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromPersonnelId() {
		return fromPersonnelId;
	}

	public void setFromPersonnelId(String fromPersonnelId) {
		this.fromPersonnelId = fromPersonnelId;
	}

	public String getFromIdentificationNo() {
		return fromIdentificationNo;
	}

	public void setFromIdentificationNo(String fromIdentificationNo) {
		this.fromIdentificationNo = fromIdentificationNo;
	}

	public String getToPersonnelId() {
		return toPersonnelId;
	}

	public void setToPersonnelId(String toPersonnelId) {
		this.toPersonnelId = toPersonnelId;
	}

	public String getToIdentificationNo() {
		return toIdentificationNo;
	}

	public void setToIdentificationNo(String toIdentificationNo) {
		this.toIdentificationNo = toIdentificationNo;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getIllustrate() {
		return illustrate;
	}

	public void setIllustrate(String illustrate) {
		this.illustrate = illustrate;
	}

	public Integer getFromIdentificationType() {
		return fromIdentificationType;
	}

	public void setFromIdentificationType(Integer fromIdentificationType) {
		this.fromIdentificationType = fromIdentificationType;
	}

	public Integer getToIdentificationType() {
		return toIdentificationType;
	}

	public void setToIdentificationType(Integer toIdentificationType) {
		this.toIdentificationType = toIdentificationType;
	}

}