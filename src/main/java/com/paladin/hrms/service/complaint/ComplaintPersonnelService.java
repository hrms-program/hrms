package com.paladin.hrms.service.complaint;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.hrms.controller.complaint.pojo.ComplaintPersonnelVO;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.complaint.ComplaintPersonnelMapper;
import com.paladin.hrms.model.complaint.ComplaintModel;
import com.paladin.hrms.model.complaint.ComplaintPersonnel;
import com.paladin.hrms.model.complaint.ComplaintPersonnelRecord;
import com.paladin.hrms.model.org.OrgPersonnel;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelSimpleVO;
import com.paladin.hrms.service.org.OrgPersonnelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ComplaintPersonnelService extends ComplaintServiceSupport<ComplaintPersonnel> {

	@Autowired
	private ComplaintPersonnelMapper complaintPersonnelMapper;
	@Autowired
	private OrgPersonnelService personnelService;
	@Autowired
	private ComplaintPersonnelRecordService complaintPersonnelRecordService;

	public PageResult<ComplaintPersonnelSimpleVO> findComplaintPersonnelPage(ComplaintPersonnelQueryDTO query) {
		Page<ComplaintPersonnelSimpleVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		complaintPersonnelMapper.findComplaintPersonnel(query, permission);
		return new PageResult<>(page);
	}

	public ComplaintPersonnelVO getComplaintPersonnel(String id) {
		return complaintPersonnelMapper.getComplaintPersonnelById(id);
	}

	/**
	 * 申请或修改申请
	 */
	public int applyOrModifyComplaint(ComplaintPersonnel model) {
		String personnelId = model.getPersonnelId();
		OrgPersonnel personnel = personnelService.get(personnelId);

		int type = 0;
		int count = 0;
		if (isAttributionChange(model.getNowIdentificationNo(), personnel.getIdentificationNo())) {
			type = ComplaintPersonnel.TYPE_IDENTIFICATION_NO;
			count++;
		} else {
			model.setNowIdentificationNo(null);
		}

		if (isAttributionChange(model.getNowIdentificationType(), personnel.getIdentificationType())) {
			type = ComplaintPersonnel.TYPE_IDENTIFICATION_TYPE;
			count++;
		} else {
			model.setNowIdentificationType(null);
		}

		if (isAttributionChange(model.getNowSex(), personnel.getSex())) {
			type = ComplaintPersonnel.TYPE_SEX;
			count++;
		} else {
			model.setNowSex(null);
		}

		if (isAttributionChange(model.getNowName(), personnel.getName())) {
			type = ComplaintPersonnel.TYPE_NAME;
			count++;
		} else {
			model.setNowName(null);
		}

		if (count > 1) {
			type = ComplaintPersonnel.TYPE_ALL;
		}

		if (count == 0) {
			throw new BusinessException("没有修改的内容");
		}

		model.setType(type);
		return super.applyOrModifyComplaint(model);
	}

	private boolean isAttributionChange(Object newAttr, Object originAttr) {
		return newAttr != null && !newAttr.equals(originAttr);
	}

	/**
	 * 审核
	 * 
	 * @param id
	 * @param illustrate
	 * @param success
	 * @return
	 */
	@Transactional
	public boolean check(String id, String illustrate, boolean success) {
		ComplaintPersonnelVO complaintPersonnel = getComplaintPersonnel(id);
		if (complaintPersonnel.getStatus() == null) {
			throw new BusinessException("找不到审核对象");
		}
		int status = success ? ComplaintModel.STATUS_SUCCESS : ComplaintModel.STATUS_FAIL;
		if (complaintPersonnelMapper.checkComplaintPersonnel(id, status, illustrate) > 0) {
			ComplaintPersonnelRecord record = new ComplaintPersonnelRecord();
			SimpleBeanCopyUtil.simpleCopy(complaintPersonnel, record);
			record.setIllustrate(illustrate);
			record.setResult(status);
			complaintPersonnelRecordService.save(record);
			return true;
		}
		return false;
	}
}
