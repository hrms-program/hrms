package com.paladin.hrms.mapper.org;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.paladin.hrms.controller.org.pojo.OrgAgencyManagerQuery;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.org.OrgAgencyManager;
import com.paladin.hrms.model.org.OrgAgencyManagerVo;
import com.paladin.framework.mybatis.CustomMapper;

public interface OrgAgencyManagerMapper extends CustomMapper<OrgAgencyManager>{
    
    List<OrgAgencyManagerVo> findAll(@Param("query")OrgAgencyManagerQuery query, @Param("permission")DataPermissionCondition permission);
}