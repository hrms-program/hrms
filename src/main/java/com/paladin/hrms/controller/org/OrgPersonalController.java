package com.paladin.hrms.controller.org;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.paladin.hrms.controller.org.pojo.*;
import com.paladin.hrms.core.HrmsUserSession;
import com.paladin.hrms.model.org.*;
import com.paladin.hrms.service.org.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.common.ExcelImportResult;
import com.paladin.framework.common.ExcelImportResult.ExcelImportError;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.excel.DefaultSheet;
import com.paladin.framework.excel.read.DefaultReadColumn;
import com.paladin.framework.excel.read.ExcelReadException;
import com.paladin.framework.excel.read.ExcelReader;
import com.paladin.framework.excel.read.ReadColumn;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.service.org.dto.OrgPersonnelBaseUploadDTO;
import com.paladin.hrms.service.org.dto.OrgPersonnelClaimDTO;
import com.paladin.hrms.service.org.dto.OrgPersonnelClaimQueryDTO;
import com.paladin.hrms.service.org.dto.OrgPersonnelExportQueryDTO;
import com.paladin.hrms.service.org.dto.OrgPersonnelPracticeUploadDTO;
import com.paladin.hrms.service.org.dto.OrgPersonnelYearAssessUploadDTO;
import com.paladin.hrms.service.org.dto.PhysicianAssessUploadDTO;

@Controller
@RequestMapping("/hrms/org/personal/")
public class OrgPersonalController extends ControllerSupport {

	@Autowired
	private OrgPersonnelService personnelService;

	@Autowired
	private OrgPersonnelEducationService personnelEducationService;

	@Autowired
	private SysAttachmentService attachmentService;

	@Autowired
	private OrgPersonnelJobService personnelJobService;

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
	private OrgPersonnelPracticeRegistrationService orgPracticeRegistrationService;

	@Autowired
	private OrgPersonnelYearAssessService orgPersonnelYearAssessService;

	@Autowired
	private OrgPersonnelScienceEducationService OrgScienceEducationService;

	@Autowired
	private OrgPersonnelExportService personnelExportService;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	@QueryInputMethod(queryClass = OrgPersonnelQuery.class)
	public String index() {
		return "/hrms/org/personal_index";
	}

	/**
	 * 查询
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping("/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = OrgPersonnelQuery.class, paramIndex = 0)
	public Object findAll(OrgPersonnelQuery query) {
		return CommonResponse.getSuccessResponse(personnelService.findPersonnelPage(query));
	}

	/**
	 * 我的档案
	 * 
	 * @return
	 */
	@RequestMapping("/self")
	public String self(Model model) {
		HrmsUserSession session = HrmsUserSession.getCurrentUserSession();
		if (session.isPersonalUser()) {
			model.addAttribute("id", session.getUserId());
			return "/hrms/org/personal_self";
		}
		throw new BusinessException("账号类型不对");
	}

