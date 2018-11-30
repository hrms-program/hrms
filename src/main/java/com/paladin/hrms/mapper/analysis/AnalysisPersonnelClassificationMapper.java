package com.paladin.hrms.mapper.analysis;

import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.analysis.AnalysisPersonnelClassification;
import com.paladin.hrms.service.analysis.dto.AnalysisPersonnelClassificationQueryDTO;
import com.paladin.hrms.service.analysis.dto.PersonnelClassificationVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.mybatis.CustomMapper;

public interface AnalysisPersonnelClassificationMapper extends CustomMapper<AnalysisPersonnelClassification> {

	public List<PersonnelClassificationVO> countClassificationGroupByAgency(@Param("query") AnalysisPersonnelClassificationQueryDTO query,
			@Param("permission") DataPermissionCondition permission);

	public PersonnelClassificationVO countClassification(@Param("permission") DataPermissionCondition permission);
}