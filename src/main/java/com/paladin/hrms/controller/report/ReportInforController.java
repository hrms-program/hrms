package com.paladin.hrms.controller.report;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelDetailVO;
import com.paladin.hrms.controller.report.pojo.ReportInforDTO;
import com.paladin.hrms.controller.report.pojo.ReportInforQuery;
import com.paladin.hrms.service.org.OrgPersonnelService;
import com.paladin.hrms.service.report.ReportInforService;

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
        return CommonResponse.getSuccessResponse(new PageResult<ReportInforDTO>(reportInforService.searchTableList(query))  );
    }
	
    /**
     * 个人上报
     * @param id
     * @return
     */
    @RequestMapping("/reportPersonal")
	@ResponseBody
	public Object reportPersonal(@RequestParam String personnelId) {
		HashMap<String, Object> result = new HashMap<>();
		try{
			reportInforService.reportPersonal(personnelId);
		}catch(Exception e){
			return CommonResponse.getErrorResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("上报成功");
	}
}
