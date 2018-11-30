package com.paladin.hrms.controller.syst;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.syst.pojo.SysAnnouncementDTO;
import com.paladin.hrms.controller.syst.pojo.SysAnnouncementQuery;
import com.paladin.hrms.controller.syst.pojo.SysAnnouncementVO;
import com.paladin.hrms.model.syst.SysAnnouncement;
import com.paladin.hrms.service.syst.SysAnnouncementService;

@Controller
@RequestMapping("/hrms/system/announcement")
public class SysAnnouncementController extends ControllerSupport {
	@Autowired
	private SysAnnouncementService sysAnnouncementService;
	@Autowired
	private SysAttachmentService attachmentService;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	@QueryInputMethod(queryClass = SysAnnouncementQuery.class)
	public String index() {
		return "/hrms/syst/announcement_index";
	}

	/**
	 * 查询
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping("/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = SysAnnouncementQuery.class, paramIndex = 0)
	public Object findAll(SysAnnouncementQuery query) {
		return CommonResponse.getSuccessResponse(sysAnnouncementService.searchPage(query));
	}

	/**
	 * 详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/view")
	public String view(@RequestParam String id, Model model) {
		SysAnnouncement sysAnnouncement = sysAnnouncementService.get(id);
		if (sysAnnouncement == null) {
			throw new BusinessException("查看的内容不存在");
		}
		SysAnnouncementVO sysAnnouncementVO = beanIncompleteCopy(sysAnnouncement, new SysAnnouncementVO());
		model.addAttribute("object", sysAnnouncementVO);

		return "/hrms/syst/announcement_view";
	}

	/**
	 * 新增
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("object", new SysAnnouncementVO());
		model.addAttribute("isAdd", true);
		return "/hrms/syst/announcement_edit";
	}

	@RequestMapping("/edit")
	public String toEdit(@RequestParam(required = false) String id, Model model) {
		model.addAttribute("id", id);
		return "/hrms/syst/announcement_edit";
	}

	/**
	 * 编辑
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public Object edit(@RequestParam String id, ModelAndView model) {
		SysAnnouncement sysAnnouncement = sysAnnouncementService.get(id);
		if (sysAnnouncement == null) {
			throw new BusinessException("编辑的内容不存在");
		}
		SysAnnouncementVO sysAnnouncementVO = beanIncompleteCopy(sysAnnouncement, new SysAnnouncementVO());
		return CommonResponse.getSuccessResponse(sysAnnouncementVO);
	}

	/**
	 * 保存
	 * 
	 * @param publicityMessage
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object addSave(@Valid SysAnnouncementDTO sysAnnouncementDTO, BindingResult bindingResult,
			@RequestParam(required = false) MultipartFile[] attachmentFiles) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(sysAnnouncementDTO.getAttachments(), attachmentFiles);
		if (attachments != null && attachments.size() > 4) {
			return CommonResponse.getErrorResponse("附件数量不能超过4张");
		}

		sysAnnouncementDTO.setAttachments(attachmentService.splicingAttachmentId(attachments));
		String id = sysAnnouncementDTO.getId();
		if (id != null && id.length() != 0) {
			SysAnnouncement sysAnnouncement = sysAnnouncementService.get(id);

			if (sysAnnouncement == null) {
				throw new BusinessException("编辑的用户不存在");
			}

			beanIncompleteCopy(sysAnnouncementDTO, sysAnnouncement);
			return CommonResponse.getResponse(sysAnnouncementService.updateSelective(sysAnnouncement));
		} else {
			SysAnnouncement sysAnnouncement = beanCompleteCopy(sysAnnouncementDTO, new SysAnnouncement());
			sysAnnouncement.setPublishedTime(new Date());
			return CommonResponse.getResponse(sysAnnouncementService.save(sysAnnouncement));
		}
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	@ResponseBody
	public Object remove(@RequestParam String id) {
		return CommonResponse.getResponse(sysAnnouncementService.removeByPrimaryKey(id));
	}

}
