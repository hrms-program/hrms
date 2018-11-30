package com.paladin.hrms.controller.org;

import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.org.pojo.OrgPersonalAccountQuery;
import com.paladin.hrms.model.syst.SysUser;
import com.paladin.hrms.service.org.OrgPersonnelService;
import com.paladin.hrms.service.syst.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <个人账户管理>
 *
 * @author Huangguochen
 * @create 2018/11/9 15:37
 */
@Controller
@RequestMapping("/hrms/account/personal")
public class OrgPersonalAccountController extends ControllerSupport {

    @Autowired
    private OrgPersonnelService orgPersonnelService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/index")
    @QueryInputMethod(queryClass = OrgPersonalAccountQuery.class)
    public String index () { return  "/hrms/org/personal_account_index"; }

	@RequestMapping("/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = OrgPersonalAccountQuery.class, paramIndex = 0)
	public Object find(OrgPersonalAccountQuery query) {
		return CommonResponse.getSuccessResponse(orgPersonnelService.findPersonalAccountPage(query));
	}

    /*@RequestMapping("/view")
    public String view (@RequestParam("id") String id, Model model) {
       OrgPersonalAccountVO account =  orgPersonnelService.findPersonById(id);
       if (account == null) {
           throw  new BusinessException("无该账号信息！");
       }
       model.addAttribute("object",account);
       model.addAttribute("isView", true);
       return "/hrms/org/personal_account_view";
    }

    @RequestMapping("/edit")
    public String edit (@RequestParam("id") String id, Model model) {
        OrgPersonalAccountVO account =  orgPersonnelService.findPersonById(id);
        if (account == null) {
            throw  new BusinessException("无该账号信息！");
        }
        model.addAttribute("object",account);
        model.addAttribute("isCheck", true);
        return "/hrms/org/personal_account_view";
    }*/


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestParam String account, @RequestParam String id) {
        return  CommonResponse.getResponse(sysUserService.createUserAccount(account,id,SysUser.STATE_WAITING_ENABLED,SysUser.TYPE_PERSONAL_USER));
    }


    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public Object reset(@RequestParam String account) {
        return  CommonResponse.getResponse(sysUserService.resetPasswordByAccount(account));
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public Object cancel (@RequestParam String id,@RequestParam String account) {
        List<SysUser> users = sysUserService.searchAll(new GeneralCriteriaBuilder.Condition[]{
                new GeneralCriteriaBuilder.Condition(SysUser.COLUMN_FIELD_ACCOUNT, QueryType.EQUAL, account),
                new GeneralCriteriaBuilder.Condition(SysUser.COLUMN_FIELD_USER_ID, QueryType.EQUAL, id)
        });

        SysUser user = users != null && users.size() > 0 ? users.get(0) : null;
        if (user == null) {
            throw new BusinessException("账号异常");
        }

        return  CommonResponse.getResponse(sysUserService.removeByPrimaryKey(user.getId()));
    }

	@RequestMapping(value = "/unlock", method = RequestMethod.POST)
	@ResponseBody
	public Object unlock(@RequestParam String account) {
		SysUser user = sysUserService.getUser(account);
		if (user == null) {
			throw new BusinessException("账号异常");
		} else {
			return CommonResponse.getResponse(sysUserService.updateState(account,SysUser.STATE_ENABLED));
		}
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public Object count() {
        return  CommonResponse.getSuccessResponse(orgPersonnelService.findPeopleNotAccount());
	}

	@RequestMapping(value = "/addAccounts")
	@ResponseBody
	public Object addAccounts() {
		return CommonResponse.getSuccessResponse(orgPersonnelService.addAccounts());
	}

}
