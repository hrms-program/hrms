package com.paladin.hrms.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.complaint.pojo.ComplaintIdentificationBindVO;
import com.paladin.hrms.service.complaint.ComplaintIdentificationBindService;
import com.paladin.hrms.service.org.OrgPersonnelService;
import com.paladin.hrms.service.org.dto.OrgPersonnelVO;

/**  <app证件绑定> 
 * @author 黄伟华
 * @version 2018年11月13日 下午1:40:10 
 */
@Controller
@RequestMapping("/hrms/app/complaint/zjbind/")
public class ComplaintIdentificationBindRecordResource extends ControllerSupport{
        
    @Autowired
    private OrgPersonnelService orgPersonnelService;
    
    @Autowired
    private ComplaintIdentificationBindService complaintIdentificationBindService;
    
    @RequestMapping("/check")
    @ResponseBody
    public Object check(@RequestParam String pid){
    	ComplaintIdentificationBindVO ComplaintIdentificationBindVO = beanIncompleteCopy(complaintIdentificationBindService.appCheck(pid), new ComplaintIdentificationBindVO());
        return CommonResponse.getSuccessResponse(ComplaintIdentificationBindVO);
    } 
    
    @RequestMapping("/add")
    @ResponseBody
    public Object add(@RequestParam String pid){
    	OrgPersonnelVO orgPersonnelVO = beanIncompleteCopy(orgPersonnelService.get(pid), new OrgPersonnelVO());
       return CommonResponse.getSuccessResponse(orgPersonnelVO); 
    }
    
}
