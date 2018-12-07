package com.paladin.hrms.controller.org;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.org.pojo.OrgAgencyDetailVO;
import com.paladin.hrms.controller.org.pojo.OrgAgencyVO;
import com.paladin.hrms.service.org.OrgAgencyDetailService;
import com.paladin.hrms.service.org.OrgAgencyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * <机构账户统计>
 *
 * @author Huangguochen
 * @create 2018/11/14 9:02
 */
@Controller
@RequestMapping("/hrms/org/agency/account")
public class OrgAgencyAccountController extends ControllerSupport {

    @Autowired
     private OrgAgencyService orgAgencyService;
    
    @Autowired
    private OrgAgencyDetailService orgAgencyDetailService;

    @RequestMapping("/index")
    public String index() { return "/hrms/org/agency_account_index";}

    @RequestMapping("/find")
    @ResponseBody
    public Object find(OffsetPage offsetPage) {
        return CommonResponse.getSuccessResponse(orgAgencyService.findAgencyAccountPage(offsetPage));
    }

    @RequestMapping("/view")
    public String view(@RequestParam(required = false) String id, Model model) {
        model.addAttribute("agencyId", id);
        return "/hrms/org/agency_account_view";
    }

    @RequestMapping("/get/detail")
    @ResponseBody
    public Object getDetail(@RequestParam String id) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("base", beanCopy(orgAgencyService.get(id), new OrgAgencyVO()));
        result.put("detail", beanCopy(orgAgencyDetailService.get(id), new OrgAgencyDetailVO()));

        return CommonResponse.getSuccessResponse(result);
    }
}
