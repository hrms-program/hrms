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
import com.paladin.hrms.model.document.DocumentAnnualCheck;
import com.paladin.hrms.service.document.DocumentAnnualCheckService;
/**
 * 年度考核
 * @author FM
 *
 */
@Controller
@RequestMapping("/hrms/annualCheck")
public class DocumentAnnualCheckController extends ControllerSupport {

	@Autowired
	private DocumentAnnualCheckService annualCheckService;
	
	//修改保存(年度考核)
	@ResponseBody
	@PostMapping("/add")
	public Object saveOrUpdateWorkingExperience(DocumentAnnualCheck annualCheck) {
		try{
		   if(StringUtils.isBlank(annualCheck.getBusiId())){
			   annualCheckService.saveAnnualCheck(annualCheck);
		   }else{
			   annualCheckService.updateAnnualCheck(annualCheck);
		   }
		}catch(BusinessException e){
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("保存成功",annualCheck);
	}
		
	//删除
	@ResponseBody
	@PostMapping("/delete")
	public Object delete(DocumentAnnualCheck annualCheck) {
		try{
			annualCheckService.deleteAnnualCheck(annualCheck);
		}catch(BusinessException e){
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("删除成功",annualCheck);
	}
}
