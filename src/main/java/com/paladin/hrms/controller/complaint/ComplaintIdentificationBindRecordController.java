package com.paladin.hrms.controller.complaint;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.complaint.pojo.ComplaintIdentificationBindDTO;
import com.paladin.hrms.controller.complaint.pojo.ComplaintIdentificationBindVO;
import com.paladin.hrms.controller.complaint.pojo.IdentificationBindQuery;
import com.paladin.hrms.core.HrmsUserSession;
import com.paladin.hrms.model.complaint.ComplaintIdentificationBind;
import com.paladin.hrms.model.complaint.ComplaintIdentificationBindRecord;
import com.paladin.hrms.service.complaint.ComplaintIdentificationBindRecordService;
import com.paladin.hrms.service.complaint.ComplaintIdentificationBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * <证件绑定>
 *
 * @author Huangguochen
 * @create 2018/10/27 20:36
 * @since 1.0.0
 */
@Controller
@RequestMapping("/hrms/complaint/zjbind")
public class ComplaintIdentificationBindRecordController  extends ControllerSupport {

    @Autowired
   private ComplaintIdentificationBindService complaintIdentificationBindService;

    @Autowired
    private SysAttachmentService attachmentService;

    @Autowired
    private ComplaintIdentificationBindRecordService complaintIdentificationBindRecordService;

    @RequestMapping("/index")
    @QueryInputMethod(queryClass = IdentificationBindQuery.class)
    public String index() { return "/hrms/complaint/zjbind/identification_bind_index"; }

    @RequestMapping("/self/index")
    public String selfIndex() {
        return "/hrms/complaint/zjbind/identification_bind_self_index";
    }

    @RequestMapping("/self/get/detail")
    @ResponseBody
    public Object selfDetail() {
        return CommonResponse.getSuccessResponse(complaintIdentificationBindService.getComplaintPersonnel(HrmsUserSession.getCurrentUserSession().getUserId()));
    }

    @RequestMapping("/findAgencyPeople")
    @ResponseBody
    @QueryOutputMethod(queryClass = IdentificationBindQuery.class, paramIndex = 0)
    public Object findAgencyPeople(IdentificationBindQuery query ) {
        return CommonResponse.getSuccessResponse(complaintIdentificationBindService.findComplaintPersonnelPage(query));
    }

    @RequestMapping("/add")
    public String add(@RequestParam String pId, Model model) {
        model.addAttribute("personnelId",pId);
        return "/hrms/complaint/zjbind/identification_bind_add";
    }

    @RequestMapping("/get/detail")
    @ResponseBody
    public Object getDetail(@RequestParam String id) {
        return CommonResponse.getSuccessResponse(complaintIdentificationBindService.getComplaintPersonnel(id));
    }

    @RequestMapping("/add/save")
    @ResponseBody
    public Object save(@Valid ComplaintIdentificationBindDTO complaintIdentificationBindDTO, @RequestParam(required = false) MultipartFile[] attachmentFiles, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(complaintIdentificationBindDTO.getAttachments(), attachmentFiles);
        if (attachments != null && attachments.size() > 4) {
            return CommonResponse.getErrorResponse("附件数量不能超过4张");
        }
        complaintIdentificationBindDTO.setAttachments(attachmentService.splicingAttachmentId(attachments));
        String personnelId = complaintIdentificationBindDTO.getPersonnelId();
        if (personnelId != null && personnelId.length() > 0) {
            ComplaintIdentificationBind bind = complaintIdentificationBindService.get(personnelId);
            ComplaintIdentificationBind complaintIdentificationBind = beanCopy(complaintIdentificationBindDTO, bind == null ? new ComplaintIdentificationBind():bind);
            int i =complaintIdentificationBindService.applyOrModifyComplaint(complaintIdentificationBind);
            if ( i > 0) {
                return  CommonResponse.getSuccessResponse(complaintIdentificationBindService.getComplaintPersonnel(complaintIdentificationBind.getPersonnelId()));
            }
        }
        return  CommonResponse.getFailResponse();
    }

    @RequestMapping("/check")
    public String check(@RequestParam String pId,Model model) {
        model.addAttribute("personnelId",pId);
        return "/hrms/complaint/zjbind/identification_bind_check";
    }


    @RequestMapping("/check/success")
    @ResponseBody
    public Object checkSuccess(@RequestParam String id, @RequestParam(required = false) String illustrate) {
        return CommonResponse.getResponse(complaintIdentificationBindService.check(id, illustrate, true));
    }

    @RequestMapping("/check/fail")
    @ResponseBody
    public Object checkFail(@RequestParam String id, @RequestParam String illustrate) {
        return CommonResponse.getResponse(complaintIdentificationBindService.check(id, illustrate, false));
    }


    @RequestMapping("/record/index")
    @QueryInputMethod(queryClass = IdentificationBindQuery.class)
    public String recordIndex(String pId,Model model) {
        model.addAttribute("id",pId);
        return "/hrms/complaint/zjbind/identification_bdjl_index";
    }

    @RequestMapping("/record/find")
    @ResponseBody
    @QueryOutputMethod(queryClass = IdentificationBindQuery.class, paramIndex = 0)
    public Object findRecordAll(IdentificationBindQuery query) {
            return CommonResponse.getSuccessResponse(complaintIdentificationBindRecordService.findComplaintRecordPage(query));
    }

    @RequestMapping("/record/view")
    public String recordView(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return "/hrms/complaint/zjbind/identification_bdjl_view";
    }

    @RequestMapping("/record/get")
    @ResponseBody
    public Object recordView(@RequestParam String id) {
        ComplaintIdentificationBindRecord record = complaintIdentificationBindRecordService.get(id);
        if (record == null) {
            return CommonResponse.getFailResponse("查不到相关记录！");
        }
        return CommonResponse.getSuccessResponse(beanCopy(record, new ComplaintIdentificationBindVO()));
    }

}
