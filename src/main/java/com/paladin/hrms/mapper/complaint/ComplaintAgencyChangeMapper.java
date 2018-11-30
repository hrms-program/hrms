package com.paladin.hrms.mapper.complaint;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.complaint.ComplaintAgencyChange;
import com.paladin.hrms.service.complaint.dto.ComplaintAgencyAppealsQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintAgencyChangeDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintAgencyChangeSimpleVO;
import com.paladin.hrms.service.complaint.dto.PersonnelForAgencyChangeVO;
import com.paladin.hrms.service.complaint.dto.PersonnelForAgencyQueryDTO;

public interface ComplaintAgencyChangeMapper extends CustomMapper<ComplaintAgencyChange> {

	public List<PersonnelForAgencyChangeVO> findPersonnelForChange(@Param("query") PersonnelForAgencyQueryDTO query,
			@Param("permission") DataPermissionCondition permission);

	public List<ComplaintAgencyChangeSimpleVO> findAgencyChange(@Param("query") ComplaintAgencyAppealsQueryDTO query,
			@Param("permission") DataPermissionCondition permission);

	public ComplaintAgencyChangeDTO getAgencyChangeById(@Param("id") String id);
}