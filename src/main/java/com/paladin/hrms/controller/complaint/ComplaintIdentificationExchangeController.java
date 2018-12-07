package com.paladin.hrms.controller.complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.model.complaint.ComplaintIdentificationExchange;
import com.paladin.hrms.service.complaint.ComplaintIdentificationExchangeService;
import com.paladin.hrms.service.complaint.dto.ComplaintIdentificationExchangeQueryDTO;

/**
 * 证件置换
 * 
 * @author TontoZhou
 * @since 2018年12月3日
 */
@Controller
@RequestMapping("/hrms/complaint/identification/exchange")
public class ComplaintIdentificationExchangeController extends ControllerSupport {

	@Autowired
	private ComplaintIdentificationExchangeService complaintIdentificationExchangeService;

	/**
	 * 数据列表页面
	 * @return
	 */
	@RequestMapping("/index")
	@QueryInputMethod(queryClass = ComplaintIdentificationExchangeQueryDTO.class)
	public String index() {
		return "/hrms/complaint/complaint_identification_exchange_index";
	}

	/**
	 * 查找所有置换记录
	 * @param query
	 * @return
	 */
	@RequestMapping("/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = ComplaintIdentificationExchangeQueryDTO.class, paramIndex = 0)
	public Object findAll(ComplaintIdentificationExchangeQueryDTO query) {
		return CommonResponse.getSuccessResponse(complaintIdentificationExchangeService.findComplaintPage(query));
	}
	
	/**
	 * 新增申请
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "/hrms/complaint/complaint_identification_exchange_add";
	}
	
	
	/**
	 * 置换记录详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/find/personnel")
	@ResponseBody
	public Object findPersonnel(@RequestParam String identificationNo) {
		return CommonResponse.getSuccessResponse(complaintIdentificationExchangeService.getComplaintPersonnel(identificationNo));
	}
	
	/**
	 * 置换记录详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object save(@RequestParam String fromPersonnelId, @RequestParam String toPersonnelId) {
		ComplaintIdentificationExchange exchange = new ComplaintIdentificationExchange();
		exchange.setFromPersonnelId(fromPersonnelId);
		exchange.setToPersonnelId(toPersonnelId);
		return CommonResponse.getSuccessResponse(complaintIdentificationExchangeService.save(exchange));
	}
	
	/**
	 * 审核页面
	 * @return
	 */
	@RequestMapping("/check")
	public String check(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/hrms/complaint/complaint_identification_exchange_check";
	}

	/**
	 * 置换记录详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/detail")
	@ResponseBody
	public Object getDetail(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(complaintIdentificationExchangeService.getComplaintDetail(id));
	}
	
	
	/**
	 * 审核页面
	 * @return
	 */
	@RequestMapping("/check/success")
	@ResponseBody
	public Object checkSuccess(@RequestParam String id, @RequestParam(required = false) String illustrate) {
		return CommonResponse.getResponse(complaintIdentificationExchangeService.check(id, illustrate, true));
	}
	
	/**
	 * 审核失败
	 * @return
	 */
	@RequestMapping("/check/fail")
	@ResponseBody
	public Object checkFail(@RequestParam String id, @RequestParam String illustrate) {
		return CommonResponse.getResponse(complaintIdentificationExchangeService.check(id, illustrate, false));

	}
}
