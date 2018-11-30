package com.paladin.hrms.controller.document;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.model.document.DocumentAdministrativePost;
import com.paladin.hrms.service.document.DocumentAdministrativePostService;

/**
 * 行政职务
 */
@Controller
@RequestMapping("/hrms/administrativePost")
public class DocumentAdministrativePostController extends ControllerSupport {

	@Autowired
	private DocumentAdministrativePostService administrativeService;

	// 修改保存(行政职务经历)
	@ResponseBody
	@RequestMapping("/add")
	public Object saveOrUpdateWorkingExperience(DocumentAdministrativePost administrativePost) {
		try {
			if (StringUtils.isBlank(administrativePost.getBusiId())) {
				administrativeService.saveAdministrativePost(administrativePost);
			} else {
				administrativeService.updateAdministrativePost(administrativePost);
			}
		} catch (BusinessException e) {
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("保存成功", administrativePost);
	}

	// 删除
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(DocumentAdministrativePost administrativePost) {
		try {
			administrativeService.deleteAdministrativePost(administrativePost);
		} catch (BusinessException e) {
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("保存成功");
	}
}
