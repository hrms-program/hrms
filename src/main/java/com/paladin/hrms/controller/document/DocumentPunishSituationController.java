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
import com.paladin.hrms.model.document.DocumentPunishSituation;
import com.paladin.hrms.service.document.DocumentPunishSituationService;

/**
 * 处罚经历
 * @author FM
 *
 */
@Controller
@RequestMapping("/hrms/punishSituation")
public class DocumentPunishSituationController  extends ControllerSupport {

	@Autowired
	private DocumentPunishSituationService punishSituationService;
	
	//修改保存(奖惩情况)
	@ResponseBody
	@PostMapping("/add")
	@RequiresPermissions("document:add")
	public Object saveOrUpdateWorkingExperience(DocumentPunishSituation punishSituation) {
		try{
			   if(StringUtils.isBlank(punishSituation.getBusiId())){
				   punishSituationService.savePunishSituation(punishSituation);
			   }else{
				   punishSituationService.updatePunishSituation(punishSituation);
			   }
		}catch(BusinessException e){
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("保存成功",punishSituation);
	}
		
	//删除
	@ResponseBody
	@PostMapping("/delete")
	@RequiresPermissions("document:add")
	public Object delete(DocumentPunishSituation punishSituation) {
		try{
			punishSituationService.deletePunishSituation(punishSituation);
		}catch(BusinessException e){
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("删除成功",punishSituation);
	}
}
