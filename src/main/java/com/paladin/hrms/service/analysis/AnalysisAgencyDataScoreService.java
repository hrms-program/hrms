package com.paladin.hrms.service.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.analysis.AnalysisAgencyDataScoreMapper;
import com.paladin.hrms.model.analysis.AnalysisAgencyDataScore;
import com.paladin.hrms.service.analysis.dto.AnalysisAgencyDataScoreQueryDTO;
import com.paladin.hrms.service.analysis.dto.AnalysisAgencyDataScoreVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;

@Service
public class AnalysisAgencyDataScoreService extends ServiceSupport<AnalysisAgencyDataScore>{
	
	@Autowired
	private AnalysisAgencyDataScoreMapper analysisAgencyDataScoreMapper;
	
	public PageResult<AnalysisAgencyDataScoreVO> findAgencyScore(AnalysisAgencyDataScoreQueryDTO query) {
		Page<AnalysisAgencyDataScoreVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		analysisAgencyDataScoreMapper.findAgencyScore(query, permission);
		return new PageResult<>(page);
	}
	
}