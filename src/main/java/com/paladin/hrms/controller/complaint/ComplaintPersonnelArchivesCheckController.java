package com.paladin.hrms.controller.complaint;

import com.alibaba.fastjson.JSON;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.core.HrmsUserSession;
import com.paladin.hrms.model.complaint.ComplaintModel;
import com.paladin.hrms.model.complaint.ComplaintPersonnelArchivesCheck;
import com.paladin.hrms.service.complaint.ComplaintPersonnelArchivesCheckRecordService;
import com.paladin.hrms.service.complaint.ComplaintPersonnelArchivesCheckService;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckRecordVO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 人力资源信息审核
 * 
 * @author jisanjie
 * @version 2018年10月31日 下午1:40
 */
@Controller
@RequestMapping("/hrms/complaint/personnel/archives")
public class ComplaintPersonnelArchivesCheckController extends ControllerSupport {

	@Autowired
	private ComplaintPersonnelArchivesCheckService archivesCheckService;

	@Autowired
	private ComplaintPersonnelArchivesCheckRecordService archivesCheckRecordService;

  /** 跳首页 */
  @RequestMapping("/index")
  @QueryInputMethod(queryClass = ComplaintPersonnelArchivesCheckQueryDTO.class)
  public String index() {
    if (HrmsUserSession.getCurrentUserSession().isAgencyManager()) {
      return "/hrms/complaint/personnel_archives_check_org_everyone_index";
    } else {
      return "/hrms/complaint/personnel_archives_check_application_amount";
    }
  }

	/**
	 * 首页申请数量汇总
	 *
	 */
	@RequestMapping("/application/amount")
	@ResponseBody
	@QueryOutputMethod(queryClass = ComplaintPersonnelArchivesCheckQueryDTO.class, paramIndex = 0)
	public Object applicationAmount(ComplaintPersonnelArchivesCheckQueryDTO query) {
		return CommonResponse.getSuccessResponse(archivesCheckService.searchTableList(query));
	}

	/**
	 * 功能描述: <br>
	 * 〈行政审核页面〉
	 * @param
	 * @return  java.lang.String
	 * @author  Huangguochen
	 * @date  2019/2/25
	 */
	@RequestMapping("/district/everyone/index")
	public String districtPersonlIndex() {
		return "/hrms/complaint/personnel_archives_check_application_amount";
	}

	/**
	 * 功能描述: <br>
	 * 〈机构审核页面〉
	 * @param
	 * @return  java.lang.String
	 * @author  Huangguochen
	 * @date  2019/2/25
	 */
	@RequestMapping("/org/everyone/index")
	public String orgPersonlIndex() {
		return "/hrms/complaint/personnel_archives_check_org_everyone_index";
	}

	/**
	 * 跳往个人的申请列表页
	 * 
	 */
	@RequestMapping("/everyone/index")
	public String personnelIndex(String personnelId, Model model) {
		model.addAttribute("personnelId", personnelId);
		return "/hrms/complaint/personnel_archives_check_everyone_index";
	}

	/**
	 * 查询个人的申请列表
	 *
	 */
	@RequestMapping("/everyone/appliacation/list")
	@ResponseBody
	public Object searchAppliacationList(ComplaintPersonnelArchivesCheckQueryDTO query) {
		return CommonResponse.getSuccessResponse(archivesCheckService.searchAppliacationList(query));
	}

	@RequestMapping("/district/everyone/appliacation/list")
	@ResponseBody
	public Object searchDistrictAppliacationList(ComplaintPersonnelArchivesCheckQueryDTO query) {
		return CommonResponse.getSuccessResponse(archivesCheckService.searchDistrictAppliacationList(query));
	}

	/**
	 * 查看审核的信息
	 * 
	 * @param personnelId
	 * @param checkType
	 * @param model
	 * @return
	 */
	@RequestMapping("/check/view")
	public String checkView(@RequestParam String recordId, @RequestParam Integer recordType,@RequestParam Integer isOrg, Model model) {
		model.addAttribute("recordId", recordId);
		model.addAttribute("recordType", recordType);
		model.addAttribute("isOrg", isOrg);
		return "/hrms/complaint/personnel_archives_check_view";
	}

	/**
	 * 批量机构审核通过
	 */
	@RequestMapping("/org/check/records/successes")
	@ResponseBody
	public Object orgCheckSuccesses(@RequestParam(name = "ids[]") String[] ids) {
		int effect = 0;
		if (ids != null) {
			for (String id : ids) {
				ComplaintPersonnelArchivesCheck check = archivesCheckService.get(id);
				try {
					effect += archivesCheckService.check(check, null, true,1) ? 1 : 0;
				} catch (BusinessException e) {

				}
			}
		}
		return CommonResponse.getSuccessResponse(effect);
	}

