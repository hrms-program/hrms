package com.paladin.hrms.mapper.analysis;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.analysis.AnalysisPersonnelDataScore;
import com.paladin.hrms.service.analysis.dto.AgencyDataSumScoreDTO;
import com.paladin.hrms.service.analysis.dto.AnalysisPersonnelDataScoreQueryDTO;
import com.paladin.hrms.service.analysis.dto.AnalysisPersonnelDataScoreVO;
import com.paladin.hrms.service.analysis.dto.AnalysisPersonnelExport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnalysisPersonnelDataScoreMapper extends CustomMapper<AnalysisPersonnelDataScore>{

	public List<AnalysisPersonnelDataScoreVO> findPersonnelScore(@Param("query") AnalysisPersonnelDataScoreQueryDTO query, @Param("permission") DataPermissionCondition permission);
	
	public int countOfContext(@Param("tableName") String tableName, @Param("personnelId") String personnelId);
	
	public int countOfValidContext(@Param("tableName") String tableName, @Param("personnelId") String personnelId);
	
	public AgencyDataSumScoreDTO sumOfScoreForAgency(@Param("agencyId") String agencyId);

	List<AnalysisPersonnelExport> findPersonnelScoreExport(@Param("query") AnalysisPersonnelDataScoreQueryDTO query, @Param("permission") DataPermissionCondition permission);
}