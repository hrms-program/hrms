package com.paladin.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.hrms.controller.syst.pojo.SysAnnouncementQuery;
import com.paladin.hrms.service.complaint.ComplaintPersonnelArchivesCheckRecordService;
import com.paladin.hrms.service.syst.SysAnnouncementService;

@Controller
@RequestMapping("/hrms/manager")
public class IndexController extends ControllerSupport {
	@Autowired
	private SysAnnouncementService sysAnnouncementService;
	
	@Autowired
	private ComplaintPersonnelArchivesCheckRecordService complaintPersonnelArchivesCheckRecordService;

	@RequestMapping(value = "/index")
	public ModelAndView index(SysAnnouncementQuery query) {
		
		query.setLimit(8);
		query.setOffset(0);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("notices", sysAnnouncementService.searchPage(query).getData());
		mav.addObject("things", complaintPersonnelArchivesCheckRecordService.indexShowCount());
		mav.setViewName("/hrms/index_content");
		return mav;
	}

}
