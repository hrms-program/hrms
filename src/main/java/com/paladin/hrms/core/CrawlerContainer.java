package com.paladin.hrms.core;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.spring.SpringContainer;
import com.paladin.hrms.crawler.AgencyDetailCrawler;
import com.paladin.hrms.crawler.AgencyPageCrawler;
import com.paladin.hrms.crawler.CultivateCrawler;
import com.paladin.hrms.crawler.FileCrawler;
import com.paladin.hrms.crawler.FileCrawler.FileResult;
import com.paladin.hrms.crawler.PersonnelEducationCrawler;
import com.paladin.hrms.crawler.PersonnelInfoCrawler;
import com.paladin.hrms.crawler.PersonnelJobInfoCrawler;
import com.paladin.hrms.crawler.PersonnelPagerCrawler;
import com.paladin.hrms.crawler.PersonnelPracticeCrawler;
import com.paladin.hrms.crawler.PersonnelWorkExperienceCrawler;
import com.paladin.hrms.crawler.PositionalInfoCrawler;
import com.paladin.hrms.crawler.PracticeRegistrationCrawler;
import com.paladin.hrms.crawler.RewardInfoCrawler;
import com.paladin.hrms.crawler.ScienceEducationCrawler;
import com.paladin.hrms.crawler.YearAssessCrawler;
import com.paladin.hrms.crawler.pojo.OrgPersonnelCultivateCrawler;
import com.paladin.hrms.crawler.pojo.OrgPersonnelEducationCrawler;
import com.paladin.hrms.crawler.pojo.OrgPersonnelJobCrewler;
import com.paladin.hrms.crawler.pojo.OrgPersonnelPositionalInfoCrawler;
import com.paladin.hrms.crawler.pojo.OrgPersonnelPracticeRegistrationCrawler;
import com.paladin.hrms.crawler.pojo.OrgPersonnelRewardInfoCrawler;
import com.paladin.hrms.crawler.pojo.OrgPersonnelScienceEducationCrawler;
import com.paladin.hrms.crawler.pojo.OrgPersonnelWorkExperienceCrawler;
import com.paladin.hrms.crawler.pojo.OrgPersonnelYearAssessCrawler;
import com.paladin.hrms.mapper.org.OrgPersonnelMapper;
import com.paladin.hrms.model.org.OrgAgency;
import com.paladin.hrms.model.org.OrgAgencyDetail;
import com.paladin.hrms.model.org.OrgPersonnel;
import com.paladin.hrms.model.org.OrgPersonnelCultivate;
import com.paladin.hrms.model.org.OrgPersonnelEducation;
import com.paladin.hrms.model.org.OrgPersonnelJob;
import com.paladin.hrms.model.org.OrgPersonnelPositionalInfo;
import com.paladin.hrms.model.org.OrgPersonnelPractice;
import com.paladin.hrms.model.org.OrgPersonnelPracticeRegistration;
import com.paladin.hrms.model.org.OrgPersonnelRewardInfo;
import com.paladin.hrms.model.org.OrgPersonnelScienceEducation;
import com.paladin.hrms.model.org.OrgPersonnelWorkExperience;
import com.paladin.hrms.model.org.OrgPersonnelYearAssess;
import com.paladin.hrms.crawler.AgencyDetailCrawler.AgencyResult;
import com.paladin.hrms.crawler.PersonnelPagerCrawler.AdditionalData;
import com.paladin.hrms.service.org.OrgAgencyDetailService;
import com.paladin.hrms.service.org.OrgAgencyService;
import com.paladin.hrms.service.org.OrgPersonnelCultivateService;
import com.paladin.hrms.service.org.OrgPersonnelEducationService;
import com.paladin.hrms.service.org.OrgPersonnelJobService;
import com.paladin.hrms.service.org.OrgPersonnelPositionalInfoService;
import com.paladin.hrms.service.org.OrgPersonnelPracticeRegistrationService;
import com.paladin.hrms.service.org.OrgPersonnelPracticeService;
import com.paladin.hrms.service.org.OrgPersonnelRewardInfoService;
import com.paladin.hrms.service.org.OrgPersonnelScienceEducationService;
import com.paladin.hrms.service.org.OrgPersonnelWorkExperienceService;
import com.paladin.hrms.service.org.OrgPersonnelYearAssessService;

