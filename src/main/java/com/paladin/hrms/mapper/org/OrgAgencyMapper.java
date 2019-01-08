package com.paladin.hrms.mapper.org;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.org.pojo.OrgAgencyQuery;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.org.OrgAgency;
import com.paladin.hrms.service.org.dto.OrgAgencyAccountVO;
import com.paladin.hrms.service.org.dto.OrgAgencyExportQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrgAgencyMapper extends CustomMapper<OrgAgency>{

    List<OrgAgencyAccountVO> findAccount(@Param("permission") DataPermissionCondition permission);
    
    List<OrgAgency> findAll(@Param("query")OrgAgencyQuery query, @Param("permission")DataPermissionCondition permission);

    List<OrgAgencyExportQueryDTO> findExportAgency(@Param("query") OrgAgencyQuery query, @Param("permission") DataPermissionCondition permission);
}