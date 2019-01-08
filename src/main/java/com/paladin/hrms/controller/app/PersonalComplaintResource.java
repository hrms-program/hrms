package com.paladin.hrms.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.model.complaint.ComplaintPersonnel;
import com.paladin.hrms.service.complaint.ComplaintPersonnelService;
import com.paladin.hrms.service.org.OrgPersonnelService;
import com.paladin.hrms.service.org.dto.OrgPersonnelVO;

/** <app个人申诉>
 * @author 黄伟华
 * @version 2018年11月13日 下午1:42:12 
 */
@Controller
@RequestMapping("/hrms/app/personal/complaint/")
public class PersonalComplaintResource extends ControllerSupport{
    
    @Autowired
    private OrgPersonnelService orgPersonnelService;
    
    @Autowired
    private ComplaintPersonnelService complaintPersonnelService;

    @RequestMapping("/add")
    @ResponseBody
    public Object add(@RequestParam String id){
    	OrgPersonnelVO orgPersonnelVO = beanIncompleteCopy(orgPersonnelService.get(id), new OrgPersonnelVO());
        return CommonResponse.getSuccessResponse(orgPersonnelVO);
    }
    
    @RequestMapping("/edit")
    @ResponseBody
    public Object view(@RequestParam String id){
    	ComplaintPersonnel complaintPersonnel =complaintPersonnelService.get(id);
        return CommonResponse.getSuccessResponse(complaintPersonnel);
    }
    
    @RequestMapping("/check")
    @ResponseBody
    public Object appCheck(@RequestParam String pid){
        return CommonResponse.getSuccessResponse(complaintPersonnelService.getComplaintPersonnel(pid));
    }
    
    
}
