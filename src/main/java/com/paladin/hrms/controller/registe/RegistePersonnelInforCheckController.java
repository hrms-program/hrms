package com.paladin.hrms.controller.registe;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.registe.pojo.RegistePersonnelInforDTO;
import com.paladin.hrms.model.registe.RegistePersonnelInfor;
import com.paladin.hrms.service.registe.RegistePersonnelInforService;
/**
 * 备案信息 审核
 * 行政审核
 * @author FM
 *
 */
@Controller
@RequestMapping("/hrms/registe/personnel/infor/check")
public class RegistePersonnelInforCheckController extends ControllerSupport{

	@Autowired
	private RegistePersonnelInforService registePersonnelInforService;

	//机构审核列表
	@RequestMapping("/index/agency")
	public String indexAgency() {
		return "/hrms/org/registe_personnel_infor_check_index_agency";
	}
	
	//行政审核列表
	@RequestMapping("/index/admin")
	public String indexAdmin(){
		return "/hrms/org/registe_personnel_infor_check_index_admin";
	}
	
	/**
     * 机构审核页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/agency")
    public String agency(@RequestParam String id,Model model){
    	RegistePersonnelInfor registePersonnelInfor = registePersonnelInforService.get(id);
        if(registePersonnelInfor == null){
            throw new BusinessException("备案信息变更申请记录不存在！");
        }
        model.addAttribute("registePersonnelInfor", registePersonnelInfor);
        return "/hrms/org/registe_personnel_infor_check_agency";
    }
	
    /**
     * 机构审核结果保存
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/agencycheck",method = {RequestMethod.POST})
    public Object agencycheck(RegistePersonnelInforDTO dto){
    	RegistePersonnelInfor dbEntity = registePersonnelInforService.get(dto.getId());
    	if(!RegistePersonnelInfor.ADMIN_UNCHECK.equals(dbEntity.getAdminCheckStatus())){
    		return CommonResponse.getFailResponse("行政机构已审，不能再修改审核结果！");
    	}
        RegistePersonnelInfor model=beanIncompleteCopy(dto,new RegistePersonnelInfor());
        model.setAgencyCheckTime(new Date());
        model.setAgencyCheckUser(UserSession.getCurrentUserSession().getUserId());
        return CommonResponse.getResponse(registePersonnelInforService.check(model));
    }
    
    /**
     * 行政审核页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/admin")
    public String admin(@RequestParam String id,Model model){
    	RegistePersonnelInfor registePersonnelInfor = registePersonnelInforService.get(id);
        if(registePersonnelInfor == null){
            throw new BusinessException("备案信息变更申请记录不存在！");
        }
        model.addAttribute("registePersonnelInfor", registePersonnelInfor);
        return "/hrms/org/registe_personnel_infor_check_admin";
    }
	
    /**
     * 机构审核结果保存
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/admincheck",method = {RequestMethod.POST})
    public Object admincheck(RegistePersonnelInforDTO dto){
         RegistePersonnelInfor model=beanIncompleteCopy(dto,new RegistePersonnelInfor());
         model.setAdminCheckTime(new Date());
         model.setAdminCheckUser(UserSession.getCurrentUserSession().getUserId());
         return CommonResponse.getResponse(registePersonnelInforService.check(model));
    }
}
