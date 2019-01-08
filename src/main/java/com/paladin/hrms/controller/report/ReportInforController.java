package com.paladin.hrms.controller.report;


import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.report.pojo.ReportInforQuery;
import com.paladin.hrms.service.report.ReportInforService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * <个案信息上报>
 * 
 * @author yanghui
 * @create 2018/11/20 15:01
 */
@Controller
@RequestMapping("/hrms/report/infor")
public class ReportInforController extends ControllerSupport {

	@Autowired
	private ReportInforService reportInforService;
	
	
	@RequestMapping("/index")
	public String index() {
		return "/hrms/report/infor_index";
	}

	/**
     * 查询
     * 
     */
    @RequestMapping("/find")
    @ResponseBody
    @QueryOutputMethod(queryClass = ReportInforQuery.class, paramIndex = 0)
    public Object findAll(ReportInforQuery query) {
        return CommonResponse.getSuccessResponse(reportInforService.searchTableList(query));
    }
	
    /**
     * 个人上报
     * @param id
     * @return
     */
    @RequestMapping("/reportPersonal")
	@ResponseBody
	public Object reportPersonal(@RequestParam String personnelId) {
		try{
			reportInforService.reportPersonal(personnelId);
		}catch(Exception e){
			return CommonResponse.getErrorResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("上报成功");
	}

    @RequestMapping("/export/do")
    public void exportDo(ReportInforQuery query, HttpServletResponse response) {
        try {
            // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("个案信息上报统计.xlsx", "UTF-8"));
            reportInforService.export(query, response.getOutputStream());
        } catch (IOException e) {
            throw new BusinessException("导出个案信息上报统计失败");
        }
    }
}
