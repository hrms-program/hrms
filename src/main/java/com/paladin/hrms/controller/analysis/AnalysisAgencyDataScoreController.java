package com.paladin.hrms.controller.analysis;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.service.analysis.AnalysisAgencyDataScoreService;
import com.paladin.hrms.service.analysis.dto.AnalysisAgencyDataScoreQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

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

    @RequestMapping("/export/do")
    public void exportDo(AnalysisAgencyDataScoreQueryDTO query, HttpServletResponse response) {
        try {
            // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("人员信息完整度评分.xlsx", "UTF-8"));
            analysisAgencyDataScoreService.export(query, response.getOutputStream());
        } catch (IOException e) {
            throw new BusinessException("导出人员信息失败");
        }
    }

}
