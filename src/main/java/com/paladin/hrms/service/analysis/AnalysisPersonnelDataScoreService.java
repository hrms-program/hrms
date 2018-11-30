package com.paladin.hrms.service.analysis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.analysis.AnalysisPersonnelDataScoreMapper;
import com.paladin.hrms.model.analysis.AnalysisAgencyDataScore;
import com.paladin.hrms.model.analysis.AnalysisPersonnelDataScore;
import com.paladin.hrms.model.org.OrgAgency;
import com.paladin.hrms.model.org.OrgPersonnel;
import com.paladin.hrms.model.org.OrgPersonnelCultivate;
import com.paladin.hrms.model.org.OrgPersonnelEducation;
import com.paladin.hrms.model.org.OrgPersonnelJob;
import com.paladin.hrms.model.org.OrgPersonnelPositionalInfo;
import com.paladin.hrms.model.org.OrgPersonnelPractice;
import com.paladin.hrms.model.org.OrgPersonnelRewardInfo;
import com.paladin.hrms.model.org.OrgPersonnelScienceEducation;
import com.paladin.hrms.model.org.OrgPersonnelWorkExperience;
import com.paladin.hrms.model.org.OrgPersonnelYearAssess;
import com.paladin.hrms.service.analysis.dto.AgencyDataSumScoreDTO;
import com.paladin.hrms.service.analysis.dto.AnalysisPersonnelDataScoreQueryDTO;
import com.paladin.hrms.service.analysis.dto.AnalysisPersonnelDataScoreVO;
import com.paladin.hrms.service.org.OrgAgencyService;
import com.paladin.hrms.service.org.OrgPersonnelJobService;
import com.paladin.hrms.service.org.OrgPersonnelPracticeService;
import com.paladin.hrms.service.org.OrgPersonnelService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.utils.reflect.Entity;
import com.paladin.framework.utils.reflect.EntityField;
import com.paladin.framework.utils.reflect.NameUtil;

@Service
public class AnalysisPersonnelDataScoreService extends ServiceSupport<AnalysisPersonnelDataScore> {

	@Autowired
	private AnalysisPersonnelDataScoreMapper analysisPersonnelDataScoreMapper;

	@Autowired
	private OrgAgencyService agencyService;

	@Autowired
	private AnalysisAgencyDataScoreService analysisAgencyDataScoreService;

	@Autowired
	private OrgPersonnelService personnelService;

	@Autowired
	private OrgPersonnelJobService personnelJobService;

	@Autowired
	private OrgPersonnelPracticeService personnelPracticeService;

	public PageResult<AnalysisPersonnelDataScoreVO> findPersonnelScore(AnalysisPersonnelDataScoreQueryDTO query) {
		Page<AnalysisPersonnelDataScoreVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		analysisPersonnelDataScoreMapper.findPersonnelScore(query, permission);
		return new PageResult<>(page);
	}

	public void figureOutScore() {
		figureOutPersonnelScore();
		figureOutAgencyScore();
	}

	public void figureOutAgencyScore() {
		int limit = 100;
		int offset = 0;

		while (true) {
			PageResult<OrgAgency> pageResult = agencyService.findPage(offset, limit);
			List<OrgAgency> agencies = pageResult.getData();

			if (agencies != null && agencies.size() > 0) {
				Date now = new Date();
				for (OrgAgency agency : agencies) {
					String agencyId = agency.getId();
					AgencyDataSumScoreDTO agencyDataSumScore = analysisPersonnelDataScoreMapper.sumOfScoreForAgency(agencyId);

					int count = agencyDataSumScore.getPersonnelNum();

					int baseScore = getAverageScore(agencyDataSumScore.getBaseScore(), count);
					int jobScore = getAverageScore(agencyDataSumScore.getJobScore(), count);
					int practiceScore = getAverageScore(agencyDataSumScore.getPracticeScore(), count);
					int educationScore = getAverageScore(agencyDataSumScore.getEducationScore(), count);
					int workExperienceScore = getAverageScore(agencyDataSumScore.getWorkExperienceScore(), count);
					int positionalInfoScore = getAverageScore(agencyDataSumScore.getPositionalInfoScore(), count);
					int scienceEducationScore = getAverageScore(agencyDataSumScore.getScienceEducationScore(), count);
					int rewardInfoScore = getAverageScore(agencyDataSumScore.getRewardInfoScore(), count);
					int cultivateScore = getAverageScore(agencyDataSumScore.getCultivateScore(), count);

					double sumScore = getAverageSumScore(agencyDataSumScore.getSumScore(), count);

					AnalysisAgencyDataScore model = new AnalysisAgencyDataScore();
					model.setAgencyId(agencyId);
					model.setBaseScore(baseScore);
					model.setJobScore(jobScore);
					model.setPracticeScore(practiceScore);
					model.setEducationScore(educationScore);
					model.setWorkExperienceScore(workExperienceScore);
					model.setPositionalInfoScore(positionalInfoScore);
					model.setScienceEducationScore(scienceEducationScore);
					model.setRewardInfoScore(rewardInfoScore);
					model.setCultivateScore(cultivateScore);
					model.setSumScore(sumScore);

					model.setUpdateTime(now);

					if (analysisAgencyDataScoreService.get(agencyId) != null) {
						analysisAgencyDataScoreService.update(model);
					} else {
						analysisAgencyDataScoreService.save(model);
					}
				}
			}

			offset += limit;
			if (offset >= pageResult.getTotal()) {
				break;
			}
		}
	}