	/**
	 * 编辑
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String view(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/hrms/org/personal_edit";
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "/hrms/org/personal_edit";
	}

	/**
	 * 获取个人信息详情（包括基本信息，工作信息，执业信息等）
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public Object detail(@RequestParam String id) {
		OrgPersonnelDetailVO detail = new OrgPersonnelDetailVO();
		detail.setBase(beanCopy(personnelService.get(id), new OrgPersonnelBaseVO()));
		detail.setJob(beanCopy(personnelJobService.get(id), new OrgPersonnelJobVO()));
		detail.setEducations(beanCopyList(personnelEducationService.getPersonnelEducations(id), OrgPersonnelEducationVO.class));
		detail.setPractice(beanCopy(personnelPracticeService.get(id), new OrgPersonnelPracticeVO()));
		detail.setExperience(beanCopyList(orgPersonnelWorkExperienceService.getPersonnelExperience(id), OrgPersonnelWorkExperienceVO.class));
		detail.setCultivate(beanCopyList(orgPersonnelCultivateService.getPersonnelCultivate(id), OrgPersonnelCultivateVO.class));
		detail.setPositionalInfo(beanCopyList(orgPersonnelPositionalInfoService.getPersonnelPositionalInfo(id), OrgPersonnelPositionalInfoVO.class));
		detail.setRegistrations(beanCopyList(orgPracticeRegistrationService.getPracticeRegistration(id), OrgPersonnelPracticeRegistrationVO.class));
		detail.setRewardInfo(beanCopyList(orgPersonnelRewardInfoService.getPersonnelRewardInfo(id), OrgPersonnelRewardInfoVO.class));
		detail.setYearassess(beanCopyList(orgPersonnelYearAssessService.getPersonnelYearAssess(id), OrgPersonnelYearAssessVO.class));
		detail.setScienceEducation(beanCopyList(OrgScienceEducationService.getScienceEducation(id), OrgPersonnelScienceEducationVO.class));
		return CommonResponse.getSuccessResponse(detail);
	}

	/**
	 * 获取基本信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/base")
	@ResponseBody
	public Object getBaseModel(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(beanCopy(personnelService.get(id), new OrgPersonnelBaseVO()));
	}

	/**
	 * 获取工作信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/job")
	@ResponseBody
	public Object getJobModel(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(beanCopy(personnelJobService.get(id), new OrgPersonnelJobVO()));
	}

	/**
	 * 获取执业信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/practice")
	@ResponseBody
	public Object getPracticeModel(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(beanCopy(personnelPracticeService.get(id), new OrgPersonnelPracticeVO()));
	}

	/**
	 * 获取教育经历信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/education")
	@ResponseBody
	public Object getEducationModel(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(beanCopy(personnelEducationService.get(id), new OrgPersonnelEducationVO()));
	}

	/**
	 * 获取工作经历信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/workExperience")
	@ResponseBody
	public Object getWorkExperienceModel(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(beanCopy(orgPersonnelWorkExperienceService.get(id), new OrgPersonnelWorkExperienceVO()));
	}

	/**
	 * 获取培训经历信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/cultivate")
	@ResponseBody
	public Object getCultivateModel(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(beanCopy(orgPersonnelCultivateService.get(id), new OrgPersonnelCultivateVO()));
	}

	/**
	 * 获取职称信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/positionalInfo")
	@ResponseBody
	public Object getPositionalInfoModel(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(beanCopy(orgPersonnelPositionalInfoService.get(id), new OrgPersonnelPositionalInfoVO()));
	}

	/**
	 * 获取执业注册信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/registration")
	@ResponseBody
	public Object getRegistrationModel(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(beanCopy(orgPracticeRegistrationService.get(id), new OrgPersonnelPracticeRegistrationVO()));
	}

	/**
	 * 获取奖励信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/rewardInfo")
	@ResponseBody
	public Object getRewardInfoModel(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(beanCopy(orgPersonnelRewardInfoService.get(id), new OrgPersonnelRewardInfoVO()));
	}

	/**
	 * 获取年度考核信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/yearAssess")
	@ResponseBody
	public Object getYearAssessModel(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(beanCopy(orgPersonnelYearAssessService.get(id), new OrgPersonnelYearAssessVO()));
	}

	/**
	 * 获取科研信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/scienceEducation")
	@ResponseBody
	public Object getScienceEducationModel(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(beanCopy(OrgScienceEducationService.get(id), new OrgPersonnelScienceEducationVO()));
	}

	/**
	 * 保存基本信息，并返回基本信息
	 * 
	 * @param base
	 * @return
	 */
	@RequestMapping("/save/base/add")
	@ResponseBody
	public Object addBaseModel(OrgPersonnelBaseDTO base) {
		OrgPersonnel model = beanCopy(base, new OrgPersonnel());
		model.setId(UUIDUtil.createUUID());
		if (personnelService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(model);
		}
		return CommonResponse.getFailResponse();
	}

