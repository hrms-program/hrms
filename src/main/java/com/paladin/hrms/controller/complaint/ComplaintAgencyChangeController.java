package com.paladin.hrms.controller.complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.model.complaint.ComplaintAgencyChange;
import com.paladin.hrms.service.complaint.ComplaintAgencyChangeService;
import com.paladin.hrms.service.complaint.dto.ComplaintAgencyAppealsQueryDTO;

/**
 * 机构变更确认
 * 
 * @author 黄伟华
 * @version 2018年11月2日 下午2:16:37
 */
@Controller
@RequestMapping("/hrms/complaint/agency/confirm/")
public class ComplaintAgencyChangeController extends ControllerSupport {

	@Autowired
	ComplaintAgencyChangeService complaintAgencyChangeService;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "/hrms/complaint/complaint_agency_confirm_index";
	}

	/**
	 * 查询
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = ComplaintAgencyAppealsQueryDTO.class, paramIndex = 0)
	public Object find(ComplaintAgencyAppealsQueryDTO query) {
		return CommonResponse.getSuccessResponse(complaintAgencyChangeService.findAgencyChangePage(query));
	}

	/**
	 * 是否通过操作
	 * 
	 * @param personnelId
	 * @param illustrate
	 * @param result
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object agencyConfirm(@RequestParam String personnelId, @RequestParam String illustrate, @RequestParam Integer result) {
		return CommonResponse
				.getResponse(complaintAgencyChangeService.confirmCheck(personnelId, illustrate, result == ComplaintAgencyChange.STATUS_SUCCESS ? true : false));
	}

}
