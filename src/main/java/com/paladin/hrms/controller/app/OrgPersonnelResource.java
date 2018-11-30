package com.paladin.hrms.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelCultivateVO;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelEducationVO;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelJobVO;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelPositionalInfoVO;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelRewardInfoVO;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelScienceEducationVO;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelWorkExperienceVO;
import com.paladin.hrms.service.org.OrgPersonnelClaimRecordService;
import com.paladin.hrms.service.org.OrgPersonnelCultivateService;
import com.paladin.hrms.service.org.OrgPersonnelEducationService;
import com.paladin.hrms.service.org.OrgPersonnelJobService;
import com.paladin.hrms.service.org.OrgPersonnelPositionalInfoService;
import com.paladin.hrms.service.org.OrgPersonnelPracticeRegistrationService;
import com.paladin.hrms.service.org.OrgPersonnelPracticeService;
import com.paladin.hrms.service.org.OrgPersonnelRewardInfoService;
import com.paladin.hrms.service.org.OrgPersonnelScienceEducationService;
import com.paladin.hrms.service.org.OrgPersonnelService;
import com.paladin.hrms.service.org.OrgPersonnelWorkExperienceService;
import com.paladin.hrms.service.org.OrgPersonnelYearAssessService;

@Controller
@RequestMapping("/hrms/app/personal/")
public class OrgPersonnelResource extends ControllerSupport{
    
	@Autowired
	private OrgPersonnelService personnelService;
	
	@Autowired
	private OrgPersonnelJobService personnelJobService;
	
	@Autowired
    private OrgPersonnelEducationService personnelEducationService;
	
	@Autowired
	private OrgPersonnelPracticeService personnelPracticeService;
	
	@Autowired
    private OrgPersonnelCultivateService orgPersonnelCultivateService;

    @Autowired
    private OrgPersonnelPositionalInfoService orgPersonnelPositionalInfoService;

    @Autowired
    private OrgPersonnelRewardInfoService orgPersonnelRewardInfoService;

    @Autowired
    private OrgPersonnelWorkExperienceService orgPersonnelWorkExperienceService;

    @Autowired
    private OrgPersonnelPracticeRegistrationService orgPersonnelPracticeRegistrationService;

    @Autowired
    private OrgPersonnelYearAssessService orgPersonnelYearAssessService;

    @Autowired
    private OrgPersonnelScienceEducationService orgPersonnelScienceEducationService;

    @Autowired
    private OrgPersonnelClaimRecordService orgPersonnelClaimRecordService;
    
	/**
	 * 基本信息
	 * 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/detail")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public Object detail(@RequestParam String id){
		return CommonResponse.getSuccessResponse(personnelService.get(id));
	}
	
	/**
	 * 工作信息
	 * 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/job")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailJob(@RequestParam String id){
		OrgPersonnelJobVO orgPersonnelJobVO = beanIncompleteCopy(personnelJobService.get(id), new OrgPersonnelJobVO());
        return CommonResponse.getSuccessResponse(orgPersonnelJobVO);
    }
    
    /**
     * 执业信息
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/practice")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailPractice(@RequestParam String id){
        return CommonResponse.getSuccessResponse(personnelPracticeService.get(id));
    }
    
    
    /**
    * 教育经历
    * 
    * @param id
    * @return
    * @see [类、类#方法、类#成员]
    */
   @RequestMapping("/education")
   @ResponseBody
   @CrossOrigin(origins = "*", maxAge = 3600)
   public Object detailEducation(@RequestParam String id){
       return CommonResponse.getSuccessResponse(personnelEducationService.getPersonnelEducations(id));
   }
   
   /**
    * 教育经历详情
    * 
    * @param id
    * @return
    * @see [类、类#方法、类#成员]
    */
   @RequestMapping("/education/details")
   @ResponseBody
   @CrossOrigin(origins = "*", maxAge = 3600)
   public Object detailEducationDetails(@RequestParam String id){
	   OrgPersonnelEducationVO orgPersonnelEducationVO = beanIncompleteCopy(personnelEducationService.get(id), new OrgPersonnelEducationVO());
       return CommonResponse.getSuccessResponse(orgPersonnelEducationVO);
   }
    
