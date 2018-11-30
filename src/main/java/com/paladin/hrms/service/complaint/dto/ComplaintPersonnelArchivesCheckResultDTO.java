package com.paladin.hrms.service.complaint.dto;


public class ComplaintPersonnelArchivesCheckResultDTO  {
    
    private String recordId;
        
    private Integer recordType;
      
    private String illustrate;
	

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public String getIllustrate() {
		return illustrate;
	}

	public void setIllustrate(String illustrate) {
		this.illustrate = illustrate;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

    
}
