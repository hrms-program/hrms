package com.paladin.hrms.controller.org;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.org.pojo.OrgAgencyDTO;
import com.paladin.hrms.controller.org.pojo.OrgAgencyDetailDTO;
import com.paladin.hrms.controller.org.pojo.OrgAgencyDetailVO;
import com.paladin.hrms.controller.org.pojo.OrgAgencyQuery;
import com.paladin.hrms.controller.org.pojo.OrgAgencyVO;
import com.paladin.hrms.model.org.OrgAgency;
import com.paladin.hrms.model.org.OrgAgencyDetail;
import com.paladin.hrms.service.org.OrgAgencyDetailService;
import com.paladin.hrms.service.org.OrgAgencyService;

/**
 * @author 黄伟华
 * @version 2018年10月16日 上午9:17:26
 */

@Controller
@RequestMapping("/hrms/org/agency/")
public class OrgAgencyController extends ControllerSupport {

	@Autowired
	private OrgAgencyService agencyService;

	@Autowired
	private OrgAgencyDetailService agencyDetailService;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	@QueryInputMethod(queryClass = OrgAgencyQuery.class)
	public String index() {
		return "/hrms/org/agency_index";
	}

	/**
	 * 查询
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping("/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = OrgAgencyQuery.class, paramIndex = 0)
	public Object findAll(OrgAgencyQuery query) {
		return CommonResponse.getSuccessResponse(agencyService.findOrgAgency(query));
	}

	/**
	 * 详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String view(@RequestParam(required = false) String id, Model model) {
		model.addAttribute("agencyId", id);
		return "/hrms/org/agency_edit";
	}

	/**
	 * 查询
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping("/get/detail")
	@ResponseBody
	public Object getDetail(@RequestParam String id) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("base", beanCopy(agencyService.get(id), new OrgAgencyVO()));
		result.put("detail", beanCopy(agencyDetailService.get(id), new OrgAgencyDetailVO()));

		return CommonResponse.getSuccessResponse(result);
	}

	/**
	 * 保存基础信息
	 * 
	 * @param publicityMessage
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("/save/base")
	@ResponseBody
	public Object saveBase(@Valid OrgAgencyDTO base, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}

		String id = base.getId();
		boolean isAdd = (id != null && id.length() == 0);
		OrgAgency model = beanCopy(base, isAdd ? new OrgAgency() : agencyService.get(id));

		if (isAdd) {
			id = UUIDUtil.createUUID();
			model.setId(id);
			if (agencyService.save(model) > 0) {
				return CommonResponse.getSuccessResponse(beanCopy(agencyService.get(id), new OrgAgencyVO()));
			}
		} else {
			if (agencyService.update(model) > 0) {
				return CommonResponse.getSuccessResponse(beanCopy(agencyService.get(id), new OrgAgencyVO()));
			}
		}

		return CommonResponse.getFailResponse();
	}

	/**
	 * 保存基础信息
	 * 
	 * @param publicityMessage
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("/save/detail")
	@ResponseBody
	public Object saveDetail(@Valid OrgAgencyDetailDTO detail, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}

		String id = detail.getAgencyId();
		if (id == null || id.length() == 0) {
			return CommonResponse.getFailResponse("没有机构ID");
		}

		OrgAgencyDetail model = agencyDetailService.get(id);
		boolean isAdd = (model == null);
		model = beanCopy(detail, isAdd ? new OrgAgencyDetail() : model);
		if (isAdd) {
			if (agencyDetailService.save(model) > 0) {
				return CommonResponse.getSuccessResponse(beanCopy(agencyDetailService.get(id), new OrgAgencyDetailVO()));
			}
		} else {
			if (agencyDetailService.update(model) > 0) {
				return CommonResponse.getSuccessResponse(beanCopy(agencyDetailService.get(id), new OrgAgencyDetailVO()));
			}
		}

		return CommonResponse.getFailResponse();
	}

	/**
	 * 删除
	 * 
	 * @param publicityMessage
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("/remove")
	@ResponseBody
	public Object delete(@RequestParam String id) {
		return CommonResponse.getResponse(agencyService.removeByPrimaryKey(id));
	}
}
