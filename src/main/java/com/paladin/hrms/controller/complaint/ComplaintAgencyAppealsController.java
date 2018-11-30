package com.paladin.hrms.controller.complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.service.complaint.ComplaintAgencyChangeService;
import com.paladin.hrms.service.complaint.dto.ComplaintAgencyAppealsQueryDTO;
import com.paladin.hrms.service.complaint.dto.PersonnelForAgencyQueryDTO;

/**
 * 机构变更申诉
 *    
 * @author 黄伟华
 * @version 2018年11月2日 上午10:38:53 
 */

@Controller
@RequestMapping("/hrms/complaint/agency/appeals/")
public class ComplaintAgencyAppealsController
{
   
    @Autowired
    private ComplaintAgencyChangeService agencyChangeConfirmService;
    
    /**
     * 首页
     * 
     * @return
     */
    @RequestMapping("/index")
    @QueryInputMethod(queryClass = ComplaintAgencyAppealsQueryDTO.class)
    public String index() {
        return "/hrms/complaint/complaint_agency_appeals_index";
    }
    
    /**查询
     * @param query
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/find")
    @ResponseBody
    @QueryInputMethod(queryClass = PersonnelForAgencyQueryDTO.class)
    public Object agencyFind(PersonnelForAgencyQueryDTO query){
        return CommonResponse.getSuccessResponse(agencyChangeConfirmService.findPersonnelForChange(query));
    }
    
    /**
     * 人员机构转移
     * 
     * @param userId
     * @param target
     * @return
     */
    @RequestMapping("/transfer")
    @ResponseBody
    public Object appeals(@RequestParam(value = "personnelIds[]") String[] personnelIds, @RequestParam String targetAgency) {
        return CommonResponse.getResponse(agencyChangeConfirmService.applyAgencyChange(personnelIds, targetAgency));
    }
    
    /**
     * 撤销
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @PostMapping("/deleteTransfer")
    @ResponseBody
    public Object deleteTransfer(@RequestParam String id){
        return CommonResponse.getResponse(agencyChangeConfirmService.removeByPrimaryKey(id));
    }
}
