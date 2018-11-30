package com.paladin.hrms.controller.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.service.analysis.AnalysisPersonnelDataScoreService;
import com.paladin.hrms.service.analysis.dto.AnalysisPersonnelDataScoreQueryDTO;

@Controller
@RequestMapping("/hrms/analysis/personnel/data/score")
public class AnalysisPersonnelDataScoreController extends ControllerSupport {

	@Autowired
	private AnalysisPersonnelDataScoreService analysisPersonnelDataScoreService;

	
	@RequestMapping(value = "/find")
	@ResponseBody
	public Object find(AnalysisPersonnelDataScoreQueryDTO query) {
		return CommonResponse.getSuccessResponse(analysisPersonnelDataScoreService.findPersonnelScore(query));
	}

}
