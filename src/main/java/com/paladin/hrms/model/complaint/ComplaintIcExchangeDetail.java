package com.paladin.hrms.model.complaint;

import com.paladin.framework.common.BaseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ComplaintIcExchangeDetail extends BaseModel {

    @Id
    @GeneratedValue(generator = "UUID")
	private String id;

	private String initiateId;

	private String forExchangeId;

	private String initiateIc;

	private String forExchangeIc;

	private Integer dealResult;

	private String dealIllustration;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInitiateId() {
		return initiateId;
	}

	public void setInitiateId(String initiateId) {
		this.initiateId = initiateId;
	}

	public String getForExchangeId() {
		return forExchangeId;
	}

	public void setForExchangeId(String forExchangeId) {
		this.forExchangeId = forExchangeId;
	}

	public String getInitiateIc() {
		return initiateIc;
	}

	public void setInitiateIc(String initiateIc) {
		this.initiateIc = initiateIc;
	}

	public String getForExchangeIc() {
		return forExchangeIc;
	}

	public void setForExchangeIc(String forExchangeIc) {
		this.forExchangeIc = forExchangeIc;
	}

	public Integer getDealResult() {
		return dealResult;
	}

	public void setDealResult(Integer dealResult) {
		this.dealResult = dealResult;
	}

	public String getDealIllustration() {
		return dealIllustration;
	}

	public void setDealIllustration(String dealIllustration) {
		this.dealIllustration = dealIllustration;
	}

}