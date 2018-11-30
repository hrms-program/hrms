package com.paladin.hrms.controller.document;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.model.document.DocumentWorkingExperience;
import com.paladin.hrms.service.document.DocumentWorkingExperienceService;


/**
 * 工作经历
 * @author FM
 *
 */
@Controller
@RequestMapping("/hrms/workingExperience")
public class DocumentWorkingExperienceController {

	@Autowired
	private DocumentWorkingExperienceService workingExperienceService;
	
	//修改保存(工作经历)
	@ResponseBody
	@PostMapping("/add")
	public Object saveOrUpdateWorkingExperience(DocumentWorkingExperience workingExperience) {
		
		try{
			   if(StringUtils.isBlank(workingExperience.getBusiId())){
				   workingExperienceService.saveWorkingExperience(workingExperience);
			   }else{
				   workingExperienceService.updateWorkingExperience(workingExperience);
			   }
				
			
		}catch(BusinessException e){
			return CommonResponse.getErrorResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("保存成功", workingExperience);
	}
	
	//删除
	@ResponseBody
	@PostMapping("/delete")
	public Object delete(DocumentWorkingExperience workingExperience) {
		try{
			  
			workingExperienceService.deleteWorkingExperience(workingExperience);
			
		}catch(BusinessException e){
			return CommonResponse.getErrorResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("保存成功", workingExperience);
	}
	
}