/**
 * 爬取容器类
 * 
 * @author jisanjie
 * @version 2018年12月14日 上午10:10:41
 */
@Component
public class CrawlerContainer implements SpringContainer {

	private Logger logger = LoggerFactory.getLogger(CrawlerContainer.class);

	@Autowired
	private OrgAgencyService agencyService;

	@Autowired
	private OrgAgencyDetailService agencyDetailService;

	@Autowired
	private OrgPersonnelMapper personnelMapper;

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
	private OrgPersonnelScienceEducationService scienceEducationService;

	public boolean afterInitialize() {
		logger.info("不需要爬取数据");
		return true;
	};

	/**
	 * 将爬取到的数据存入数据库
	 * 
	 * @throws IOException
	 * 
	 */
	public void crawlAgencyData() throws IOException {
		AgencyDetailCrawler detailCrawler = new AgencyDetailCrawler();
		AgencyPageCrawler pageCrawler = new AgencyPageCrawler();

		List<String> agencyIds = pageCrawler.getAgencyIds();

		for (String agencyId : agencyIds) {
			try {
				AgencyResult result = detailCrawler.parseAgencyDetail(agencyId);

				OrgAgency agency = result.getOrgAgency();
				OrgAgencyDetail agencyDetail = result.getOrgAgencyDetail();

				agency.setCityCode(3205831);
				agency.setDistrictCode(3205831);

				agencyService.save(agency);
				agencyDetailService.save(agencyDetail);
			} catch (Exception e) {
				logger.info("读取机构失败[ID:" + agencyId + "]", e);
			}
		}
	}

	private PersonnelPagerCrawler pageCrawler = new PersonnelPagerCrawler();

	private PersonnelInfoCrawler baseCrawler = new PersonnelInfoCrawler();
	private PersonnelJobInfoCrawler jobCrawler = new PersonnelJobInfoCrawler();
	private PersonnelPracticeCrawler practiceCrawler = new PersonnelPracticeCrawler();

	private PersonnelEducationCrawler educationCrawler = new PersonnelEducationCrawler();
	private PersonnelWorkExperienceCrawler workExperienceCrawler = new PersonnelWorkExperienceCrawler();
	private PositionalInfoCrawler positionalInfoCrawler = new PositionalInfoCrawler();
	private PracticeRegistrationCrawler practiceRegistrationCrawler = new PracticeRegistrationCrawler();
	private RewardInfoCrawler rewardInfoCrawler = new RewardInfoCrawler();
	private ScienceEducationCrawler scienceEducationCrawler = new ScienceEducationCrawler();
	private YearAssessCrawler yearAssessCrawler = new YearAssessCrawler();
	private CultivateCrawler cultivateCrawler = new CultivateCrawler();

	public void crawPersonnelBase(String personnelId, Map<String, String> agencyNameMap, AdditionalData additionalData) throws IOException {
		// 人员信息
		OrgPersonnel personnel = baseCrawler.crawl(personnelId);

		String agencyName = additionalData.getAgencyName();
		if (agencyName == null) {
			throw new RuntimeException("机构为NULL");
		}

		String agencyId = agencyNameMap.get(agencyName);
		if (agencyId == null) {
			// throw new RuntimeException("找不到对应机构：" + agencyName);
			agencyId = "unknown";
		}

		personnel.setId(personnelId);
		personnel.setAgencyId(agencyId);
		personnel.setAgencyName(agencyName);
		personnel.setCityCode(3205831);
		personnel.setDistrictCode(3205831);

		personnel.setCreateTime(new Date());
		personnel.setCreateUserId("admin");
		personnel.setUpdateTime(new Date());
		personnel.setUpdateUserId("admin");

		personnelMapper.insert(personnel);
	}

	public void crawPersonnelJob(String personnelId) {
		try {
			OrgPersonnelJobCrewler jobData = jobCrawler.crewl(personnelId);

			OrgPersonnelJob job = new OrgPersonnelJob();
			SimpleBeanCopyUtil.simpleCopy(jobData, job);

			job.setId(personnelId);
			job.setAttachments(createAttachments(personnelId, jobData.getUrl()));

			personnelJobService.save(job);
		} catch (Exception e) {
			logger.info("人员[ID:" + personnelId + "]爬取工作信息失败:" + e.getMessage());
		}
	}

