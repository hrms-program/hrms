package com.paladin.hrms.controller.analysis;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.service.analysis.AnalysisPersonnelDataScoreService;
import com.paladin.hrms.service.analysis.dto.AnalysisPersonnelDataScoreQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

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
	
    @RequestMapping(value = "/execute")
    @ResponseBody
    public Object execute() {
        analysisPersonnelDataScoreService.figureOutScore();
        return CommonResponse.getSuccessResponse("执行成功！");
    }

    @RequestMapping("/export/do")
    public void exportDo(AnalysisPersonnelDataScoreQueryDTO query, HttpServletResponse response) {
        try {
            // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("人员详细信息完整度评分.xlsx", "UTF-8"));
            analysisPersonnelDataScoreService.export(query, response.getOutputStream());
        } catch (IOException e) {
            throw new BusinessException("导出人员详细信息完整度评分失败");
        }
    }


}
