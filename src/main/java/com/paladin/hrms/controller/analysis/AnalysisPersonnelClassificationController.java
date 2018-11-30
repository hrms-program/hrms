package com.paladin.hrms.controller.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.service.analysis.AnalysisPersonnelClassificationService;
import com.paladin.hrms.service.analysis.dto.AnalysisPersonnelClassificationQueryDTO;

@Controller
@RequestMapping("/hrms/analysis/personnel/classification")
public class AnalysisPersonnelClassificationController extends ControllerSupport{
	
	@Autowired
	private AnalysisPersonnelClassificationService analysisPersonnelClassificationService;
	
	@RequestMapping(value = "/index")
	@QueryInputMethod(queryClass = AnalysisPersonnelClassificationQueryDTO.class)
	public String index() {
		return "/hrms/analysis/personnel_classification_index";
	}
	
	@RequestMapping(value = "/find/agency")
	@ResponseBody
	@QueryOutputMethod(queryClass = AnalysisPersonnelClassificationQueryDTO.class, paramIndex = 0)
	public Object findByAgency(AnalysisPersonnelClassificationQueryDTO query) {
		return CommonResponse.getSuccessResponse(analysisPersonnelClassificationService.findPersonnelClassificationOfAgency(query));
	}
	
	
	@RequestMapping(value = "/find/district")
	@ResponseBody
	public Object findByDistrict() {
		return CommonResponse.getSuccessResponse(analysisPersonnelClassificationService.findPersonnelClassificationOfDistrict());
	}
}
