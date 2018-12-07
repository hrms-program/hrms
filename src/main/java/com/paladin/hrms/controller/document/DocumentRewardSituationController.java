package com.paladin.hrms.controller.document;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.model.document.DocumentRewardSituation;
import com.paladin.hrms.service.document.DocumentRewardSituationService;

/**
 * 奖惩经历
 * @author FM
 *
 */
@Controller
@RequestMapping("/hrms/rewardSituation")
public class DocumentRewardSituationController {

	@Autowired
	private DocumentRewardSituationService rewardSituationService;
	
	//修改保存(奖惩情况)
	@ResponseBody
	@PostMapping("/add")
	public Object saveOrUpdateWorkingExperience(DocumentRewardSituation rewardSituation) {
		
		try{
			   if(StringUtils.isBlank(rewardSituation.getBusiId())){
				   rewardSituationService.saveRewardSituation(rewardSituation);
			   }else{
				   rewardSituationService.updateRewardSituation(rewardSituation);
			   }
				
			
		}catch(BusinessException e){
			return CommonResponse.getErrorResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("删除成功", rewardSituation);
	}
		
	//删除
	@ResponseBody
	@PostMapping("/delete")
	public Object delete(DocumentRewardSituation rewardSituation) {
		try{
			  
			rewardSituationService.deleteRewardSituation(rewardSituation);
			
		}catch(BusinessException e){
			return CommonResponse.getErrorResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("删除成功", rewardSituation);
	}
}
