package com.paladin.hrms.controller.org;

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
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.org.pojo.OrgAgencyManagerDTO;
import com.paladin.hrms.controller.org.pojo.OrgAgencyManagerQuery;
import com.paladin.hrms.model.org.OrgAgencyManager;
import com.paladin.hrms.model.syst.SysUser;
import com.paladin.hrms.service.org.OrgAgencyManagerService;
import com.paladin.hrms.service.syst.SysUserService;

/**
 * 所辖机构管理员
 * 
 * @author 黄伟华
 * @version 2018年11月6日 下午1:40:47
 */
@Controller
@RequestMapping("/hrms/org/agency/manager")
public class OrgAgencyManagerController extends ControllerSupport{
    
    @Autowired
    private OrgAgencyManagerService orgAgencyManagerService;
    
    @Autowired
    private SysUserService sysUserService;
    
    /**
     * 首页
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/index")
    @QueryInputMethod(queryClass = OrgAgencyManagerQuery.class)
    public String index(){
        return "/hrms/org/agency_manager_index";
    }
    
    /**
     * 查询
     * 
     * @param query
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/find")
    @ResponseBody
    @QueryInputMethod(queryClass = OrgAgencyManagerQuery.class)
    public Object findAll(OrgAgencyManagerQuery query){
        return CommonResponse.getSuccessResponse(orgAgencyManagerService.fildAll(query));
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
        OrgAgencyManager manager = orgAgencyManagerService.get(id);
        if(manager == null){
            throw new BusinessException("查看的机构管理员帐号不存在");
        }
        model.addAttribute("manager", manager);
        return "/hrms/org/agency_manager_view";
    }
    
    /**
     * 新增
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(@RequestParam String account,String agencyId,Model model){
        OrgAgencyManager manager=new OrgAgencyManager();
        manager.setAccount(account);
        manager.setAgencyId(agencyId);
        model.addAttribute("manager", manager);
        return "/hrms/org/agency_manager_edit";
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
        OrgAgencyManager manager = orgAgencyManagerService.get(id);
        if(manager == null){
            throw new BusinessException("修改的机构管理员用户不存在");
        }
        model.addAttribute("manager", manager);
        return "/hrms/org/agency_manager_edit";
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
    public Object addSave(@Valid OrgAgencyManagerDTO dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        OrgAgencyManager manager=beanIncompleteCopy(dto,new OrgAgencyManager());
        
        return CommonResponse.getResponse(orgAgencyManagerService.addSave(manager));
    }
    
    /**
     * 重置密码
     * 
     * @param account
     * @return
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value="/resetPwd",method = {RequestMethod.POST})
    public Object resetPwd(@RequestParam String account){
        return CommonResponse.getResponse(sysUserService.resetPasswordByAccount(account));
    }
 
    /**
     * 注销帐号
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value="/cancel",method = {RequestMethod.POST})
    public Object cancel(@RequestParam String id){
        if(sysUserService.get(id) == null){
            throw new BusinessException("机构管理员帐号不存在");
        }
        SysUser user = new SysUser();
        user.setId(id);
        user.setState(SysUser.STATE_LOGOUT);
        return CommonResponse.getResponse(sysUserService.updateSelective(user));
    }
}
