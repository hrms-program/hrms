package com.paladin.hrms.controller.report.confirm.pojo;

import java.io.Serializable;

public class ReportConfirmVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1402589130720968819L;
	
	private String division;//行政区划
	
	private String shouldReport;//应上报机构数
	
	private String reportedOrg;//已上报机构数
	
	private String noReportedOrg;//未上报机构数

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getShouldReport() {
		return shouldReport;
	}

	public void setShouldReport(String shouldReport) {
		this.shouldReport = shouldReport;
	}

	public String getReportedOrg() {
		return reportedOrg;
	}

	public void setReportedOrg(String reportedOrg) {
		this.reportedOrg = reportedOrg;
	}

	public String getNoReportedOrg() {
		return noReportedOrg;
	}

	public void setNoReportedOrg(String noReportedOrg) {
		this.noReportedOrg = noReportedOrg;
	}
	
	

}
