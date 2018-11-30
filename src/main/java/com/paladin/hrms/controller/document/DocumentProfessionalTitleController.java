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
import com.paladin.hrms.model.document.DocumentProfessionalTitle;
import com.paladin.hrms.service.document.DocumentProfessionalTitleService;

/**
 * 职称
 * @author FM
 *
 */
@Controller
@RequestMapping("/hrms/professionalTitle")
public class DocumentProfessionalTitleController {

	@Autowired
	private DocumentProfessionalTitleService professionalTitleService;
	
	//修改保存(职称信息)
	@ResponseBody
	@PostMapping("/add")
	public Object saveOrUpdateWorkingInfo(DocumentProfessionalTitle professionalTitle) {
		
		try{
			   if(StringUtils.isBlank(professionalTitle.getBusiId())){
				   professionalTitleService.saveProfessionalTitle(professionalTitle);
			   }else{
				   professionalTitleService.updateProfessionalTitle(professionalTitle);
			   }
				
			
		}catch(BusinessException e){
			return CommonResponse.getErrorResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("保存成功", professionalTitle);
	}
	
	
	//删除
	@ResponseBody
	@PostMapping("/delete")
	public Object delete(DocumentProfessionalTitle professionalTitle) {
		try{
			  
			professionalTitleService.deleteProfessionalTitle(professionalTitle);
			
		}catch(BusinessException e){
			return CommonResponse.getErrorResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("删除成功", professionalTitle);
	}
		
}
