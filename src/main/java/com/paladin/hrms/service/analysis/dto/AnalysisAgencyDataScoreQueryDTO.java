package com.paladin.hrms.service.analysis.dto;

import com.paladin.framework.common.OffsetPage;

public class AnalysisAgencyDataScoreQueryDTO extends OffsetPage{
	private String agencyId;

	private String agencyName;

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
}
