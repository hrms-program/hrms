package com.paladin.hrms.mapper.complaint;

import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.complaint.ComplaintIdentificationExchange;
import com.paladin.hrms.service.complaint.dto.ComplaintIdentificationExchangeQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintIdentificationExchangeSimpleVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.mybatis.CustomMapper;

public interface ComplaintIdentificationExchangeMapper extends CustomMapper<ComplaintIdentificationExchange> {

	public List<ComplaintIdentificationExchangeSimpleVO> findComplaint(@Param("query") ComplaintIdentificationExchangeQueryDTO query,
			@Param("permission") DataPermissionCondition permission);

	public int exchangeIdentificationNo(@Param("target") String target, @Param("source") String source);

	public int countRelationRecord(@Param("fromId") String fromId, @Param("toId") String toId);
}