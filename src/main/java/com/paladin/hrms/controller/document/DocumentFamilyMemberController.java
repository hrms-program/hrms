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
import com.paladin.hrms.model.document.DocumentFamilyMember;
import com.paladin.hrms.service.document.DocumentFamilyMemberService;

/**
 * 家庭成员
 * @author FM
 */
@Controller
@RequestMapping("/hrms/familyMember")
public class DocumentFamilyMemberController extends ControllerSupport {

	@Autowired
	private DocumentFamilyMemberService familyMemberService;
	
	//修改保存(家庭成员)
	@ResponseBody
	@PostMapping("/add")
	public Object saveOrUpdateWorkingExperience(DocumentFamilyMember familyMember) {
		try{
		   if(StringUtils.isBlank(familyMember.getBusiId())){
			   familyMemberService.saveFamilyMember(familyMember);
		   }else{
			   familyMemberService.updateFamilyMember(familyMember);
		   }
		}catch(BusinessException e){
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("保存成功",familyMember);
	}
	
	//删除
	@ResponseBody
	@PostMapping("/delete")
	public Object delete(DocumentFamilyMember familyMember) {
		try{
			familyMemberService.deleteFamilyMember(familyMember);
		}catch(BusinessException e){
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("删除成功",familyMember);
	}
}
