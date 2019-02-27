package com.paladin.hrms.controller.registe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.core.HrmsUserSession;
import com.paladin.hrms.model.registe.RegisteAgencyInfor;
import com.paladin.hrms.service.registe.RegisteAgencyInforService;
import com.paladin.hrms.service.registe.dto.RegisteAgencyInforQueryDTO;
import com.paladin.hrms.service.registe.vo.RegisteAgencyInforVO;

/**
 * 备案机构审核
 * 机构审核+行政审核
 * @author 
 *
 */
@Controller
@RequestMapping("/hrms/registe/agency/check")
public class RegisteAgencyCheckController extends ControllerSupport{
    
    @Autowired
    private RegisteAgencyInforService registeAgencyInforService;

    @RequestMapping("/index/agency")
    public String agencyIndex() {return "/hrms/org/registe_agency_infor_check_index_agency";}
    
    @RequestMapping("/index/admin")
    public String adminIndex() {return "/hrms/org/registe_agency_infor_check_index_admin";}
    
    @RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(RegisteAgencyInforQueryDTO query) {
        return CommonResponse.getSuccessResponse(registeAgencyInforService.findRegisteAgencyCheckPage(query));
    }

    @RequestMapping("/view")
    public String view(@RequestParam String id, Model model) {
        if(HrmsUserSession.getCurrentUserSession().isAgencyManager()){
            model.addAttribute("backUrl","/hrms/registe/agency/check/index/agency"); 
        }
        if(HrmsUserSession.getCurrentUserSession().isDistrictManager()){
            model.addAttribute("backUrl","/hrms/registe/agency/check/index/admin");
        }
        model.addAttribute("info", registeAgencyInforService.get(id));
        return "/hrms/org/registe_agency_infor_view";
    }
    
    @RequestMapping("/examine")
    public String checkView(@RequestParam String id,Model model) {
        model.addAttribute("info", beanCopy(registeAgencyInforService.get(id), new RegisteAgencyInforVO()));
        return "/hrms/org/registe_agency_infor_check_agency";
    }
    
    @RequestMapping("/success")
    @ResponseBody
    public Object checkSuccess(@RequestParam String id, @RequestParam(required = false) String agencyCheckRemarks) {
        return CommonResponse.getResponse(registeAgencyInforService.check(id, agencyCheckRemarks, RegisteAgencyInfor.SUCCESS_STATUS));
    }

    @RequestMapping("/fail")
    @ResponseBody
    public Object checkFail(@RequestParam String id, @RequestParam String agencyCheckRemarks) {
        return CommonResponse.getResponse(registeAgencyInforService.check(id, agencyCheckRemarks, RegisteAgencyInfor.FAILURE_STATUS));
    }
    
    @RequestMapping("/rebut")
    @ResponseBody
    public Object checkRebut(@RequestParam String id, @RequestParam String agencyCheckRemarks) {
        return CommonResponse.getResponse(registeAgencyInforService.check(id, agencyCheckRemarks, RegisteAgencyInfor.REBUT_STATUS));
    }
    
}
