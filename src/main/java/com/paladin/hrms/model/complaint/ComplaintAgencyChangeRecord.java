package com.paladin.hrms.model.complaint;

import com.paladin.framework.common.BaseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ComplaintAgencyChangeRecord extends BaseModel {

    @Id
    @GeneratedValue(generator = "UUID")
	private String id;

	private String personnelId;

	private String agencyId;

	private String targetAgencyId;

	private String illustrate;
	
	private Integer result;

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

	public String getAgencyId()
    {
        return agencyId;
    }

    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }

    public String getTargetAgencyId()
    {
        return targetAgencyId;
    }

    public void setTargetAgencyId(String targetAgencyId)
    {
        this.targetAgencyId = targetAgencyId;
    }

    public String getIllustrate() {
		return illustrate;
	}

	public void setIllustrate(String illustrate) {
		this.illustrate = illustrate;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	
}