package com.paladin.hrms.mapper.complaint;

import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckRecordVO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckVO;
import org.apache.ibatis.annotations.Param;

import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.complaint.ComplaintCheckCount;
import com.paladin.hrms.model.complaint.ComplaintPersonnelArchivesCheckRecord;
import com.paladin.framework.mybatis.CustomMapper;

import java.util.List;

public interface ComplaintPersonnelArchivesCheckRecordMapper extends CustomMapper<ComplaintPersonnelArchivesCheckRecord>{
	public ComplaintCheckCount indexShowCount(@Param("permission")DataPermissionCondition permission);

	List<ComplaintPersonnelArchivesCheckVO> showCheckRecords(@Param("query") ComplaintPersonnelArchivesCheckQueryDTO query, @Param("permission") DataPermissionCondition permission);

    ComplaintPersonnelArchivesCheckRecordVO showCheckRecordByRecordId(@Param("recordId") String recordId, @Param("recordType") Integer recordType);

	ComplaintCheckCount indexDistrictShowCount(@Param("permission") DataPermissionCondition permission);
}