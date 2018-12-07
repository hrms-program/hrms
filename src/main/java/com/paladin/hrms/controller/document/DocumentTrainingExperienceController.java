package com.paladin.hrms.controller.document;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.model.document.DocumentTrainingExperience;
import com.paladin.hrms.service.document.DocumentTrainingExperienceService;

/**
 * 培训经历
 * @author FM
 *
 */
@Controller
@RequestMapping("/hrms/trainingExperience")
public class DocumentTrainingExperienceController {

	@Autowired
	private DocumentTrainingExperienceService trainingExperienceService;
	
	//修改保存(培训经历)
		@ResponseBody
		@PostMapping("/add")
		public Object saveOrUpdateWorkingExperience(DocumentTrainingExperience trainingExperience) {
			
			try{
				   if(StringUtils.isBlank(trainingExperience.getBusiId())){
					   trainingExperienceService.saveTrainingExperience(trainingExperience);
				   }else{
					   trainingExperienceService.updateTrainingExperience(trainingExperience);
				   }
					
				
			}catch(BusinessException e){
				return CommonResponse.getErrorResponse(e.getMessage());
			}
			return CommonResponse.getSuccessResponse("保存成功", trainingExperience);
		}
		
		//删除
		@ResponseBody
		@PostMapping("/delete")
		public Object delete(DocumentTrainingExperience trainingExperience) {
			
			try{
				  
				trainingExperienceService.deleteTrainingExperience(trainingExperience);
				
			}catch(BusinessException e){
				return CommonResponse.getErrorResponse(e.getMessage());
			}
			return CommonResponse.getSuccessResponse("删除成功", trainingExperience);
		}
}
