package com.paladin.hrms.controller.document;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.model.document.DocumentParellelPost;
import com.paladin.hrms.service.document.DocumentParellelPostService;

@Controller
@RequestMapping("/parellelPost")
public class DocumentParellelPostController extends ControllerSupport {

	@Autowired
	private DocumentParellelPostService parellelPostService;

	// 修改保存(教育经历)
	@ResponseBody
	@PostMapping("/add")
	@RequiresPermissions("document:add")
	public Object saveOrUpdateLearningExperience(DocumentParellelPost parellelPost) {
		try {
			if (StringUtils.isBlank(parellelPost.getBusiId())) {
				parellelPostService.saveDocumentParellelPost(parellelPost);
			} else {
				parellelPostService.updateDocumentParellelPost(parellelPost);
			}

		}catch(BusinessException e){
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("保存成功",parellelPost);
	}

	@ResponseBody
	@PostMapping("/delete")
	@RequiresPermissions("document:add")
	public Object delete(DocumentParellelPost parellelPost) {
		try {

			parellelPostService.deleteDocumentParellelPost(parellelPost);

		}catch(BusinessException e){
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("删除成功",parellelPost);
	}
}
