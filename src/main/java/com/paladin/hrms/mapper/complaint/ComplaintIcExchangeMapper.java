package com.paladin.hrms.mapper.complaint;

import com.paladin.hrms.controller.complaint.pojo.ComplaintIcExchangeQuery;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.complaint.ComplaintIcExchange;
import com.paladin.hrms.model.org.OrgPersonnel;
import com.paladin.hrms.service.complaint.dto.ComplaintIcExchangeDTO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.paladin.framework.mybatis.CustomMapper;

public interface ComplaintIcExchangeMapper extends CustomMapper<ComplaintIcExchange>{

      Page<ComplaintIcExchangeDTO> searchTableList(@Param("query") ComplaintIcExchangeQuery query, @Param("permission") DataPermissionCondition permission);

      List<OrgPersonnel> validate(@Param("idcard") String idcard, @Param("permission") DataPermissionCondition permission);

}