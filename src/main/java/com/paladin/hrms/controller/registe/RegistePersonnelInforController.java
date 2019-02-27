package com.paladin.hrms.controller.registe;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.org.pojo.OrgAgencyManagerDTO;
import com.paladin.hrms.controller.registe.pojo.RegistePersonnelInforDTO;
import com.paladin.hrms.controller.registe.pojo.RegistePersonnelInforQuery;
import com.paladin.hrms.model.org.OrgAgencyManager;
import com.paladin.hrms.model.registe.RegistePersonnelInfor;
import com.paladin.hrms.service.registe.RegistePersonnelInforService;

/**
 * 备案信息申请
 * @author FM
 *
 */
@Controller
@RequestMapping("/hrms/registe/personnel/infor")
public class RegistePersonnelInforController extends ControllerSupport {
	@Autowired
	private RegistePersonnelInforService registePersonnelInforService;

	@RequestMapping("/index")
	public String index() {
		return "/hrms/org/registe_personnel_infor_index";
	}
	/**
     * 查询
     */
    @RequestMapping("/list")
    @ResponseBody
    @QueryOutputMethod(queryClass = RegistePersonnelInforQuery.class, paramIndex = 0)
    public Object list(RegistePersonnelInforQuery query) {
        return CommonResponse.getSuccessResponse(registePersonnelInforService.searchTableList(query));
    }
    /**
     * 详情
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/view")
    public String view(@RequestParam String id,Model model){
        RegistePersonnelInfor registePersonnelInfor = registePersonnelInforService.get(id);
        if(registePersonnelInfor == null){
            throw new BusinessException("查看的信息变更申请不存在！");
        }
        model.addAttribute("registePersonnelInfor", registePersonnelInfor);
        return "/hrms/org/registe_personnel_infor_view";
    }
    
    /**
     * 新增
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model,RegistePersonnelInfor registePersonnelInfor){
        model.addAttribute("registePersonnelInfor", registePersonnelInfor);
        return "/hrms/org/registe_personnel_infor_edit";
    }
    
    /**
     * 修改
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam String id,Model model){
    	RegistePersonnelInfor registePersonnelInfor = registePersonnelInforService.get(id);
        if(registePersonnelInfor == null){
            throw new BusinessException("修改的信息变更申请记录不存在！");
        }
        model.addAttribute("registePersonnelInfor", registePersonnelInfor);
        return "/hrms/org/registe_personnel_infor_edit";
    }
    
    /**
     * 保存
     * 
     * @param id
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Object addSave(@Valid RegistePersonnelInforDTO dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        RegistePersonnelInfor model=beanIncompleteCopy(dto,new RegistePersonnelInfor());
        
        return CommonResponse.getResponse(registePersonnelInforService.saveOrUpdate(model));
    }
    /**
     * 删除
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public Object remove(@RequestParam String id,Model model){
    	return CommonResponse.getResponse(registePersonnelInforService.remove(id));
    }
    
}
