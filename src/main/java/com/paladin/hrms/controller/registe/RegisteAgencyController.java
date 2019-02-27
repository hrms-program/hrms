package com.paladin.hrms.controller.registe;

import com.paladin.hrms.model.registe.RegisteAgencyInfor;
import com.paladin.hrms.service.registe.RegisteAgencyInforService;
import com.paladin.hrms.service.registe.dto.RegisteAgencyInforQueryDTO;
import com.paladin.hrms.service.registe.dto.RegisteAgencyInforDTO;
import com.paladin.hrms.service.registe.vo.RegisteAgencyInforVO;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.framework.utils.uuid.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/hrms/registe/agency/infor")
public class RegisteAgencyController extends ControllerSupport {

    @Autowired
    private RegisteAgencyInforService registeAgencyInforService;

    @RequestMapping("/index")
    public String index() {
        return "/hrms/org/registe_agency_infor_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(RegisteAgencyInforQueryDTO query) {
        return CommonResponse.getSuccessResponse(registeAgencyInforService.findRegisteAgencyPage(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {     
        return CommonResponse.getSuccessResponse(beanCopy(registeAgencyInforService.get(id), new RegisteAgencyInforVO()));
    }
    
    @RequestMapping("/add")
    public String addInput() {
        return "/hrms/org/registe_agency_infor_edit";
    }

    @RequestMapping("/edit")
    public String detailInput(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return "/hrms/org/registe_agency_infor_edit";
    }
    
    @RequestMapping("/view")
    public String view(@RequestParam String id, Model model) {
        model.addAttribute("info", registeAgencyInforService.get(id));
        model.addAttribute("backUrl","/hrms/registe/agency/infor/index");
        return "/hrms/org/registe_agency_infor_view";
    }
    
    @RequestMapping("/save")
    @ResponseBody
    public Object saveOrUpdate(@Valid RegisteAgencyInforDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        String id = dto.getId();
        boolean isAdd = (id != null && id.length() == 0);
        RegisteAgencyInfor model = beanCopy(dto,isAdd ? new RegisteAgencyInfor() : registeAgencyInforService.get(id));
        if(isAdd){
            id = UUIDUtil.createUUID();
            model.setId(id);
            if (registeAgencyInforService.registeSave(model) > 0) {
                return CommonResponse.getSuccessResponse(beanCopy(registeAgencyInforService.get(id),new RegisteAgencyInforVO()));
            }
        }else{
            model.setAgencyCheckStatus(RegisteAgencyInfor.WAIT_STATUS);
            model.setAdminCheckStatus(RegisteAgencyInfor.WAIT_STATUS);
            if (registeAgencyInforService.updateSelective(model) > 0) {
                return CommonResponse.getSuccessResponse(beanCopy(registeAgencyInforService.get(id),new RegisteAgencyInforVO()));
            }
        }
        return CommonResponse.getFailResponse();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(registeAgencyInforService.removeByPrimaryKey(id));
    }
}