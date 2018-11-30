package com.paladin.hrms.mapper.complaint;

import org.apache.ibatis.annotations.Param;

import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.complaint.ComplaintCheckCount;
import com.paladin.hrms.model.complaint.ComplaintPersonnelArchivesCheckRecord;
import com.paladin.framework.mybatis.CustomMapper;

public interface ComplaintPersonnelArchivesCheckRecordMapper extends CustomMapper<ComplaintPersonnelArchivesCheckRecord>{
	public ComplaintCheckCount indexShowCount(@Param("permission")DataPermissionCondition permission);
}