	/**
	 * 保存基本信息，并返回基本信息
	 * 
	 * @param base
	 * @return
	 */
	@RequestMapping("/save/base/update")
	@ResponseBody
	public Object updateBaseModel(OrgPersonnelBaseUpdateDTO base) {
		String id = base.getId();
		OrgPersonnel personnel = personnelService.get(id);
		if (personnel == null) {
			return CommonResponse.getFailResponse("找不到对应人员信息");
		}

		OrgPersonnel model = beanCopy(base, personnel);
		if (personnelService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(personnelService.get(id), new OrgPersonnelBaseVO()));
		}
		return CommonResponse.getFailResponse();
	}

	/**
	 * 保存教育经历，并返回教育经历
	 * 
	 * @param education
	 * @return
	 */
	@RequestMapping("/save/education")
	@ResponseBody
	public Object saveEducationModel(OrgPersonnelEducationDTO education, @RequestParam(required = false) MultipartFile[] attachmentFiles) {
		List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(education.getAttachments(), attachmentFiles);
		if (attachments != null && attachments.size() > 4) {
			return CommonResponse.getErrorResponse("附件数量不能超过4张");
		}
		education.setAttachments(attachmentService.splicingAttachmentId(attachments));
		String id = education.getId();

		OrgPersonnelEducation model = beanCopy(education, (id == null || id.length() == 0) ? new OrgPersonnelEducation() : personnelEducationService.get(id));
		if (personnelEducationService.saveOrUpdate(model) > 0) {
			return CommonResponse
					.getSuccessResponse(beanCopyList(personnelEducationService.getPersonnelEducations(model.getPersonnelId()), OrgPersonnelEducationVO.class));
		}

		return CommonResponse.getFailResponse();
	}

	/**
	 * 保存工作信息，并返回基本信息
	 * 
	 * @param job
	 * @return
	 */
	@RequestMapping("/save/job")
	@ResponseBody
	public Object saveJobModel(OrgPersonnelJobDTO job, @RequestParam(required = false) MultipartFile[] attachmentFiles) {
		List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(job.getAttachments(), attachmentFiles);
		if (attachments != null && attachments.size() > 4) {
			return CommonResponse.getErrorResponse("附件数量不能超过4张");
		}

		job.setAttachments(attachmentService.splicingAttachmentId(attachments));
		int row = 0;
		String id = job.getId();
		OrgPersonnelJob model = personnelJobService.get(id);
		if (model == null) {
			model = beanCopy(job, new OrgPersonnelJob());
			row = personnelJobService.save(model);
		} else {
			model = beanCopy(job, model);
			row = personnelJobService.update(model);
		}
		if (row > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(personnelJobService.get(id), new OrgPersonnelJobVO()));
		}
		return CommonResponse.getValidFailResponse();
	}

	/**
	 * 保存工作经历，并返回工作经历
	 * 
	 * @param workExperience
	 * @return
	 */
	@RequestMapping("/save/workExperience")
	@ResponseBody
	public Object saveworkExperience(OrgPersonnelWorkExperienceDTO workExperience, @RequestParam(required = false) MultipartFile[] attachmentFiles) {
		List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(workExperience.getAttachments(), attachmentFiles);
		if (attachments != null && attachments.size() > 4) {
			return CommonResponse.getErrorResponse("附件数量不能超过4张");
		}

		workExperience.setAttachments(attachmentService.splicingAttachmentId(attachments));
		String id = workExperience.getId();

		OrgPersonnelWorkExperience model = beanCopy(workExperience,
				(id == null || id.length() == 0) ? new OrgPersonnelWorkExperience() : orgPersonnelWorkExperienceService.get(id));
		if (orgPersonnelWorkExperienceService.saveOrUpdate(model) > 0) {
			return CommonResponse.getSuccessResponse(
					beanCopyList(orgPersonnelWorkExperienceService.getPersonnelExperience(model.getPersonnelId()), OrgPersonnelWorkExperienceVO.class));
		}

		return CommonResponse.getValidFailResponse();
	}

	/**
	 * 保存执业信息，并返回基本信息
	 * 
	 * @param job
	 * @return
	 */
	@RequestMapping("/save/practice")
	@ResponseBody
	public Object saveJobModel(OrgPersonnelPracticeDTO practice) {
		int row = 0;
		String id = practice.getId();
		OrgPersonnelPractice model = personnelPracticeService.get(id);
		if (model == null) {
			model = beanCopy(practice, new OrgPersonnelPractice());
			row = personnelPracticeService.save(model);
		} else {
			model = beanCopy(practice, model);
			row = personnelPracticeService.update(model);
		}
		if (row > 0) {
			return CommonResponse.getSuccessResponse(beanCopy(personnelPracticeService.get(id), new OrgPersonnelPracticeVO()));
		}
		return CommonResponse.getValidFailResponse();
	}

	/**
	 * 保存职称信息，并返回职称信息
	 * 
	 * @param positionalInfo
	 * @return
	 */
	@RequestMapping("/save/positionalInfo")
	@ResponseBody
	public Object savePositionalInfo(OrgPersonnelPositionalInfoDTO positionalInfo, @RequestParam(required = false) MultipartFile[] attachmentFiles) {
		List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(positionalInfo.getAttachments(), attachmentFiles);
		if (attachments != null && attachments.size() > 4) {
			return CommonResponse.getErrorResponse("附件数量不能超过4张");
		}

		positionalInfo.setAttachments(attachmentService.splicingAttachmentId(attachments));
		String id = positionalInfo.getId();

		OrgPersonnelPositionalInfo model = beanCopy(positionalInfo,
				(id == null || id.length() == 0) ? new OrgPersonnelPositionalInfo() : orgPersonnelPositionalInfoService.get(id));
		if (orgPersonnelPositionalInfoService.saveOrUpdate(model) > 0) {
			return CommonResponse.getSuccessResponse(
					beanCopyList(orgPersonnelPositionalInfoService.getPersonnelPositionalInfo(model.getPersonnelId()), OrgPersonnelPositionalInfoVO.class));
		}

		return CommonResponse.getFailResponse();
	}

	/**
	 * 保存科教信息，并返回科教信息
	 * 
	 * @param positionalInfo
	 * @return
	 */
	@RequestMapping("/save/scienceEducation")
	@ResponseBody
	public Object saveScienceEducation(OrgPersonnelScienceEducationDTO scienceEducation, @RequestParam(required = false) MultipartFile[] attachmentFiles) {
		List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(scienceEducation.getAttachments(), attachmentFiles);
		if (attachments != null && attachments.size() > 4) {
			return CommonResponse.getErrorResponse("附件数量不能超过4张");
		}

		scienceEducation.setAttachments(attachmentService.splicingAttachmentId(attachments));
		String id = scienceEducation.getId();

		OrgPersonnelScienceEducation model = beanCopy(scienceEducation,
				(id == null || id.length() == 0) ? new OrgPersonnelScienceEducation() : OrgScienceEducationService.get(id));
		if (OrgScienceEducationService.saveOrUpdate(model) > 0) {
			return CommonResponse.getSuccessResponse(
					beanCopyList(OrgScienceEducationService.getScienceEducation(model.getPersonnelId()), OrgPersonnelScienceEducationVO.class));
		}

		return CommonResponse.getFailResponse();
	}

	/**
	 * 保存奖励信息，并返回奖励信息
	 * 
	 * @param rewardInfo
	 * @return
	 */
	@RequestMapping("/save/rewardInfo")
	@ResponseBody
	public Object saveRewardInfo(OrgPersonnelRewardInfoDTO rewardInfo, @RequestParam(required = false) MultipartFile[] attachmentFiles) {
		List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(rewardInfo.getAttachments(), attachmentFiles);
		if (attachments != null && attachments.size() > 4) {
			return CommonResponse.getErrorResponse("附件数量不能超过4张");
		}

		rewardInfo.setAttachments(attachmentService.splicingAttachmentId(attachments));
		String id = rewardInfo.getId();

		OrgPersonnelRewardInfo model = beanCopy(rewardInfo,
				(id == null || id.length() == 0) ? new OrgPersonnelRewardInfo() : orgPersonnelRewardInfoService.get(id));
		if (orgPersonnelRewardInfoService.saveOrUpdate(model) > 0) {
			return CommonResponse.getSuccessResponse(
					beanCopyList(orgPersonnelRewardInfoService.getPersonnelRewardInfo(model.getPersonnelId()), OrgPersonnelRewardInfoVO.class));
		}

		return CommonResponse.getFailResponse();
	}

	/**
	 * 保存培训经历，并返回培训经历
	 * 
	 * @param cultivate
	 * @return
	 */
	@RequestMapping("/save/cultivate")
	@ResponseBody
	public Object saveCultivate(OrgPersonnelCultivateDTO cultivate, @RequestParam(required = false) MultipartFile[] attachmentFiles) {
		List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(cultivate.getAttachments(), attachmentFiles);
		if (attachments != null && attachments.size() > 4) {
			return CommonResponse.getErrorResponse("附件数量不能超过4张");
		}

		cultivate.setAttachments(attachmentService.splicingAttachmentId(attachments));
		String id = cultivate.getId();

		OrgPersonnelCultivate model = beanCopy(cultivate,
				(id == null || id.length() == 0) ? new OrgPersonnelCultivate() : orgPersonnelCultivateService.get(id));
		if (orgPersonnelCultivateService.saveOrUpdate(model) > 0) {
			return CommonResponse.getSuccessResponse(
					beanCopyList(orgPersonnelCultivateService.getPersonnelCultivate(model.getPersonnelId()), OrgPersonnelCultivateVO.class));
		}

		return CommonResponse.getFailResponse();
	}

	/**
	 * 保存年度考核，并返回年度考核
	 * 
	 * @param yearassess
	 * @return
	 */
	@RequestMapping("/save/yearassess")
	@ResponseBody
	public Object saveYearassess(OrgPersonnelYearAssessDTO yearassess) {

		String id = yearassess.getId();

		OrgPersonnelYearAssess model = beanCopy(yearassess,
				(id == null || id.length() == 0) ? new OrgPersonnelYearAssess() : orgPersonnelYearAssessService.get(id));
		if (orgPersonnelYearAssessService.saveOrUpdate(model) > 0) {
			return CommonResponse.getSuccessResponse(
					beanCopyList(orgPersonnelYearAssessService.getPersonnelYearAssess(model.getPersonnelId()), OrgPersonnelYearAssessVO.class));
		}

		return CommonResponse.getFailResponse();
	}

	/**
	 * 上传头像
	 * 
	 * @param base
	 * @return
	 */
	@RequestMapping("/upload/profile")
	@ResponseBody
	public Object uploadProfilePicture(@RequestParam String personnelId, @RequestParam MultipartFile profileFile) {
		SysAttachment attachment = attachmentService.createAttachment(profileFile, null);
		if (attachment != null) {
			return CommonResponse.getResponse(personnelService.updateProfilePhoto(personnelId, attachment.getId()));
		}
		return CommonResponse.getFailResponse();
	}

	@RequestMapping("/upload/index")
	public Object uploadIndex() {
		return "/hrms/org/personal_upload";
	}

	// Excel读列可以重用，不用每次导入时生成
	private static final List<ReadColumn> personnelBaseUploadColumns = DefaultReadColumn.createReadColumn(OrgPersonnelBaseUploadDTO.class);
	private static final List<ReadColumn> orgPersonnelPracticeUploadColumns = DefaultReadColumn.createReadColumn(OrgPersonnelPracticeUploadDTO.class);
	private static final List<ReadColumn> physicianAssessUploadColumns = DefaultReadColumn.createReadColumn(PhysicianAssessUploadDTO.class);
	private static final List<ReadColumn> orgPersonnelYearAssessUploadColumns = DefaultReadColumn.createReadColumn(OrgPersonnelYearAssessUploadDTO.class);

	/**
	 * 基本信息Excel表导入
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload/base")
	@ResponseBody
	public Object uploadBase(@RequestParam("file") MultipartFile file) {
		try {
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			ExcelReader<OrgPersonnelBaseUploadDTO> reader = new ExcelReader<>(OrgPersonnelBaseUploadDTO.class, personnelBaseUploadColumns,
					new DefaultSheet(workbook.getSheetAt(0)), 1);

			int maxImportCount = 1000;

			List<ExcelImportError> importErrors = new ArrayList<>();
			int index = 1;
			while (reader.hasNext()) {
				try {
					index++;
					OrgPersonnelBaseUploadDTO dto = reader.readRow();
					String errorMsg = null;
					if (dto != null) {
						errorMsg = personnelService.importBase(dto);
					} else {
						errorMsg = "空行";
					}

					if (errorMsg != null) {
						importErrors.add(new ExcelImportError(index, errorMsg));
					}
				} catch (Exception e) {
					e.printStackTrace();
					importErrors.add(new ExcelImportError(index, e));
				}

				if (index > maxImportCount) {
					break;
				}
			}

			return CommonResponse.getSuccessResponse(new ExcelImportResult(index - 1, importErrors));
		} catch (IOException e) {
			return CommonResponse.getFailResponse("上传文件异常，请检查是否基于下载的模板编辑！");
		}
	}

	/**
	 * 执业信息Excel表导入
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload/practice")
	@ResponseBody
	public Object uploadPractice(@RequestParam("file") MultipartFile file) {
		try {
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			ExcelReader<OrgPersonnelPracticeUploadDTO> reader = new ExcelReader<>(OrgPersonnelPracticeUploadDTO.class, orgPersonnelPracticeUploadColumns,
					new DefaultSheet(workbook.getSheetAt(0)), 1);

			int maxImportCount = 1000;

			List<ExcelImportError> importErrors = new ArrayList<>();
			int index = 1;
			while (reader.hasNext()) {
				try {
					index++;
					OrgPersonnelPracticeUploadDTO dto = reader.readRow();
					String errorMsg = null;
					if (dto != null) {
						errorMsg = personnelService.importPractice(dto);
					} else {
						errorMsg = "空行";
					}

					if (errorMsg != null) {
						importErrors.add(new ExcelImportError(index, errorMsg));
					}
				} catch (ExcelReadException e) {
					importErrors.add(new ExcelImportError(index, e.getMessage()));
				}

				if (index > maxImportCount) {
					break;
				}
			}

			return CommonResponse.getSuccessResponse(new ExcelImportResult(index - 1, importErrors));
		} catch (IOException e) {
			return CommonResponse.getFailResponse("上传文件异常，请检查是否基于下载的模板编辑！");
		}
	}

	/**
	 * 医师考核Excel表导入
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload/assess")
	@ResponseBody
	public Object uploadPhysicianAssess(@RequestParam("file") MultipartFile file) {
		try {
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			ExcelReader<PhysicianAssessUploadDTO> reader = new ExcelReader<>(PhysicianAssessUploadDTO.class, physicianAssessUploadColumns,
					new DefaultSheet(workbook.getSheetAt(0)), 1);

			int maxImportCount = 1000;

			List<ExcelImportError> importErrors = new ArrayList<>();
			int index = 1;
			while (reader.hasNext()) {
				try {
					index++;
					PhysicianAssessUploadDTO dto = reader.readRow();
					String errorMsg = null;
					if (dto != null) {
						errorMsg = personnelService.importPhysicianAssess(dto);
					} else {
						errorMsg = "空行";
					}

					if (errorMsg != null) {
						importErrors.add(new ExcelImportError(index, errorMsg));
					}
				} catch (ExcelReadException e) {
					e.getStackTrace();
					importErrors.add(new ExcelImportError(index, e.getMessage()));
				}

				if (index > maxImportCount) {
					break;
				}
			}

			return CommonResponse.getSuccessResponse(new ExcelImportResult(index - 1, importErrors));
		} catch (IOException e) {
			return CommonResponse.getFailResponse("上传文件异常，请检查是否基于下载的模板编辑！");
		}
	}

	/**
	 * 年度考核Excel表导入
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload/yearAssess")
	@ResponseBody
	public Object uploadYearAssess(@RequestParam("file") MultipartFile file) {
		try {
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			ExcelReader<OrgPersonnelYearAssessUploadDTO> reader = new ExcelReader<>(OrgPersonnelYearAssessUploadDTO.class, orgPersonnelYearAssessUploadColumns,
					new DefaultSheet(workbook.getSheetAt(0)), 1);

			int maxImportCount = 1000;

			List<ExcelImportError> importErrors = new ArrayList<>();
			int index = 1;
			while (reader.hasNext()) {
				try {
					index++;
					OrgPersonnelYearAssessUploadDTO dto = reader.readRow();
					String errorMsg = null;
					if (dto != null) {
						errorMsg = personnelService.importYearAssess(dto);
					} else {
						errorMsg = "空行";
					}

					if (errorMsg != null) {
						importErrors.add(new ExcelImportError(index, errorMsg));
					}
				} catch (ExcelReadException e) {
					e.printStackTrace();
					importErrors.add(new ExcelImportError(index, e.getMessage()));
				}

				if (index > maxImportCount) {
					break;
				}
			}

			return CommonResponse.getSuccessResponse(new ExcelImportResult(index - 1, importErrors));
		} catch (IOException e) {
			return CommonResponse.getFailResponse("上传文件异常，请检查是否基于下载的模板编辑！");
		}
	}

	/**
	 * 功能描述: <br>
	 * 〈注销人员认领首页〉
	 * 
	 * @param
	 * @return:java.lang.String
	 */
	@RequestMapping("/claim/index")
	@QueryInputMethod(queryClass = OrgPersonnelClaimQueryDTO.class)
	public String claimIndex() {
		return "/hrms/org/personal_claim_index";
	}

	/**
	 * 功能描述: <br>
	 * 〈查询所有〉
	 * 
	 * @param query
	 * @return:java.lang.Object
	 */
	@RequestMapping("/claim/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = OrgPersonnelClaimQueryDTO.class, paramIndex = 0)
	public Object findAll(OrgPersonnelClaimQueryDTO query) {
		return CommonResponse.getSuccessResponse(personnelService.findLevelPersonnelPage(query));
	}

	/**
	 * 离岗
	 * 
	 */
	@RequestMapping("/leave")
	@ResponseBody
	public Object leave(String id) {
		return CommonResponse.getResponse(personnelService.levelPersonnel(id));
	}

	/**
	 * 功能描述: <br>
	 * 〈确认认领〉
	 * 
	 * @param id
	 * @param identificationNo
	 * @param name
	 * @return:java.lang.Object
	 */
	@RequestMapping("/claim/confirm")
	@ResponseBody
	public Object confirm(OrgPersonnelClaimDTO personnelClaimDTO) {
		return CommonResponse.getResponse(personnelService.claimPersonnel(personnelClaimDTO));
	}

	@RequestMapping("/export/index")
	public String exportIndex() {
		return "/hrms/org/personnel_export";
	}

	@RequestMapping("/export/find")
	@ResponseBody
	public Object exportFind(OrgPersonnelExportQueryDTO query) {
		return CommonResponse.getSuccessResponse(personnelExportService.findSimpleExportPersonnel(query));
	}

	@RequestMapping("/export/do")
	public void exportDo(OrgPersonnelExportQueryDTO query, @RequestParam("column") String[] columns, HttpServletResponse response) {
		try {
			// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
			response.setContentType("multipart/form-data");
			// 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
			response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("人员信息.xlsx", "UTF-8"));
			personnelExportService.export(columns, query, response.getOutputStream());
		} catch (IOException e) {
			throw new BusinessException("导出人员信息失败");
		}
	}

}
