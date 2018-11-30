package com.paladin.hrms.model.complaint;

import com.paladin.framework.common.BaseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

public class ComplaintIcExchange extends BaseModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

	private String initiateId;

	private String forExchangeId;

	private Integer dealStatus;
	

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

	public Integer getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}
	
	@Transient
	private String  initiateName;
	
	@Transient
	private String  forExchangeName;
	
	@Transient
	private String  initiateIc;
	   
	@Transient
	private String  forExchangeIc;

      public String getInitiateName() {
            return initiateName;
      }

      public void setInitiateName(String initiateName) {
            this.initiateName = initiateName;
      }

      public String getForExchangeName() {
            return forExchangeName;
      }

      public void setForExchangeName(String forExchangeName) {
            this.forExchangeName = forExchangeName;
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

	
	
	
	

}