	public void crawPersonnelPractice(String personnelId, AdditionalData additionalData) {
		try {
			Integer personnelType = additionalData.getType();
			OrgPersonnelPractice practice = practiceCrawler.crawl(personnelId, personnelType);
			practice.setId(personnelId);
			personnelPracticeService.save(practice);
		} catch (Exception e) {
			logger.info("人员[ID:" + personnelId + "]爬取执业信息失败:" + e.getMessage());
		}
	}

	public void crawPersonnelEducation(String personnelId) {
		try {
			List<OrgPersonnelEducationCrawler> educationDatas = educationCrawler.crawl(personnelId);
			for (OrgPersonnelEducationCrawler educationData : educationDatas) {
				OrgPersonnelEducation education = new OrgPersonnelEducation();
				SimpleBeanCopyUtil.simpleCopy(educationData, education);

				education.setPersonnelId(personnelId);
				education.setAttachments(createAttachments(personnelId, educationData.getUrl()));

				personnelEducationService.save(education);
			}
		} catch (Exception e) {
			logger.info("人员[ID:" + personnelId + "]爬取教育经历失败:" + e.getMessage());
		}
	}

	public void crawPersonnelWorkExperience(String personnelId) {
		try {
			List<OrgPersonnelWorkExperienceCrawler> datas = workExperienceCrawler.crawl(personnelId);
			for (OrgPersonnelWorkExperienceCrawler data : datas) {
				OrgPersonnelWorkExperience model = new OrgPersonnelWorkExperience();
				SimpleBeanCopyUtil.simpleCopy(data, model);

				model.setPersonnelId(personnelId);
				model.setAttachments(createAttachments(personnelId, data.getUrl()));

				orgPersonnelWorkExperienceService.save(model);
			}
		} catch (Exception e) {
			logger.info("人员[ID:" + personnelId + "]爬取工作经历失败:" + e.getMessage());
		}
	}

	public void crawPersonnelPositionalInfo(String personnelId) {
		try {
			List<OrgPersonnelPositionalInfoCrawler> datas = positionalInfoCrawler.crawl(personnelId);
			for (OrgPersonnelPositionalInfoCrawler data : datas) {
				OrgPersonnelPositionalInfo model = new OrgPersonnelPositionalInfo();
				SimpleBeanCopyUtil.simpleCopy(data, model);

				model.setPersonnelId(personnelId);
				model.setAttachments(createAttachments(personnelId, data.getUrl()));

				orgPersonnelPositionalInfoService.save(model);
			}
		} catch (Exception e) {
			logger.info("人员[ID:" + personnelId + "]爬取职称失败:" + e.getMessage());
		}
	}

	public void crawPersonnelPracticeRegistration(String personnelId) {
		try {
			List<OrgPersonnelPracticeRegistrationCrawler> datas = practiceRegistrationCrawler.crawl(personnelId);
			for (OrgPersonnelPracticeRegistrationCrawler data : datas) {
				OrgPersonnelPracticeRegistration model = new OrgPersonnelPracticeRegistration();
				SimpleBeanCopyUtil.simpleCopy(data, model);

				model.setPersonnelId(personnelId);

				orgPracticeRegistrationService.save(model);
			}
		} catch (Exception e) {
			logger.info("人员[ID:" + personnelId + "]爬取执业注册失败:" + e.getMessage());
		}
	}

	public void crawPersonnelRewardInfo(String personnelId) {
		try {
			List<OrgPersonnelRewardInfoCrawler> datas = rewardInfoCrawler.crawl(personnelId);
			for (OrgPersonnelRewardInfoCrawler data : datas) {
				OrgPersonnelRewardInfo model = new OrgPersonnelRewardInfo();
				SimpleBeanCopyUtil.simpleCopy(data, model);

				model.setPersonnelId(personnelId);
				model.setAttachments(createAttachments(personnelId, data.getUrl()));

				orgPersonnelRewardInfoService.save(model);
			}
		} catch (Exception e) {
			logger.info("人员[ID:" + personnelId + "]爬取奖励信息失败:" + e.getMessage());
		}
	}

