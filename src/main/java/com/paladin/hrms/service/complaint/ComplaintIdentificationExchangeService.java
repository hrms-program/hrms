package com.paladin.hrms.service.complaint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.complaint.ComplaintIdentificationExchangeMapper;
import com.paladin.hrms.model.complaint.ComplaintIdentificationExchange;
import com.paladin.hrms.model.complaint.ComplaintIdentificationExchangeRecord;
import com.paladin.hrms.model.complaint.ComplaintModel;
import com.paladin.hrms.model.org.OrgPersonnel;
import com.paladin.hrms.service.complaint.dto.ComplaintIdentificationExchangeDetailVO;
import com.paladin.hrms.service.complaint.dto.ComplaintIdentificationExchangeDetailVO.ComplaintIdentificationExchangePersonnelVO;
import com.paladin.hrms.service.complaint.dto.ComplaintIdentificationExchangeQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintIdentificationExchangeSimpleVO;
import com.paladin.hrms.service.org.OrgPersonnelService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;

@Service
public class ComplaintIdentificationExchangeService extends ServiceSupport<ComplaintIdentificationExchange> {

	@Autowired
	private ComplaintIdentificationExchangeMapper complaintIdentificationExchangeMapper;

	@Autowired
	private OrgPersonnelService personnelService;

	@Autowired
	private ComplaintIdentificationExchangeRecordService complaintIdentificationExchangeRecordService;

	public PageResult<ComplaintIdentificationExchangeSimpleVO> findComplaintPage(ComplaintIdentificationExchangeQueryDTO query) {
		Page<ComplaintIdentificationExchangeSimpleVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, null);
		complaintIdentificationExchangeMapper.findComplaint(query, permission);
		return new PageResult<>(page);
	}

	public ComplaintIdentificationExchangeDetailVO getComplaintDetail(String id) {
		ComplaintIdentificationExchange exchange = get(id);
		if (exchange != null) {
			ComplaintIdentificationExchangeDetailVO detail = new ComplaintIdentificationExchangeDetailVO();
			ComplaintIdentificationExchangePersonnelVO fromPersonnel = new ComplaintIdentificationExchangePersonnelVO();
			SimpleBeanCopyUtil.simpleCopy(personnelService.get(exchange.getFromPersonnelId()), fromPersonnel);
			ComplaintIdentificationExchangePersonnelVO toPersonnel = new ComplaintIdentificationExchangePersonnelVO();
			SimpleBeanCopyUtil.simpleCopy(personnelService.get(exchange.getToPersonnelId()), toPersonnel);

			detail.setFromPersonnel(fromPersonnel);
			detail.setToPersonnel(toPersonnel);
			return detail;
		} else {
			throw new BusinessException("找不到对应的身份置换记录");
		}
	}

	public List<ComplaintIdentificationExchangePersonnelVO> getComplaintPersonnel(String identificationNo) {
		List<OrgPersonnel> personnels = personnelService.getAllStatusPersonnelByIdNo(identificationNo);
		if (personnels != null) {
			return SimpleBeanCopyUtil.simpleCopyList(personnels, ComplaintIdentificationExchangePersonnelVO.class);
		}
		return null;
	}

	/**
	 * 新增保存，需要判断是否有已经在置换中的记录
	 */
	public int save(ComplaintIdentificationExchange exchange) {

		String fromId = exchange.getFromPersonnelId();
		String toId = exchange.getToPersonnelId();

		if (fromId == null || fromId.length() == 0 || toId == null || toId.length() == 0) {
			throw new BusinessException("置换人员不能为空");
		}

		if (complaintIdentificationExchangeMapper.countRelationRecord(fromId, toId) > 0) {
			throw new BusinessException("该人员身份证正在置换中");
		}

		return super.save(exchange);
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
		int result = success ? ComplaintModel.STATUS_SUCCESS : ComplaintModel.STATUS_FAIL;

		ComplaintIdentificationExchange exchange = get(id);
		String fromId = exchange.getFromPersonnelId();
		String toId = exchange.getToPersonnelId();

		if (removeByPrimaryKey(id) > 0) {
			if (success) {
				if (complaintIdentificationExchangeMapper.exchangeIdentificationNo(fromId, toId) <= 0
						|| complaintIdentificationExchangeMapper.exchangeIdentificationNo(toId, fromId) <= 0) {
					throw new BusinessException("置换身份证失败");
				}
			}

			OrgPersonnel fromPer = personnelService.get(fromId);
			OrgPersonnel toPer = personnelService.get(toId);

			ComplaintIdentificationExchangeRecord record = new ComplaintIdentificationExchangeRecord();

			record.setFromPersonnelId(fromId);
			record.setFromIdentificationType(fromPer.getIdentificationType());
			record.setFromIdentificationNo(fromPer.getIdentificationNo());
			record.setToPersonnelId(toId);
			record.setToIdentificationType(toPer.getIdentificationType());
			record.setToIdentificationNo(toPer.getIdentificationNo());
			record.setResult(result);
			record.setIllustrate(illustrate);

			return complaintIdentificationExchangeRecordService.save(record) > 0;
		}

		return false;
	}

}