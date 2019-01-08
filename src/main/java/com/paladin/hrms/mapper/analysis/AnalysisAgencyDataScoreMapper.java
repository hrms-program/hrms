package com.paladin.hrms.mapper.analysis;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.analysis.AnalysisAgencyDataScore;
import com.paladin.hrms.service.analysis.dto.AnalysisAgencyDataScoreQueryDTO;
import com.paladin.hrms.service.analysis.dto.AnalysisAgencyDataScoreVO;
import com.paladin.hrms.service.analysis.dto.AnalysisAgencyExport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnalysisAgencyDataScoreMapper extends CustomMapper<AnalysisAgencyDataScore>{

	public List<AnalysisAgencyDataScoreVO> findAgencyScore(@Param("query") AnalysisAgencyDataScoreQueryDTO query, @Param("permission") DataPermissionCondition permission);


    List<AnalysisAgencyExport> findAgencyScoreExport(@Param("query") AnalysisAgencyDataScoreQueryDTO query, @Param("permission") DataPermissionCondition permission);
}