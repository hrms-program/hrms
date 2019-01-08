package com.paladin.hrms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.WebUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.core.HrmsUserSession;
import com.paladin.hrms.service.syst.SysUserService;

@Controller
@RequestMapping("/hrms/")
public class LoginController extends ControllerSupport {

	@Autowired
	SysUserService sysUserService;

	@RequestMapping(value = "/index")
	public Object index(HttpServletRequest request) {
		HrmsUserSession session = HrmsUserSession.getCurrentUserSession();
		if (session.isPersonalUser()) {
			return new ModelAndView("/hrms/index_personal", "user", session);
		} else {
			return new ModelAndView("/hrms/index_manager", "user", session);
		}
	}

	@RequestMapping(value = "/main")
	public Object main(HttpServletRequest request) {
		HrmsUserSession session = HrmsUserSession.getCurrentUserSession();
		if (session.isPersonalUser()) {
			return new ModelAndView("/hrms/index_personal", "user", session);
		} else {
			return new ModelAndView("/hrms/index_manager", "user", session);
		}
	}

	/**
	 * 登录入口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Object loginInput(HttpServletRequest request, HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		boolean isAjax = WebUtil.isAjaxRequest(request);
		if (subject.isAuthenticated()) {
			if (isAjax) {
				WebUtil.sendJson(response, CommonResponse.getSuccessResponse("登录成功"));
				return null;
			} else {
				return index(request);
			}
		}

		return "/hrms/login";
	}

	/**
	 * 登录验证，交由shiro做
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(HttpServletRequest request, HttpServletResponse response, Model model) {

		Subject subject = SecurityUtils.getSubject();
		boolean isAjax = WebUtil.isAjaxRequest(request);
		boolean isApp = WebUtil.isApp(request);

		if (subject.isAuthenticated()) {
			if (isApp) {
				WebUtil.sendJson(response, CommonResponse.getSuccessResponse("登录成功", subject.getSession().getId()));
				return null;
			}

			if (isAjax) {
				WebUtil.sendJson(response, CommonResponse.getSuccessResponse("登录成功"));
				return null;
			} else {
				return null;
			}
		} else {
			if (isApp) {
				WebUtil.sendJson(response, CommonResponse.getFailResponse("登录失败,用户名或密码错误！"));
				return null;
			}

			if (isAjax) {
				WebUtil.sendJson(response, CommonResponse.getFailResponse("登录失败,用户名或密码错误！"));
				return null;
			} else {
				model.addAttribute("isError", true);
				return "/hrms/login";
			}
		}
	}

	/**
	 * 登出logout
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public Object logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return CommonResponse.getSuccessResponse("退出成功");
	}

	/**
	 * 更新密码
	 * 
	 * @param newPassword
	 * @param oldPassword
	 * @return
	 */
	@RequestMapping(value = "/update/password")
	@ResponseBody
	public Object updatePassword(@RequestParam String newPassword, @RequestParam String oldPassword) {
		return CommonResponse.getResponse(sysUserService.updateSelfPassword(newPassword, oldPassword));
	}

	@RequestMapping(value = "/test/error")
	public Object testError() {
		throw new BusinessException("测试异常页面");
	}

}