	/**
	 * 批量行政审核通过
	 */
	@RequestMapping("/district/check/records/successes")
	@ResponseBody
	public Object districtCheckSuccesses(@RequestParam(name = "ids[]") String[] ids) {
		int effect = 0;
		if (ids != null) {
			for (String id : ids) {
				ComplaintPersonnelArchivesCheck check = archivesCheckService.get(id);
				try {
					effect += archivesCheckService.check(check, null, true,3) ? 1 : 0;
				} catch (BusinessException e) {

				}
			}
		}
		return CommonResponse.getSuccessResponse(effect);
	}

	/**
	 * 机构审核通过
	 */
	@RequestMapping("/org/check/success")
	@ResponseBody
	public Object checkSuccess(ComplaintPersonnelArchivesCheckResultDTO archivesCheckResult) {
		return CommonResponse.getResponse(archivesCheckService.checkSuccess(archivesCheckResult,true));
	}

	/**
	 * 机构审核不通过
	 * 
	 * @param archivesCheckResult
	 * @return
	 */
	@RequestMapping("/org/check/fail")
	@ResponseBody
	public Object checkFail(ComplaintPersonnelArchivesCheckResultDTO archivesCheckResult) {
		return CommonResponse.getResponse(archivesCheckService.checkFail(archivesCheckResult,true));
	}

	/**
	 * 行政审核通过
	 */
	@RequestMapping("/district/check/success")
	@ResponseBody
	public Object districtcheckSuccess(ComplaintPersonnelArchivesCheckResultDTO archivesCheckResult) {
		return CommonResponse.getResponse(archivesCheckService.checkSuccess(archivesCheckResult,false));
	}

	/**
	 * 行政审核不通过
	 *
	 * @param archivesCheckResult
	 * @return
	 */
	@RequestMapping("/district/check/fail")
	@ResponseBody
	public Object districtcheckFail(ComplaintPersonnelArchivesCheckResultDTO archivesCheckResult) {
		return CommonResponse.getResponse(archivesCheckService.checkFail(archivesCheckResult,false));
	}

	/**
	 * 功能描述: <br>
	 * 〈已审核记录页面〉
	 * @param
	 * @return  java.lang.String
	 * @author  Huangguochen
	 * @date  2019/2/25
	 */
	@RequestMapping("/check/records/index")
	public String checkRecordsIndex() {
		return "/hrms/complaint/personnel_archives_check_records_index";
	}


	/**
	 * 功能描述: <br>
	 * 〈查询已审核记录列表〉
	 * @param query
	 * @return  java.lang.Object
	 * @author  Huangguochen
	 * @date  2019/2/25
	 */
	@RequestMapping("/check/records/list")
	@ResponseBody
	public Object checkRecordsList(ComplaintPersonnelArchivesCheckQueryDTO query) {
		return CommonResponse.getSuccessResponse(archivesCheckRecordService.searchCheckRecord(query));
	}

	/**
	 * 功能描述: <br>
	 * 〈已审核记录查看〉
	 * @param recordId
	 * @param recordType
	 * @param model
	 * @return  java.lang.String
	 * @author  Huangguochen
	 * @date  2019/2/26
	 */
	@RequestMapping("/check/records/view")
	public String checkRecordsView(@RequestParam String recordId, @RequestParam Integer recordType, Model model) {
		model.addAttribute("recordId", recordId);
		model.addAttribute("recordType", recordType);
		ComplaintPersonnelArchivesCheckRecordVO record = archivesCheckRecordService.getArchivesCheckRecordByRecordId(recordId, recordType);
		String checkstatus = "";
		if (record != null) {
			model.addAttribute("illustrate",record.getIllustrate());
			model.addAttribute("checkTime",record.getUpdateTime());
			Integer result = record.getResult();
			  if (result == ComplaintModel.STATUS_ORG_SUCCESS) {
				  checkstatus = "机构审核通过";
			  }else if (result == ComplaintModel.STATUS_ORG_FAIL){
			  	checkstatus = "机构审核不通过";
			  }else if (result == ComplaintModel.STATUS_DISTRICT_SUCCESS){
			  	checkstatus = "行政审核通过";
			  }else if (result == ComplaintModel.STATUS_DISTRICT_FAIL){
				  checkstatus = "行政审核不通过";
			  }
			model.addAttribute("checkstatus",checkstatus);
		}
		return "/hrms/complaint/personnel_archives_check_record_view";
	}

	@RequestMapping("/check/record/view")
	@ResponseBody
	public Object checkRecordView(@RequestParam String recordId, @RequestParam Integer recordType, Model model) {
		ComplaintPersonnelArchivesCheckRecordVO record = archivesCheckRecordService.getArchivesCheckRecordByRecordId(recordId, recordType);
		if (record != null) {
			return CommonResponse.getSuccessResponse(JSON.parseObject(record.getCheckContent()));
		}else {
			return  CommonResponse.getFailResponse();
		}
	}

}
