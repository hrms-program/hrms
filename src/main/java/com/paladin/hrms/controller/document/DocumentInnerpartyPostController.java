package com.paladin.hrms.controller.document;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.model.document.DocumentInnerpartyPost;
import com.paladin.hrms.service.document.DocumentInnerpartyPostService;
/**
 * 党内职务
 * @author FM
 *
 */
@Controller
@RequestMapping("/hrms/innerpartyPost")
public class DocumentInnerpartyPostController extends ControllerSupport {

	@Autowired
	private DocumentInnerpartyPostService innerpartyPostService;
	
	//修改保存(党内经历)
	@ResponseBody
	@PostMapping("/add")
	public Object saveOrUpdateWorkingExperience(DocumentInnerpartyPost innerpartyPost) {
		try{
		   if(StringUtils.isBlank(innerpartyPost.getBusiId())){
			   innerpartyPostService.saveInnerpartyPost(innerpartyPost);
		   }else{
			   innerpartyPostService.updateInnerpartyPost(innerpartyPost);
		   }
		}catch(BusinessException e){
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("保存成功",innerpartyPost);
	}
	
	//删除
	@ResponseBody
	@PostMapping("/delete")
	public Object delete(DocumentInnerpartyPost innerpartyPost) {
		try{
			innerpartyPostService.deleteInnerpartyPost(innerpartyPost);
		}catch(BusinessException e){
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("删除成功",innerpartyPost);
	}
}