	private double getAverageSumScore(int score, int count) {
		if (count == 0) {
			return 0;
		}

		double d = score * 1.0 / count;
		return new BigDecimal(d).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	private int getAverageScore(int score, int count) {
		if (count == 0) {
			return 0;
		}
		return (int) Math.round(1.0 * score / count);
	}

	public void figureOutPersonnelScore() {
		int limit = 100;
		int offset = 0;

		while (true) {
			PageResult<OrgPersonnel> pageResult = personnelService.findPage(offset, limit);
			List<OrgPersonnel> personnels = pageResult.getData();

			if (personnels != null && personnels.size() > 0) {
				Date now = new Date();

				for (OrgPersonnel personnel : personnels) {
					String personnelId = personnel.getId();

					OrgPersonnelJob job = personnelJobService.get(personnelId);
					OrgPersonnelPractice practice = personnelPracticeService.get(personnelId);

					int baseScore = baseScoreAnalyzer.getScore(personnel);

					// 人员类型放在基本信息
					if (practice != null && practice.getPersonnelType() != null) {
						baseScore += 2;
					}

					int jobScore = jobScoreAnalyzer.getScore(job);
					// 所在机构在人员基础表中
					if (personnel.getAgencyId() != null && personnel.getAgencyId().length() != 0) {
						jobScore += 2;
					}

					int practiceScore = 0;
					if (practice != null) {
						Integer type = practice.getPersonnelType();
						if (type != null) {
							if (type == OrgPersonnelPractice.PERSONNEL_TYPE_DOCTOR) {
								practiceScore = doctorScoreAnalyzer.getScore(practice);
							} else if (type == OrgPersonnelPractice.PERSONNEL_TYPE_NURSE) {
								practiceScore = nurseScoreAnalyzer.getScore(practice);
							} else if (type == OrgPersonnelPractice.PERSONNEL_TYPE_COUNTRY_DOCTOR) {
								practiceScore = countryDoctorScoreAnalyzer.getScore(practice);
							} else {
								practiceScore = otherPracticeDoctorScoreAnalyzer.getScore(practice);
							}
						}
					}

					int educationScore = getContextScore(OrgPersonnelEducation.class, personnelId);
					int workExperienceScore = getContextScore(OrgPersonnelWorkExperience.class, personnelId);
					int positionalInfoScore = getContextScore(OrgPersonnelPositionalInfo.class, personnelId);
					int scienceEducationScore = getContextScore(OrgPersonnelScienceEducation.class, personnelId);
					int rewardInfoScore = getContextScore(OrgPersonnelRewardInfo.class, personnelId);
					int cultivateScore = getContextScore(OrgPersonnelCultivate.class, personnelId);
					int yearAssessScore = analysisPersonnelDataScoreMapper.countOfContext(getContextTableName(OrgPersonnelYearAssess.class), personnelId) > 0
							? 10
							: 0;

					int sumScore = baseScore + jobScore + practiceScore + educationScore + workExperienceScore + positionalInfoScore + scienceEducationScore
							+ rewardInfoScore + cultivateScore + yearAssessScore;

					AnalysisPersonnelDataScore model = new AnalysisPersonnelDataScore();
					model.setPersonnelId(personnelId);
					model.setBaseScore(baseScore);
					model.setJobScore(jobScore);
					model.setPracticeScore(practiceScore);
					model.setEducationScore(educationScore);
					model.setWorkExperienceScore(workExperienceScore);
					model.setPositionalInfoScore(positionalInfoScore);
					model.setScienceEducationScore(scienceEducationScore);
					model.setRewardInfoScore(rewardInfoScore);
					model.setCultivateScore(cultivateScore);
					model.setYearAssessScore(yearAssessScore);
					model.setSumScore(sumScore);
					model.setUpdateTime(now);

					if (get(personnelId) != null) {
						update(model);
					} else {
						save(model);
					}
				}
			}

			offset += limit;
			if (offset >= pageResult.getTotal()) {
				break;
			}
		}
	}

	private String getContextTableName(Class<?> modelType) {
		return NameUtil.hump2underline(modelType.getSimpleName()).substring(1);
	}

	private int getContextScore(Class<?> modelType, String personnelId) {
		String tableName = getContextTableName(modelType);
		int c1 = analysisPersonnelDataScoreMapper.countOfContext(tableName, personnelId);
		if (c1 > 0) {
			int c2 = analysisPersonnelDataScoreMapper.countOfValidContext(tableName, personnelId);
			return c1 == c2 ? 8 : 4;
		}
		return 0;
	}

	private final static ScoreAnalyzer<OrgPersonnel> baseScoreAnalyzer;
	private final static ScoreAnalyzer<OrgPersonnelJob> jobScoreAnalyzer;

	private final static ScoreAnalyzer<OrgPersonnelPractice> doctorScoreAnalyzer;
	private final static ScoreAnalyzer<OrgPersonnelPractice> nurseScoreAnalyzer;
	private final static ScoreAnalyzer<OrgPersonnelPractice> countryDoctorScoreAnalyzer;
	private final static ScoreAnalyzer<OrgPersonnelPractice> otherPracticeDoctorScoreAnalyzer;

	static {
		baseScoreAnalyzer = new ScoreAnalyzer<OrgPersonnel>(OrgPersonnel.class);
		baseScoreAnalyzer.addScoreRules(1, "nationality", "nation", "politicalAffiliation", "interest");
		baseScoreAnalyzer.addScoreRules(2, "sex", "birthday", "identificationType", "interest", "startWorkTime", "cellphone");

		jobScoreAnalyzer = new ScoreAnalyzer<OrgPersonnelJob>(OrgPersonnelJob.class);
		jobScoreAnalyzer.addScoreRules(1, "duty", "employPost", "inorout", "inoroutDate");
		jobScoreAnalyzer.addScoreRules(2, "dept", "major", "technicalQualification", "techPost", "formation");

		doctorScoreAnalyzer = new ScoreAnalyzer<OrgPersonnelPractice>(OrgPersonnelPractice.class);
		doctorScoreAnalyzer.addScoreRules(1, "docTrainCert", "isInVillageClinic");
		doctorScoreAnalyzer.addScoreRules(2, "registrationDate", "practiceCategory", "practiceScope", "isDispatched");

		nurseScoreAnalyzer = new ScoreAnalyzer<OrgPersonnelPractice>(OrgPersonnelPractice.class);
		nurseScoreAnalyzer.addScoreRules(2, "nurseInstitution", "isExam", "startWorkDate", "nurseCategory", "isInVillageClinic");

		countryDoctorScoreAnalyzer = new ScoreAnalyzer<OrgPersonnelPractice>(OrgPersonnelPractice.class);
		countryDoctorScoreAnalyzer.addScoreRules(2, "vdocCertCode", "isMedicalInsurance", "isInjuryInsurance", "isEndowmentInsurance", "isOnjobTrain");

		otherPracticeDoctorScoreAnalyzer = new ScoreAnalyzer<OrgPersonnelPractice>(OrgPersonnelPractice.class);
		otherPracticeDoctorScoreAnalyzer.addScoreRules(2, "certName", "issueUnit", "issueDate", "isInVillageClinic");
		otherPracticeDoctorScoreAnalyzer.addScoreRules(1, "startDate", "endDate");

	}

	public static class ScoreAnalyzer<T> {

		private Entity entity;
		private Map<String, Integer> field2scoreMap;

		public ScoreAnalyzer(Class<T> modelType) {
			entity = Entity.getEntity(modelType);
			field2scoreMap = new HashMap<>();
		}

		public void addScoreRule(int score, String fieldName) {
			field2scoreMap.put(fieldName, score);
		}

		public void addScoreRules(int score, String... fieldNames) {
			for (String fieldName : fieldNames) {
				field2scoreMap.put(fieldName, score);
			}
		}

		public int getScore(T model) {
			int score = 0;
			if (model != null) {
				for (Entry<String, Integer> entry : field2scoreMap.entrySet()) {
					EntityField field = entity.getEntityField(entry.getKey());
					Object value = field.getValue(model);
					if (value != null) {
						if (value instanceof String) {
							String str = (String) value;
							str = str.trim();
							if (str.length() > 0) {
								score += entry.getValue();
							}
						} else {
							score += entry.getValue();
						}
					}
				}
			}
			return score;
		}
	}
}
