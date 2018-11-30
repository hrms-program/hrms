package com.paladin.hrms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.hrms.controller.syst.pojo.SysAnnouncementQuery;
import com.paladin.hrms.controller.syst.pojo.SysAnnouncementVO;
import com.paladin.hrms.model.syst.SysAnnouncement;
import com.paladin.hrms.service.complaint.ComplaintPersonnelArchivesCheckRecordService;
import com.paladin.hrms.service.complaint.ComplaintPersonnelArchivesCheckService;
import com.paladin.hrms.service.syst.SysAnnouncementService;

@Controller
@RequestMapping("/hrms/manager")
public class IndexController extends ControllerSupport{
	@Autowired
	private SysAnnouncementService sysAnnouncementService;
	
	@Autowired
	private ComplaintPersonnelArchivesCheckService archivesCheckService;
	@Autowired
	ComplaintPersonnelArchivesCheckRecordService complaintPersonnelArchivesCheckRecordService;
	
	@RequestMapping(value = "/index")
	@ResponseBody
	@QueryOutputMethod(queryClass = SysAnnouncementQuery.class, paramIndex = 0)
	public ModelAndView findAll(SysAnnouncementQuery query) {
		query.setLimit(8);
		query.setOffset(0);
		ModelAndView mav=new ModelAndView();
		PageResult<SysAnnouncement>pageResult=sysAnnouncementService.searchPage(query);
		List<SysAnnouncementVO> sysAnnouncementVOList = beanCopyList(pageResult.getData(), SysAnnouncementVO.class);
	    mav.addObject("notices", sysAnnouncementVOList);
	    mav.addObject("things", complaintPersonnelArchivesCheckRecordService.indexShowCount());
	    mav.setViewName("/hrms/index_content");
		return mav;
	}
	
}
