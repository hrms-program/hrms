package com.paladin.hrms.controller.syst.pojo;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class SysAnnouncementQuery extends OffsetPage{
	
	private String title;
	private Integer type;
	@QueryCondition(type = QueryType.LIKE)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@QueryCondition(type = QueryType.EQUAL)
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
