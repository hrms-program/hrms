package com.paladin.hrms.controller.assess;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.assess.pojo.PhysicianAssessDTO;
import com.paladin.hrms.controller.assess.pojo.PhysicianAssessQuery;
import com.paladin.hrms.model.assess.AssessPhysicianRecord;
import com.paladin.hrms.service.assess.AssessPhysicianRecordService;
import com.paladin.hrms.service.org.OrgPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <医师定期考核>
 *
 * @author Huangguochen
 * @create 2018/11/1 15:37
 * @since 1.0.0
 */
@Controller
@RequestMapping("/hrms/yskh")
public class PhysicianAssessRecordController extends ControllerSupport{
    
    @Autowired
    private AssessPhysicianRecordService assessPhysicianRecordService;
    
    @Autowired
    private OrgPersonnelService orgPersonnelService;
    
    @RequestMapping("/index")
    @QueryInputMethod(queryClass = PhysicianAssessQuery.class)
    public String index(){
        return "/hrms/assess/physician_assess_index";
    }
    
    @RequestMapping("/find")
    @QueryOutputMethod(queryClass = PhysicianAssessQuery.class, paramIndex = 0)
    @ResponseBody
    public Object find(PhysicianAssessQuery query){
        return CommonResponse.getSuccessResponse(assessPhysicianRecordService.findAssessPhysicianRecord(query));
    }
    
    @RequestMapping("/add/save")
    @ResponseBody
    public Object addSave(PhysicianAssessDTO dto){
        dto.setAssessCycle(String.valueOf(dto.getStartTime()) + "至" + String.valueOf(dto.getEndTime()));
        
        String id = dto.getId();
        boolean isAdd = (id != null && id.length() == 0);
        
        if(isAdd){
            if (assessPhysicianRecordService.getAssessCycle(dto.getIdentificationNo(), dto.getAssessCycle()) > 0){
                throw new BusinessException("一个人员不能同时存在俩个相同的考核周期");
            }
        }
        AssessPhysicianRecord record =beanCopy(dto,isAdd ? new AssessPhysicianRecord() : assessPhysicianRecordService.get(id));
        
        if (assessPhysicianRecordService.saveOrUpdate(record) > 0){
            return CommonResponse.getSuccessResponse(record);
        }else{
            return CommonResponse.getValidFailResponse(record);
        }
    }
    
    @RequestMapping("/view")
    public String view(@RequestParam String id, Model model){
        AssessPhysicianRecord assessPhysicianRecord = assessPhysicianRecordService.get(id);
        
        if (assessPhysicianRecord == null){
            throw new BusinessException("找不到相关人员考核周期");
        }
        model.addAttribute("object", assessPhysicianRecord);
        model.addAttribute("isView", true);
        return "/hrms/assess/physician_assess_view";
    }
    
    @RequestMapping("/add")
    public String add(){
        return "/hrms/assess/physician_assess_add";
    }
    
    @RequestMapping("/edit")
    public String edit(@RequestParam String id, Model model){
        
        AssessPhysicianRecord assessPhysicianRecord = assessPhysicianRecordService.get(id);
        if (assessPhysicianRecord == null){
            throw new BusinessException("找不到相关人员考核周期");
        }
        model.addAttribute("object", assessPhysicianRecord);
        model.addAttribute("isEdit", true);
        return "/hrms/assess/physician_assess_view";
    }
    
    @RequestMapping("/remove")
    @ResponseBody
    public Object remove(@RequestParam String id){
        return CommonResponse.getResponse(assessPhysicianRecordService.removeByPrimaryKey(id));
    }
    
    /**
     * 根据身份证获取人员
     * 
     * @param identificationNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/idCard")
    @ResponseBody
    public Object idCard(@RequestParam String identificationNo){
        return CommonResponse.getSuccessResponse(orgPersonnelService.getPersonnelByIdentificationNo(identificationNo));
    }
}
