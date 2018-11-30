package com.paladin.hrms.controller.complaint;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.complaint.pojo.ComplaintPositionalTitleConfirmQuery;
import com.paladin.hrms.controller.complaint.pojo.ComplaintPositionalTitleDTO;
import com.paladin.hrms.controller.complaint.pojo.ComplaintPositionalTitleExamineDTO;
import com.paladin.hrms.controller.complaint.pojo.ComplaintPositionalTitleQuery;
import com.paladin.hrms.model.complaint.ComplaintPositionalTitle;
import com.paladin.hrms.model.complaint.ComplaintPositionalTitleRecord;
import com.paladin.hrms.service.complaint.ComplaintPositionalTitleRecordService;
import com.paladin.hrms.service.complaint.ComplaintPositionalTitleService;

/**
 * 职称变更申诉
 * 
 * @author 黄伟华
 * @version 2018年10月18日 下午2:59:06
 */

@Controller
@RequestMapping("/hrms/complaint/positional/")
public class ComplaintPositionalTitleController extends ControllerSupport {

	@Autowired
	private ComplaintPositionalTitleService complaintPositionalTitleService;

	@Autowired
	private ComplaintPositionalTitleRecordService complaintPositionalTitleRecordService;

	@Autowired
	private SysAttachmentService attachmentService;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	@QueryInputMethod(queryClass = ComplaintPositionalTitleQuery.class)
	public String index() {
		return "/hrms/complaint/complaint_positional_title_index";
	}

	/**
	 * 职称变更申诉查询
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/finds")
	@QueryOutputMethod(queryClass = ComplaintPositionalTitleQuery.class, paramIndex = 0)
	@ResponseBody
	public Object find(ComplaintPositionalTitleQuery query) {
		return CommonResponse.getSuccessResponse(complaintPositionalTitleService.complaintPositionalTitleAll(query));
	}

	/**
	 * 申请页面 跳转
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String add(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/hrms/complaint/complaint_positional_title_edit";
	}

	/**
	 * 申请详细信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public Object detail(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(complaintPositionalTitleService.complaintPositionalTitleDetail(id));
	}

	/**
	 * 新增
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object addSave(@Valid ComplaintPositionalTitleDTO dto, @RequestParam(name = "attachmentFiles", required = false) MultipartFile[] files,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}

		List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(dto.getAttachmentId(), files);
		if (attachments != null && attachments.size() > 4) {
			return CommonResponse.getErrorResponse("附件数量不能超过4张");
		}
		dto.setAttachmentId(attachmentService.splicingAttachmentId(attachments));

		ComplaintPositionalTitle title = beanCopy(dto, new ComplaintPositionalTitle());
		if(title.getModifyType() == ComplaintPositionalTitle.MODIFY_TYPE_DELETE){
		 return CommonResponse.getResponse(complaintPositionalTitleService.removeByPrimaryKey(title.getId()));   
		}else{
		return CommonResponse.getResponse(complaintPositionalTitleService.applyOrModifyComplaint(title));	
		}
	}
	
	/**
	 * 职称变更确认 首页
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/confirm/index")
	@QueryInputMethod(queryClass = ComplaintPositionalTitleConfirmQuery.class)
	public String confirmIndex() {
		return "/hrms/complaint/complaint_positional_title_confirm_index";
	}

	/**
	 * 职称变更确认首页 查询
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping("/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = ComplaintPositionalTitleConfirmQuery.class, paramIndex = 0)
	public Object findAll(ComplaintPositionalTitleConfirmQuery query) {
		return CommonResponse.getSuccessResponse(this.complaintPositionalTitleService.complaintPositionalTitleFind(query));
	}

	/**
	 * 职称变更确认 详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	/*
	 * @RequestMapping("/confirm/view") public String confirmView(@RequestParam
	 * String id,Model model) {
	 * model.addAttribute("personal",complaintPositionalTitleService.
	 * complaintPositionalTitleDetail(id)); return
	 * "/hrms/complaint/complaint_positional_title_confirm_view"; }
	 */

	/**
	 * 职称变更确认页面 跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/confirm/edit")
	public String confirmedit(@RequestParam String id, @RequestParam Integer status, Model model) {
		model.addAttribute("status", status);
		model.addAttribute("personal", complaintPositionalTitleService.complaintPositionalTitleDetail(id));
		return "/hrms/complaint/complaint_positional_title_confirm_edit";
	}

	/**
	 * 职称变更确认 审核
	 * 
	 * @param dto
	 * @param bindingResult
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/examine")
	@ResponseBody
	public Object complaintExamine(@Valid ComplaintPositionalTitleExamineDTO dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		ComplaintPositionalTitleRecord record = this.complaintPositionalTitleService.complaintPositionalTitleRecordDetail(dto.getId());
		if (record == null) {
			throw new BusinessException("无相关人员职称变更记录!");
		}
		record.setRemarks(dto.getRemarks());
		return CommonResponse.getResponse(this.complaintPositionalTitleRecordService.complaintExamine(record, dto.getStatus()));
	}

}
