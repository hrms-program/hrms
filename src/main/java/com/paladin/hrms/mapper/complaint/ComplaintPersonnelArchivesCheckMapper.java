package com.paladin.hrms.mapper.complaint;

import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.complaint.ComplaintPersonnelArchivesCheck;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckVO;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.paladin.framework.mybatis.CustomMapper;

public interface ComplaintPersonnelArchivesCheckMapper extends CustomMapper<ComplaintPersonnelArchivesCheck> {

	public Page<ComplaintPersonnelArchivesCheckVO> searchTableList(@Param("query") ComplaintPersonnelArchivesCheckQueryDTO query,
			@Param("permission") DataPermissionCondition permission);

	public Page<ComplaintPersonnelArchivesCheckVO> searchAppliacationList(@Param("query") ComplaintPersonnelArchivesCheckQueryDTO query,
			@Param("permission") DataPermissionCondition permission);

	public int updateStatusForCheck(@Param("id") String recordId, @Param("type") Integer recordType, @Param("status") Integer status);
	
	public int updateStatus(@Param("id") String recordId, @Param("type") Integer recordType, @Param("status") Integer status);

	Page<ComplaintPersonnelArchivesCheckVO>  searchDistrictAppliacationList(@Param("query") ComplaintPersonnelArchivesCheckQueryDTO query, @Param("permission") DataPermissionCondition permission);
}