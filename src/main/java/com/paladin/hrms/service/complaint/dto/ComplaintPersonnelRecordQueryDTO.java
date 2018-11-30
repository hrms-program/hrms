package com.paladin.hrms.service.complaint.dto;

import com.paladin.framework.common.OffsetPage;

public class ComplaintPersonnelRecordQueryDTO extends OffsetPage{
	
	private String personnelId;
	
	private String name;
	
	private String agencyId;
	
	private String agencyName;
	
	private Integer type;

	private Integer result;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}
}
