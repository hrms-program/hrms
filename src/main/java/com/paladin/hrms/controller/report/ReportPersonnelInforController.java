package com.paladin.hrms.controller.report;

import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.report.pojo.ReportPersonnelInforDTO;
import com.paladin.hrms.controller.report.pojo.ReportPersonnelInforQuery;
import com.paladin.hrms.service.report.ReportPersonnelInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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


    /**
     * 功能描述: <br>
     * 〈个案详细信息上报统计导出〉
     * @param query
     * @param response
     * @author  Huangguochen
     * @date  2018/12/19
     */
    @RequestMapping("/export/do")
    public void exportDo(ReportPersonnelInforQuery query, HttpServletResponse response) {
        try {
            // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("个案详细信息上报统计.xlsx", "UTF-8"));
            reportPersonnelInforService.export(query, response.getOutputStream());
        } catch (IOException e) {
            throw new BusinessException("导出个案信息上报统计失败");
        }
    }
	
}