	public void crawPersonnelScienceEducation(String personnelId) {
		try {
			List<OrgPersonnelScienceEducationCrawler> datas = scienceEducationCrawler.crawl(personnelId);
			for (OrgPersonnelScienceEducationCrawler data : datas) {
				OrgPersonnelScienceEducation model = new OrgPersonnelScienceEducation();
				SimpleBeanCopyUtil.simpleCopy(data, model);

				model.setPersonnelId(personnelId);
				model.setAttachments(createAttachments(personnelId, data.getUrl()));

				scienceEducationService.save(model);
			}
		} catch (Exception e) {
			logger.info("人员[ID:" + personnelId + "]爬取科研信息失败:" + e.getMessage());
		}

	}

	public void crawPersonnelYearAssess(String personnelId) {
		try {
			List<OrgPersonnelYearAssessCrawler> datas = yearAssessCrawler.crawl(personnelId);
			for (OrgPersonnelYearAssessCrawler data : datas) {
				OrgPersonnelYearAssess model = new OrgPersonnelYearAssess();
				SimpleBeanCopyUtil.simpleCopy(data, model);

				model.setPersonnelId(personnelId);

				orgPersonnelYearAssessService.save(model);
			}
		} catch (Exception e) {
			logger.info("人员[ID:" + personnelId + "]爬取年度考核信息失败:" + e.getMessage());
		}
	}

	public void crawPersonnelCultivate(String personnelId) {
		try {
			List<OrgPersonnelCultivateCrawler> datas = cultivateCrawler.crawl(personnelId);
			for (OrgPersonnelCultivateCrawler data : datas) {
				OrgPersonnelCultivate model = new OrgPersonnelCultivate();
				SimpleBeanCopyUtil.simpleCopy(data, model);

				model.setPersonnelId(personnelId);
				model.setAttachments(createAttachments(personnelId, data.getUrl()));

				orgPersonnelCultivateService.save(model);
			}
		} catch (Exception e) {
			logger.info("人员[ID:" + personnelId + "]爬取培训信息失败", e);
		}
	}

