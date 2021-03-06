package com.paladin.hrms.service.complaint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.GeneralCriteriaBuilder.Condition;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.complaint.ComplaintPersonnelArchivesCheckMapper;
import com.paladin.hrms.model.complaint.ComplaintModel;
import com.paladin.hrms.model.complaint.ComplaintPersonnelArchivesCheck;
import com.paladin.hrms.model.complaint.ComplaintPersonnelArchivesCheckRecord;
import com.paladin.hrms.model.org.PersonnelModel;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckRecordVO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckResultDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckVO;
import com.paladin.hrms.service.org.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComplaintPersonnelArchivesCheckService extends ServiceSupport<ComplaintPersonnelArchivesCheck> {

	@Autowired
	private ComplaintPersonnelArchivesCheckMapper archivesCheckMapper;

	@Autowired
	private ComplaintPersonnelArchivesCheckRecordService archivesCheckRecordService;
	@Autowired
	private OrgPersonnelCultivateService personnelCultivateService;
	@Autowired
	private OrgPersonnelEducationService personnelEducationService;
	@Autowired
	private OrgPersonnelPositionalInfoService personnelPositionalInfoService;
	@Autowired
	private OrgPersonnelRewardInfoService personnelRewardInfoService;
	@Autowired
	private OrgPersonnelScienceEducationService personnelScienceEducationService;
	@Autowired
	private OrgPersonnelWorkExperienceService personnelWorkExperienceService;

	/**
	 * 按人唯一查询结果
	 * 
	 * @param query
	 * @return
	 */
	public PageResult<ComplaintPersonnelArchivesCheckVO> searchTableList(ComplaintPersonnelArchivesCheckQueryDTO query) {
		Page<ComplaintPersonnelArchivesCheckVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		archivesCheckMapper.searchTableList(query, permission);
		return new PageResult<>(page);
	}

	/**
	 * 查询某个人的所有记录
	 * 
	 * @param query
	 * @return
	 */
	public PageResult<ComplaintPersonnelArchivesCheckVO> searchAppliacationList(ComplaintPersonnelArchivesCheckQueryDTO query) {
		Page<ComplaintPersonnelArchivesCheckVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		archivesCheckMapper.searchAppliacationList(query, permission);
		return new PageResult<>(page);
	}

	public PageResult<ComplaintPersonnelArchivesCheckVO> searchDistrictAppliacationList(ComplaintPersonnelArchivesCheckQueryDTO query) {
		Page<ComplaintPersonnelArchivesCheckVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		archivesCheckMapper.searchDistrictAppliacationList(query, permission);
		return new PageResult<>(page);
	}

	/**
	 * 查找
	 * 
	 * @param recordId
	 * @param recordType
	 * @return
	 */
	public ComplaintPersonnelArchivesCheck getArchivesCheckByRecordId(String recordId, Integer recordType) {
		List<ComplaintPersonnelArchivesCheck> list = searchAll(
				new Condition[] { new Condition(ComplaintPersonnelArchivesCheck.COLUMN_FIELD_RECORD_ID, QueryType.EQUAL, recordId),
						new Condition(ComplaintPersonnelArchivesCheck.COLUMN_FIELD_RECORD_TYPE, QueryType.EQUAL, recordType) });

		return (list == null || list.size() == 0) ? null : list.get(0);
	}

	/**
	 * 审核成功
	 * 
	 * @param archivesCheckResult
	 * @return
	 */
	public boolean checkSuccess(ComplaintPersonnelArchivesCheckResultDTO archivesCheckResult,boolean isOrgCheck) {
		return check(archivesCheckResult, true,isOrgCheck);
	}

	/**
	 * 审核失败
	 * 
	 * @param archivesCheckResult
	 * @return
	 */
	public boolean checkFail(ComplaintPersonnelArchivesCheckResultDTO archivesCheckResult,boolean isOrgCheck) {
		return check(archivesCheckResult, false,isOrgCheck);
	}

	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 审核
	 * 
	 * @param archivesCheckResult
	 * @param success
	 * @return
	 */
	@Transactional
	public boolean check(ComplaintPersonnelArchivesCheckResultDTO archivesCheckResult, boolean success,boolean isOrgCheck) {
		String recordId = archivesCheckResult.getRecordId();
		Integer recordType = archivesCheckResult.getRecordType();

		if (recordType == null || recordId == null || recordId.length() == 0) {
			throw new BusinessException("参数不正确");
		}
		Integer checkStatus;
		if (isOrgCheck) {
		  checkStatus = success ? ComplaintModel.STATUS_ORG_SUCCESS : ComplaintModel.STATUS_ORG_FAIL;
		} else {
		  checkStatus = success ? ComplaintModel.STATUS_DISTRICT_SUCCESS : ComplaintModel.STATUS_DISTRICT_FAIL;
		}
		ComplaintPersonnelArchivesCheck check = getArchivesCheckByRecordId(recordId, recordType);
		return check(check, archivesCheckResult.getIllustrate(), success,checkStatus);
	}

	/**
	 * 审核
	 * 
	 * @param check
	 * @param illustrate
	 * @param success
	 * @return
	 */
	@Transactional
	public boolean check(ComplaintPersonnelArchivesCheck check, String illustrate, boolean success,Integer checkStatus ) {
		if (check == null) {
			throw new BusinessException("找不到对应需要审核记录");
		}

		Integer recordCheckedStatus = check.getCheckStatus();
		if (recordCheckedStatus == ComplaintModel.STATUS_ORG_FAIL) {
		  throw new BusinessException("记录已经被机构审核为不通过,详细咨询有关机构管理员");
		}
		if (recordCheckedStatus == ComplaintModel.STATUS_DISTRICT_FAIL || recordCheckedStatus == ComplaintModel.STATUS_DISTRICT_SUCCESS) {
		  throw new BusinessException("记录已经被行政审核,无法重新审核");
		}

		String recordId = check.getRecordId();
		Integer recordType = check.getRecordType();

		int effect = archivesCheckMapper.updateStatusForCheck(recordId, recordType, checkStatus);

		if (effect <= 0) {
			throw new BusinessException("记录审核失败");
		}

		PersonnelModel content = null;
		if (recordType == ComplaintPersonnelArchivesCheck.RECORD_TYPE_CULTIVATE) {
			content = personnelCultivateService.get(recordId);
		} else if (recordType == ComplaintPersonnelArchivesCheck.RECORD_TYPE_EDUCATION) {
			content = personnelEducationService.get(recordId);
		} else if (recordType == ComplaintPersonnelArchivesCheck.RECORD_TYPE_POSITIONAL_INFO) {
			content = personnelPositionalInfoService.get(recordId);
		} else if (recordType == ComplaintPersonnelArchivesCheck.RECORD_TYPE_REWARD_INFO) {
			content = personnelRewardInfoService.get(recordId);
		} else if (recordType == ComplaintPersonnelArchivesCheck.RECORD_TYPE_SCIENCE_EDUCATION) {
			content = personnelScienceEducationService.get(recordId);
		} else if (recordType == ComplaintPersonnelArchivesCheck.RECORD_TYPE_WORK_EXPERIENCE) {
			content = personnelWorkExperienceService.get(recordId);
		} else {
			throw new BusinessException("参数异常");
		}

		String contentStr;
		try {
			contentStr = objectMapper.writeValueAsString(content);
		} catch (JsonProcessingException e) {
			throw new BusinessException("数据异常", e);
		}

		ComplaintPersonnelArchivesCheckRecord record = new ComplaintPersonnelArchivesCheckRecord();
		record.setCheckContent(contentStr);
		record.setRecordType(recordType);
		record.setRecordId(recordId);
		record.setPersonnelId(check.getPersonnelId());
		record.setIllustrate(illustrate);
		record.setResult(checkStatus);

		effect = 0;
		if (checkStatus == ComplaintModel.STATUS_ORG_SUCCESS){
			effect = archivesCheckRecordService.save(record);
		}else {
			ComplaintPersonnelArchivesCheckRecordVO checkedRecord = archivesCheckRecordService.getArchivesCheckRecordByRecordId(recordId, recordType);
			if (checkedRecord != null) {
				record.setId(checkedRecord.getId());
				record.setCreateTime(checkedRecord.getCreateTime());
				record.setCreateUserId(checkedRecord.getCreateUserId());
				if (archivesCheckRecordService.update(record) > 0) {
					effect = updatePersonnelInfo(success, recordId, recordType);
				}
			}else {
				if (archivesCheckRecordService.save(record) > 0) {
					effect = updatePersonnelInfo(success, recordId, recordType);
				}
			}
		}
		if (effect <= 0) {
			throw new BusinessException("审核失败");
		}

		return true;
	}
	/**
	 * 功能描述: <br>
	 * 〈更新人员信息变更状态〉
	 * @param success
	 * @param recordId
	 * @param recordType
	 * @return  int
	 * @author  Huangguochen
	 * @date  2019/2/27
	 */

	public int updatePersonnelInfo(boolean success, String recordId, Integer recordType) {
		int effect = 0;
		if (recordType == ComplaintPersonnelArchivesCheck.RECORD_TYPE_CULTIVATE) {
			effect = personnelCultivateService.checkPassHandler(recordId, success);
		} else if (recordType == ComplaintPersonnelArchivesCheck.RECORD_TYPE_EDUCATION) {
			effect = personnelEducationService.checkPassHandler(recordId, success);
		} else if (recordType == ComplaintPersonnelArchivesCheck.RECORD_TYPE_POSITIONAL_INFO) {
			effect = personnelPositionalInfoService.checkPassHandler(recordId, success);
		} else if (recordType == ComplaintPersonnelArchivesCheck.RECORD_TYPE_REWARD_INFO) {
			effect = personnelRewardInfoService.checkPassHandler(recordId, success);
		} else if (recordType == ComplaintPersonnelArchivesCheck.RECORD_TYPE_SCIENCE_EDUCATION) {
			effect = personnelScienceEducationService.checkPassHandler(recordId, success);
		} else if (recordType == ComplaintPersonnelArchivesCheck.RECORD_TYPE_WORK_EXPERIENCE) {
			effect = personnelWorkExperienceService.checkPassHandler(recordId, success);
		}
		return effect;
	}

	/**
	 * 新增档案信息审核记录
	 * 
	 * @param recordId
	 * @param personnelId
	 * @param recordType
	 * @return
	 */
	public boolean addArchivesCheck(String recordId, String personnelId, int recordType) {
		if (archivesCheckMapper.updateStatus(recordId, recordType, ComplaintModel.STATUS_WAITING) > 0) {
			return true;
		} else {
			ComplaintPersonnelArchivesCheck check = new ComplaintPersonnelArchivesCheck();
			check.setCheckStatus(ComplaintModel.STATUS_WAITING);
			check.setRecordId(recordId);
			check.setRecordType(recordType);
			check.setPersonnelId(personnelId);
			return save(check) > 0;
		}
	}

}