    /**
     * 工作经历
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/workExperience")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailWorkExperience(@RequestParam String id){
        return CommonResponse.getSuccessResponse(orgPersonnelWorkExperienceService.getPersonnelExperience(id));
    }
    
    /**
     * 工作经历详情
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/workExperience/details")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailWorkExperienceDetails(@RequestParam String id){
    	OrgPersonnelWorkExperienceVO orgPersonnelWorkExperienceVO = beanIncompleteCopy(orgPersonnelWorkExperienceService.get(id), new OrgPersonnelWorkExperienceVO());
        return CommonResponse.getSuccessResponse(orgPersonnelWorkExperienceVO);
    }
    
    /**
     * 职称信息
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/positionalInfo")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailPositionalInfo(@RequestParam String id){
        return CommonResponse.getSuccessResponse(orgPersonnelPositionalInfoService.getPersonnelPositionalInfo(id));
    }
    
    /**
     * 职称信息详情
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/positionalInfo/details")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailPositionalInfoDetails(@RequestParam String id){
    	OrgPersonnelPositionalInfoVO orgPersonnelPositionalInfoVO = beanIncompleteCopy(orgPersonnelPositionalInfoService.get(id), new OrgPersonnelPositionalInfoVO());
        return CommonResponse.getSuccessResponse(orgPersonnelPositionalInfoVO);
    }
    
    /**
     *科教信息
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/scienceEducation")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailScienceEducation(@RequestParam String id){
        return CommonResponse.getSuccessResponse(orgPersonnelScienceEducationService.getScienceEducation(id));
    }
    
    /**
     * 科教信息种类查询
     * 
     * @param type
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/scienceEducation/type")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailScienceEducationType(@RequestParam Integer type){
        return CommonResponse.getSuccessResponse(orgPersonnelScienceEducationService.getAppScienceEducation(type));
    }
    
    /**
     *科教信息详情
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/scienceEducation/details")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailScienceEducationDetails(@RequestParam String id){
    	OrgPersonnelScienceEducationVO orgPersonnelScienceEducationVO = beanIncompleteCopy(orgPersonnelScienceEducationService.get(id), new OrgPersonnelScienceEducationVO());
        return CommonResponse.getSuccessResponse(orgPersonnelScienceEducationVO);
    }
    
    
    
    /**
     * 奖励信息
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/rewardInfo")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailRewardInfo(@RequestParam String id){
        return CommonResponse.getSuccessResponse(orgPersonnelRewardInfoService.getPersonnelRewardInfo(id));
    }
    
    /**
     * 奖励信息详情
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/rewardInfo/details")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailRewardInfoDetails(@RequestParam String id){
    	OrgPersonnelRewardInfoVO orgPersonnelRewardInfoVO = beanIncompleteCopy(orgPersonnelRewardInfoService.get(id), new OrgPersonnelRewardInfoVO());
        return CommonResponse.getSuccessResponse(orgPersonnelRewardInfoVO);
    }
    
    /**
     * 培训经历
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/cultivate")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailCultivate(@RequestParam String id){
        return CommonResponse.getSuccessResponse(orgPersonnelCultivateService.getPersonnelCultivate(id));
    }
    
    /**
     * 培训经历详情
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/cultivate/details")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailCultivateDetails(@RequestParam String id){
    	OrgPersonnelCultivateVO cultivateVO = beanIncompleteCopy(orgPersonnelCultivateService.get(id), new OrgPersonnelCultivateVO());
        return CommonResponse.getSuccessResponse(cultivateVO);
    }
    
    /**
     * 执业注册
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/registration")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailRegistration(@RequestParam String id){
        return CommonResponse.getSuccessResponse(orgPersonnelPracticeRegistrationService.getPracticeRegistration(id));
    }
    
    /**
     * 年度考核
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/yearassess")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailYearassess(@RequestParam String id){
        return CommonResponse.getSuccessResponse(orgPersonnelYearAssessService.getPersonnelYearAssess(id));
    }
    
    /**
     * 年度考核详情
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/yearassess/details")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object detailYearassessDetails(@RequestParam String id){
        return CommonResponse.getSuccessResponse(orgPersonnelYearAssessService.get(id));
    }
}
