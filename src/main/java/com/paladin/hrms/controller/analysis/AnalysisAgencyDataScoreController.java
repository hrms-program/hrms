package com.paladin.hrms.controller.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.service.analysis.AnalysisAgencyDataScoreService;
import com.paladin.hrms.service.analysis.dto.AnalysisAgencyDataScoreQueryDTO;

@Controller
@RequestMapping("/hrms/analysis/agency/data/score")
public class AnalysisAgencyDataScoreController extends ControllerSupport {

	@Autowired
	private AnalysisAgencyDataScoreService analysisAgencyDataScoreService;

	@RequestMapping(value = "/index")
	@QueryInputMethod(queryClass = AnalysisAgencyDataScoreQueryDTO.class)
	public String index() {
		return "/hrms/analysis/data_score_index";
	}

	@RequestMapping(value = "/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = AnalysisAgencyDataScoreQueryDTO.class, paramIndex = 0)
	public Object find(AnalysisAgencyDataScoreQueryDTO query) {
		return CommonResponse.getSuccessResponse(analysisAgencyDataScoreService.findAgencyScore(query));
	}

}