	public void crawlPersonnelData() throws IOException {

		Map<String, String> agencyNameMap = new AgencyPageCrawler().getIdAndName();
		Map<String, AdditionalData> personnelAdditionalDataMap = pageCrawler.getAdditionalData();

		logger.info("-----------------开始爬取基本信息数据-------------------");
		subPath = "Base";
		for (Entry<String, AdditionalData> entry : personnelAdditionalDataMap.entrySet()) {
			String personnelId = entry.getKey();
			AdditionalData additionalData = entry.getValue();
			try {
				crawPersonnelBase(personnelId, agencyNameMap, additionalData);
			} catch (Exception e) {
				logger.info("读取人员失败[ID:" + personnelId + "]", e);
			}
		}

		logger.info("-----------------开始爬取工作信息信息数据-------------------");
		subPath = "Job";
		for (Entry<String, AdditionalData> entry : personnelAdditionalDataMap.entrySet()) {
			String personnelId = entry.getKey();
			try {
				crawPersonnelJob(personnelId);
			} catch (Exception e) {
				logger.info("读取人员失败[ID:" + personnelId + "]", e);
			}
		}

		logger.info("-----------------开始爬取执业信息信息数据-------------------");
		subPath = "Practice";
		for (Entry<String, AdditionalData> entry : personnelAdditionalDataMap.entrySet()) {
			String personnelId = entry.getKey();
			AdditionalData additionalData = entry.getValue();
			try {
				crawPersonnelPractice(personnelId, additionalData);
			} catch (Exception e) {
				logger.info("读取人员失败[ID:" + personnelId + "]", e);
			}
		}

		logger.info("-----------------开始爬取教育经历数据-------------------");
		subPath = "Education";
		for (Entry<String, AdditionalData> entry : personnelAdditionalDataMap.entrySet()) {
			String personnelId = entry.getKey();
			try {
				List<?> result = personnelEducationService.getPersonnelEducations(personnelId);
				if (result == null || result.size() == 0) {
					crawPersonnelEducation(personnelId);
				}
			} catch (Exception e) {
				logger.info("读取人员失败[ID:" + personnelId + "]", e);
			}
		}

		logger.info("-----------------开始爬取工作经历数据-------------------");
		subPath = "WorkExperience";
		for (Entry<String, AdditionalData> entry : personnelAdditionalDataMap.entrySet()) {
			String personnelId = entry.getKey();
			try {
				crawPersonnelWorkExperience(personnelId);
			} catch (Exception e) {
				logger.info("读取人员失败[ID:" + personnelId + "]", e);
			}
		}

		logger.info("-----------------开始爬取职称信息数据-------------------");
		subPath = "Positional";
		for (Entry<String, AdditionalData> entry : personnelAdditionalDataMap.entrySet()) {
			String personnelId = entry.getKey();
			try {
				crawPersonnelPositionalInfo(personnelId);
			} catch (Exception e) {
				logger.info("读取人员失败[ID:" + personnelId + "]", e);
			}
		}

		logger.info("-----------------开始爬取执业注册信息数据-------------------");
		subPath = "PracticeRegistration";
		for (Entry<String, AdditionalData> entry : personnelAdditionalDataMap.entrySet()) {
			String personnelId = entry.getKey();
			try {
				crawPersonnelPracticeRegistration(personnelId);
			} catch (Exception e) {
				logger.info("读取人员失败[ID:" + personnelId + "]", e);
			}
		}

		logger.info("-----------------开始爬取奖励信息数据-------------------");
		subPath = "Reward";
		for (Entry<String, AdditionalData> entry : personnelAdditionalDataMap.entrySet()) {
			String personnelId = entry.getKey();
			try {
				crawPersonnelRewardInfo(personnelId);
			} catch (Exception e) {
				logger.info("读取人员失败[ID:" + personnelId + "]", e);
			}
		}

		logger.info("-----------------开始爬取科研信息数据-------------------");
		subPath = "ScienceEducation";
		for (Entry<String, AdditionalData> entry : personnelAdditionalDataMap.entrySet()) {
			String personnelId = entry.getKey();
			try {
				crawPersonnelScienceEducation(personnelId);
			} catch (Exception e) {
				logger.info("读取人员失败[ID:" + personnelId + "]", e);
			}
		}

		logger.info("-----------------开始爬取年度考核数据-------------------");
		subPath = "YearAssess";
		for (Entry<String, AdditionalData> entry : personnelAdditionalDataMap.entrySet()) {
			String personnelId = entry.getKey();
			try {
				crawPersonnelYearAssess(personnelId);
			} catch (Exception e) {
				logger.info("读取人员失败[ID:" + personnelId + "]", e);
			}
		}

		logger.info("-----------------开始爬取培训信息数据-------------------");
		subPath = "Cultivate";
		for (Entry<String, AdditionalData> entry : personnelAdditionalDataMap.entrySet()) {
			String personnelId = entry.getKey();
			try {
				crawPersonnelCultivate(personnelId);
			} catch (Exception e) {
				logger.info("读取人员失败[ID:" + personnelId + "]", e);
			}
		}

		logger.info("-----------------结束爬取数据-------------------");
	}

	private FileCrawler fileCrawler = new FileCrawler();
	private String subPath = "20181201";

	private String createAttachments(String personalId, List<String> urls) throws IOException {
		if (urls == null || urls.size() == 0) {
			return null;
		}
		String att = "";
		int i = 0;
		for (String url : urls) {
			if (i > 6) {
				System.out.println("人员[ID:" + personalId + "]附件数量超过六张!");
				break;
			}
			if (!url.startsWith("http")) {
				url = "http://" + url;
			}
			FileResult result = fileCrawler.getFile(url);
			byte[] data = result.getData();
			if (data != null && data.length > 0) {
				SysAttachment sysAttachment = attachmentService.createAttachment(data, result.getFilename(), "image", String.valueOf(subPath));
				att += sysAttachment.getId() + ",";
			}
			i++;
		}

		if (att.length() == 0) {
			att = null;
		}

		return att;
	}
}
