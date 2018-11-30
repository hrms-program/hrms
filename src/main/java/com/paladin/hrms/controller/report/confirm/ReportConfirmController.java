package com.paladin.hrms.controller.report.confirm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.utils.Query;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.document.pojo.DocumentInforVO;
import com.paladin.hrms.controller.org.pojo.OrgAgencyQuery;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelBaseDTO;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelQuery;
import com.paladin.hrms.controller.report.pojo.ReportInforDTO;
import com.paladin.hrms.controller.report.pojo.ReportInforQuery;
import com.paladin.hrms.model.org.OrgPersonnel;
import com.paladin.hrms.service.report.ReportInforService;
import com.paladin.hrms.service.report.confirm.ReportConfirmService;

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
        return CommonResponse.getSuccessResponse(new PageResult<ReportInforDTO>(reportInforService.searchOrgList(query))  );
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
		HashMap<String, Object> result = new HashMap<>();
		try{
			reportConfirmService.reportOrg(id);
		}catch(Exception e){
			return CommonResponse.getErrorResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("上报成功");
	}
    
}
