package com.paladin.hrms.controller.complaint;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.model.complaint.ComplaintPersonnelArchivesCheck;
import com.paladin.hrms.service.complaint.ComplaintPersonnelArchivesCheckService;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckResultDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 人力资源信息审核
 * 
 * @author jisanjie
 * @version 2018年10月31日 下午1:40
 */
@Controller
@RequestMapping("/hrms/complaint/personnel/archives")
public class ComplaintPersonnelArchivesCheckController extends ControllerSupport {

	@Autowired
	private ComplaintPersonnelArchivesCheckService archivesCheckService;

	/**
	 * 跳首页
	 *
	 */
	@RequestMapping("/index")
	@QueryInputMethod(queryClass = ComplaintPersonnelArchivesCheckQueryDTO.class)
	public String index() {
		return "/hrms/complaint/personnel_archives_check_application_amount";
	}

	/**
	 * 首页申请数量汇总
	 *
	 */
	@RequestMapping("/application/amount")
	@ResponseBody
	@QueryOutputMethod(queryClass = ComplaintPersonnelArchivesCheckQueryDTO.class, paramIndex = 0)
	public Object applicationAmount(ComplaintPersonnelArchivesCheckQueryDTO query) {
		return CommonResponse.getSuccessResponse(archivesCheckService.searchTableList(query));
	}

	/**
	 * 跳往个人的申请列表页
	 * 
	 */
	@RequestMapping("/everyone/index")
	public String personnelIndex(String personnelId, Model model) {
		model.addAttribute("personnelId", personnelId);
		return "/hrms/complaint/personnel_archives_check_everyone_index";
	}

	/**
	 * 查询个人的申请列表
	 *
	 */
	@RequestMapping("/everyone/appliacation/list")
	@ResponseBody
	public Object searchAppliacationList(ComplaintPersonnelArchivesCheckQueryDTO query) {
		return CommonResponse.getSuccessResponse(archivesCheckService.searchAppliacationList(query));
	}

	/**
	 * 查看审核的信息
	 * 
	 * @param personnelId
	 * @param checkType
	 * @param model
	 * @return
	 */
	@RequestMapping("/check/view")
	public String checkView(@RequestParam String recordId, @RequestParam Integer recordType, Model model) {
		model.addAttribute("recordId", recordId);
		model.addAttribute("recordType", recordType);
		return "/hrms/complaint/personnel_archives_check_view";
	}

	/**
	 * 批量审核通过
	 */
	@RequestMapping("/check/successes")
	@ResponseBody
	public Object checkSuccesses(@RequestParam(name = "ids[]") String[] ids) {
		int effect = 0;
		if (ids != null) {
			for (String id : ids) {
				ComplaintPersonnelArchivesCheck check = archivesCheckService.get(id);
				try {
					effect += archivesCheckService.check(check, null, true) ? 1 : 0;
				} catch (BusinessException e) {

				}
			}
		}
		return CommonResponse.getSuccessResponse(effect);
	}

	/**
	 * 审核通过
	 */
	@RequestMapping("/check/success")
	@ResponseBody
	public Object checkSuccess(ComplaintPersonnelArchivesCheckResultDTO archivesCheckResult) {
		return CommonResponse.getResponse(archivesCheckService.checkSuccess(archivesCheckResult));
	}

	/**
	 * 审核不通过
	 * 
	 * @param archivesCheckResult
	 * @return
	 */
	@RequestMapping("/check/fail")
	@ResponseBody
	public Object checkFail(ComplaintPersonnelArchivesCheckResultDTO archivesCheckResult) {
		return CommonResponse.getResponse(archivesCheckService.checkFail(archivesCheckResult));
	}

}
