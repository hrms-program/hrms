package com.paladin.hrms.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.report.pojo.ReportPersonnelInforDTO;
import com.paladin.hrms.controller.report.pojo.ReportPersonnelInforQuery;
import com.paladin.hrms.service.report.ReportPersonnelInforService;

/**
 * <个案信息上报>
 * 
 * @author yanghui
 * @create 2018/11/20 15:01
 */
@Controller
@RequestMapping("/hrms/report/personnel/infor")
public class ReportPersonnelInforController {
	
	@Autowired
	private ReportPersonnelInforService reportPersonnelInforService;

	@RequestMapping("/index")
	@QueryInputMethod(queryClass = ReportPersonnelInforQuery.class)
	public String personnelIndex(String agencyId,Model model) {
		 model.addAttribute("agencyId",agencyId);
		return "/hrms/report/personnel_infor_index";
	}

	/**
     * 查询
     * 
     */
    @RequestMapping("/find")
    @ResponseBody
    @QueryOutputMethod(queryClass = ReportPersonnelInforQuery.class, paramIndex = 0)
    public Object findAll(ReportPersonnelInforQuery query) {
        return CommonResponse.getSuccessResponse(new PageResult<ReportPersonnelInforDTO>(reportPersonnelInforService.searchTableList(query))  );
    }
	
}
