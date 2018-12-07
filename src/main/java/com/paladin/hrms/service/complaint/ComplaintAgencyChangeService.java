package com.paladin.hrms.service.complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.complaint.ComplaintAgencyChangeMapper;
import com.paladin.hrms.model.complaint.ComplaintAgencyChange;
import com.paladin.hrms.model.complaint.ComplaintAgencyChangeRecord;
import com.paladin.hrms.model.complaint.ComplaintModel;
import com.paladin.hrms.model.org.OrgPersonnel;
import com.paladin.hrms.service.complaint.dto.ComplaintAgencyAppealsQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintAgencyChangeDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintAgencyChangeSimpleVO;
import com.paladin.hrms.service.complaint.dto.PersonnelForAgencyChangeVO;
import com.paladin.hrms.service.complaint.dto.PersonnelForAgencyQueryDTO;
import com.paladin.hrms.service.org.OrgPersonnelService;

@Service
public class ComplaintAgencyChangeService extends ComplaintServiceSupport<ComplaintAgencyChange> {

	@Autowired
	private ComplaintAgencyChangeMapper complaintAgencyChangeMapper;

	@Autowired
	private ComplaintAgencyChangeRecordService complaintAgencyChangeRecordService;

	@Autowired
	private OrgPersonnelService personnelService;

	public PageResult<PersonnelForAgencyChangeVO> findPersonnelForChange(PersonnelForAgencyQueryDTO query) {
		Page<PersonnelForAgencyChangeVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		complaintAgencyChangeMapper.findPersonnelForChange(query, permission);
		return new PageResult<>(page);
	}

	@Transactional
	public int applyAgencyChange(String[] personnelIds, String targetAgency) {
		int effect = 0;

		if (targetAgency == null || targetAgency.length() == 0) {
			throw new BusinessException("转移机构不能为空");
		}

		if (personnelIds != null && personnelIds.length > 0) {
			for (String personnelId : personnelIds) {
				OrgPersonnel personnel = personnelService.get(personnelId);
				if (personnel == null) {
					throw new BusinessException("找不到对应申请人");
				}

				if (targetAgency.equals(personnel.getAgencyId())) {
					throw new BusinessException("相同机构不能互相转移");
				}

				ComplaintAgencyChange change = new ComplaintAgencyChange();
				change.setPersonnelId(personnelId);
				change.setTargetAgency(targetAgency);

				if (applyOrModifyComplaint(change) == 0) {
					throw new BusinessException("申请机构变更失败");
				}
				effect++;
			}
		}

		return effect;
	}

	public PageResult<ComplaintAgencyChangeSimpleVO> findAgencyChangePage(ComplaintAgencyAppealsQueryDTO query) {
		Page<ComplaintAgencyChangeSimpleVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		complaintAgencyChangeMapper.findAgencyChange(query, permission);
		return new PageResult<>(page);
	}

	/**
	 * 
	 * @param personnelId
	 * @param illustrate
	 * @param result
	 * @return
	 */
	@Transactional
	public boolean confirmCheck(String personnelId, String illustrate, boolean pass) {
		int effect = pass ? complaintSuccess(personnelId, illustrate) : complaintFail(personnelId, illustrate);

		if (effect > 0) {
			ComplaintAgencyChangeDTO change = complaintAgencyChangeMapper.getAgencyChangeById(personnelId);
			String agencyId = change.getAgencyId();
			String targetAgencyId = change.getTargetAgencyId();

			if (pass) {
				if (!personnelService.changeAgencyForOwnPersonnel(personnelId, targetAgencyId)) {
					throw new BusinessException("机构变更失败");
				}
			}

			ComplaintAgencyChangeRecord record = new ComplaintAgencyChangeRecord();
			record.setPersonnelId(personnelId);
			record.setAgencyId(agencyId);
			record.setTargetAgencyId(targetAgencyId);
			record.setResult(pass ? ComplaintModel.STATUS_SUCCESS : ComplaintModel.STATUS_FAIL);
			record.setIllustrate(illustrate);
			if (complaintAgencyChangeRecordService.save(record) <= 0) {
				throw new BusinessException("审核操作失败");
			}

			return true;
		}

		return false;
	}

}