package com.paladin.hrms.controller.org;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.org.pojo.*;
import com.paladin.hrms.model.org.OrgAgency;
import com.paladin.hrms.model.org.OrgAgencyDetail;
import com.paladin.hrms.service.org.OrgAgencyDetailService;
import com.paladin.hrms.service.org.OrgAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

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

    /**
     * 功能描述: <br>
     * 〈机构信息导出〉
     * @param query
     * @param response
     * @return:void
     */
    @RequestMapping("/export/do")
    public void exportDo(OrgAgencyQuery query, HttpServletResponse response) {
        try {
            // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("机构信息统计表.xlsx", "UTF-8"));
            agencyService.export(query, response.getOutputStream());
        } catch (IOException e) {
            throw new BusinessException("导出机构信息统计表失败");
        }
    }
}
