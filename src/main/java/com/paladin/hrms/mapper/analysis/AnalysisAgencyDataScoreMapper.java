package com.paladin.hrms.mapper.analysis;

import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.analysis.AnalysisAgencyDataScore;
import com.paladin.hrms.service.analysis.dto.AnalysisAgencyDataScoreQueryDTO;
import com.paladin.hrms.service.analysis.dto.AnalysisAgencyDataScoreVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.mybatis.CustomMapper;

public interface AnalysisAgencyDataScoreMapper extends CustomMapper<AnalysisAgencyDataScore>{

	public List<AnalysisAgencyDataScoreVO> findAgencyScore(@Param("query") AnalysisAgencyDataScoreQueryDTO query, @Param("permission") DataPermissionCondition permission);

	
}