package com.paladin.hrms.controller.document;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.model.document.DocumentLearningExperience;
import com.paladin.hrms.service.document.DocumentLearningExperienceService;

/**
 * 教育经历
 * @author FM
 *
 */
@Controller
@RequestMapping("/hrms/learningExperience")
public class DocumentLearningExperienceController {

	@Autowired
	private DocumentLearningExperienceService learningExperienceService;
	
	//修改保存(教育经历)
	@ResponseBody
	@PostMapping("/add")
	public Object saveOrUpdateLearningExperience(DocumentLearningExperience learningExperience) {
		
		try{
			   if(StringUtils.isBlank(learningExperience.getBusiId())){
				   learningExperienceService.saveLearningExperience(learningExperience);
			   }else{
				   learningExperienceService.updateLearningExperience(learningExperience);
			   }
				
		}catch(BusinessException e){
			return CommonResponse.getErrorResponse(e.getMessage());
		}
		/*return R.Success("保存成功",learningExperience);*/
		return CommonResponse.getSuccessResponse("保存成功", learningExperience);
	}
	
	//删除
		@ResponseBody
		@PostMapping("/delete")
		public Object delete(DocumentLearningExperience learningExperience) {
			
			try{
				  
				learningExperienceService.deleteLearningExperience(learningExperience);
				
			}catch(BusinessException e){
				return CommonResponse.getErrorResponse(e.getMessage());
			}
			return CommonResponse.getSuccessResponse("删除成功", learningExperience);
		}
}
