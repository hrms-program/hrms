package com.paladin.hrms.model.complaint;

import com.paladin.framework.common.BaseModel;

import javax.persistence.Id;

public class ComplaintModel extends BaseModel {
	
    public final static String COLUMN_FIELD_PERSONNEL_ID = "personnelId";
    public final static String COLUMN_FIELD_STATUS = "status";
    
	
	public final static int STATUS_WAITING = 0;
	//机构审核通过
	public final static int STATUS_ORG_SUCCESS = 1;
	//机构审核不通过
	public final static int STATUS_ORG_FAIL = 2;
	//行政审核通过
	public final static int STATUS_DISTRICT_SUCCESS = 3;
	//行政审核不通过
	public final static int STATUS_DISTRICT_FAIL = 4;

	public static final int STATUS_SUCCESS = 1;

	public static final int STATUS_FAIL = 2;

	@Id
	private String personnelId;
	
	private Integer status = STATUS_WAITING;
	
	private String illustrate;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIllustrate() {
		return illustrate;
	}

	public void setIllustrate(String illustrate) {
		this.illustrate = illustrate;
	}

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}
	
}
