package com.paladin.hrms.controller.complaint;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.complaint.pojo.ComplaintPersonnelDTO;
import com.paladin.hrms.controller.complaint.pojo.ComplaintPersonnelRecordVO;
import com.paladin.hrms.core.HrmsUserSession;
import com.paladin.hrms.model.complaint.ComplaintPersonnel;
import com.paladin.hrms.model.complaint.ComplaintPersonnelRecord;
import com.paladin.hrms.service.complaint.ComplaintPersonnelRecordService;
import com.paladin.hrms.service.complaint.ComplaintPersonnelService;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelRecordQueryDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <个人信息申诉>
 *
 * @author Huangguochen
 * @create 2018/10/17 9:24
 * @since 1.0.0
 */
@Controller
@RequestMapping("/hrms/complaint/personnel")
public class ComplaintPersonnelController extends ControllerSupport {

	@Autowired
	private SysAttachmentService attachmentService;

	@Autowired
	private ComplaintPersonnelService complaintPersonnelService;

	@Autowired
	private ComplaintPersonnelRecordService complaintPersonnelRecordService;

	@RequestMapping("/index")
	@QueryInputMethod(queryClass = ComplaintPersonnelQueryDTO.class)
	public String index() {
		return "/hrms/complaint/personnel_index";
	}

	@RequestMapping("/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = ComplaintPersonnelQueryDTO.class, paramIndex = 0)
	public Object findAll(ComplaintPersonnelQueryDTO query) {
		return CommonResponse.getSuccessResponse(complaintPersonnelService.findComplaintPersonnelPage(query));
	}

	@RequestMapping("/get/detail")
	@ResponseBody
	public Object getDetail(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(complaintPersonnelService.getComplaintPersonnel(id));
	}

	@RequestMapping("/self/index")
	public String selfIndex() {
		return "/hrms/complaint/personnel_self_index";
	}

	@RequestMapping("/self/get/detail")
	@ResponseBody
	public Object selfDetail() {
		return CommonResponse.getSuccessResponse(complaintPersonnelService.getComplaintPersonnel(HrmsUserSession.getCurrentUserSession().getUserId()));
	}

	@RequestMapping("/save/complaint")
	@ResponseBody
	public Object saveEducationModel(ComplaintPersonnelDTO complaintPersonnelDTO, @RequestParam(required = false) MultipartFile[] attachmentFiles) {
		List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(complaintPersonnelDTO.getAttachments(), attachmentFiles);
		if (attachments != null && attachments.size() > 4) {
			return CommonResponse.getErrorResponse("附件数量不能超过4张");
		}
		complaintPersonnelDTO.setAttachments(attachmentService.splicingAttachmentId(attachments));

		String personnelId = complaintPersonnelDTO.getPersonnelId();
		if (personnelId != null && personnelId.length() > 0) {
			ComplaintPersonnel personnel = complaintPersonnelService.get(personnelId);
			ComplaintPersonnel model = beanCopy(complaintPersonnelDTO, personnel == null ? new ComplaintPersonnel() : personnel);

			if (complaintPersonnelService.applyOrModifyComplaint(model) > 0) {
				return CommonResponse.getSuccessResponse(complaintPersonnelService.getComplaintPersonnel(personnelId));
			}
		}

		return CommonResponse.getFailResponse();
	}

	@RequestMapping("/check")
	public String check(@RequestParam String id, Model model) {
		model.addAttribute("personnelId", id);
		return "/hrms/complaint/personnel_check";
	}

	@RequestMapping("/check/success")
	@ResponseBody
	public Object checkSuccess(@RequestParam String id, @RequestParam(required = false) String illustrate) {
		return CommonResponse.getResponse(complaintPersonnelService.check(id, illustrate, true));
	}

	@RequestMapping("/check/fail")
	@ResponseBody
	public Object checkFail(@RequestParam String id, @RequestParam String illustrate) {
		return CommonResponse.getResponse(complaintPersonnelService.check(id, illustrate, false));
	}

	@RequestMapping("/remove")
	@ResponseBody
	public Object delete(@RequestParam String id) {
		// TODO 只有待审核的才能删除
		return CommonResponse.getResponse(complaintPersonnelService.removeByPrimaryKey(id));
	}

	@RequestMapping("/record/index")
	@QueryInputMethod(queryClass = ComplaintPersonnelRecordQueryDTO.class)
	public String recordIndex() {
		return "/hrms/complaint/personnel_record_index";
	}

	@RequestMapping("/record/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = ComplaintPersonnelRecordQueryDTO.class, paramIndex = 0)
	public Object findRecordAll(ComplaintPersonnelRecordQueryDTO query) {
		return CommonResponse.getSuccessResponse(complaintPersonnelRecordService.findComplaintRecordPage(query));
	}

	@RequestMapping("/record/view")
	public Object recordView(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/hrms/complaint/personnel_record_view";
	}

	@RequestMapping("/record/get")
	@ResponseBody
	public Object recordView(@RequestParam String id) {
		ComplaintPersonnelRecord complaintPersonnelRecord = complaintPersonnelRecordService.get(id);
		if (complaintPersonnelRecord == null) {
			return CommonResponse.getFailResponse("查不到相关记录！");
		}
		return CommonResponse.getSuccessResponse(beanCopy(complaintPersonnelRecord, new ComplaintPersonnelRecordVO()));
	}

}
