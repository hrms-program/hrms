package com.paladin.hrms.model.org;

import com.paladin.framework.common.BaseModel;

public class PersonnelModel extends BaseModel{
	
	/** 初始化，待审核状态*/
	public final static int STATUS_DEFAULT = 0;
	/** 有效状态*/
	public final static int STATUS_VALID = 1;
	/** 无效状态*/
	public final static int STATUS_INVALID = 2;

	// 状态
	private Integer status = STATUS_DEFAULT;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
