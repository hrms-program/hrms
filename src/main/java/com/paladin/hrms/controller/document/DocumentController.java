package com.paladin.hrms.controller.document;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.document.pojo.DocumentInforVO;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelBaseDTO;
import com.paladin.hrms.model.document.DocumentInfor;
import com.paladin.hrms.model.org.OrgPersonnel;
import com.paladin.hrms.service.document.DocumentInforService;
import com.paladin.hrms.service.org.OrgPersonnelService;
@Controller
@RequestMapping("/hrms/document")
public class DocumentController extends ControllerSupport{

	@Autowired
    private OrgPersonnelService personnelService;
	@Autowired
	private DocumentInforService documentInforService;
	/**
     * 详情
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String view(@RequestParam String id,ModelMap map) {
    	DocumentInforVO detail=new DocumentInforVO();
    	OrgPersonnel orgPersonnel = new OrgPersonnel();
    	if(StringUtils.isNotBlank(id)){
    	    orgPersonnel = personnelService.get(id);
    		detail = documentInforService.queryDocumentById(id);
    	}
    	map.addAttribute("document", detail);
    	map.addAttribute("orgPersonnel", orgPersonnel);
        return "hrms/document/edit";
    }
    
    //基本信息保存
    @RequestMapping("/saveOrUpdateBasicInfo/add")
    @ResponseBody
    public Object saveOrUpdateBasicInfo(DocumentInfor documentInfor) {
    	try{
    		documentInforService.updateDocumentInfor(documentInfor);
		}catch(BusinessException e){
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("删除成功",documentInfor);
    }
    
  //基本信息保存
    @RequestMapping("/saveOrUpdateWorkingInfo/add")
    @ResponseBody
    public Object saveOrUpdateWorkingInfo(DocumentInfor documentInfor) {
    	try{
    		documentInforService.updateDocumentInfor(documentInfor);
		}catch(BusinessException e){
			return CommonResponse.getFailResponse(e.getMessage());
		}
		return CommonResponse.getSuccessResponse("删除成功",documentInfor);
    }

}
