package com.paladin.hrms.controller.report.confirm;

import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.document.pojo.DocumentInforVO;
import com.paladin.hrms.controller.org.pojo.OrgAgencyQuery;
import com.paladin.hrms.controller.report.pojo.ReportInforQuery;
import com.paladin.hrms.service.report.ReportInforService;
import com.paladin.hrms.service.report.confirm.ReportConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/hrms/report/confirm")
public class ReportConfirmController {

	@Autowired
	private ReportConfirmService reportConfirmService;
	@Autowired
	private ReportInforService reportInforService;
	/**
     * 首页
     * 
     * @return
     */
    @RequestMapping("/index")
    @QueryInputMethod(queryClass = DocumentInforVO.class)
    public String index() {
        return "/hrms/report/confirm_index";
    }
    
    /**
     * 查询
     * OrgAgencyQuery
     * @param query
     * @return
     */
    @RequestMapping("/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = OrgAgencyQuery.class, paramIndex = 0)
	public Object findAll(OrgAgencyQuery query) {
		return CommonResponse.getSuccessResponse(reportConfirmService.findOrgCount(query));
	}
    

    /**
     * 上报工作确认查看
     * @return
     */
    @RequestMapping("/check")
	public String check() {
		return "/hrms/report/confirm_check";
	}

	/**
     * 查询
     * 
     */
    @RequestMapping("/findOrg")
    @ResponseBody
    @QueryOutputMethod(queryClass = ReportInforQuery.class, paramIndex = 0)
    public Object findAll(ReportInforQuery query) {
        return CommonResponse.getSuccessResponse(reportInforService.searchOrgList(query));
    }
    
    /**
     * 上报功能
     * @param base
     * @return
     */
    /**
     * 个人上报
     * @param id
     * @return
     */
    @RequestMapping("/reportOrg")
	@ResponseBody
	public Object reportOrg(@RequestParam String id) {//机构id
		try{
			reportConfirmService.reportOrg(id);
		}catch(Exception e){
			return CommonResponse.getErrorResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("上报成功");
	}

    /**
     * 功能描述: <br>
     * 〈上报工作确认统计导出〉
     * @param query
     * @param response
     * @return  void
     * @author  Huangguochen
     * @date  2018/12/19
     */
    @RequestMapping("/export/do")
    public void exportDo(ReportInforQuery query, HttpServletResponse response) {
        try {
            // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("上报工作确认统计表.xlsx", "UTF-8"));
            reportInforService.export2(query, response.getOutputStream());
        } catch (IOException e) {
            throw new BusinessException("导出上报工作确认统计表计失败");
        }
    }
    
}
