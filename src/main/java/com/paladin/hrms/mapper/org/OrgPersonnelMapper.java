package com.paladin.hrms.mapper.org;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.org.pojo.OrgPersonalAccountQuery;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.org.OrgAgency;
import com.paladin.hrms.model.org.OrgPersonnel;
import com.paladin.hrms.service.org.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrgPersonnelMapper extends CustomMapper<OrgPersonnel>{

	public List<OrgPersonnelVO> findPersonnel(@Param("query") OrgPersonnelQueryDTO query, @Param("permission") DataPermissionCondition permissionCondition);
	
	public OrgPersonnel getByIdentificationNo(String identificationNo);

    public  List<OrgPersonalAccountVO> selectAccountByQuery(@Param("query") OrgPersonalAccountQuery query, @Param("permission") DataPermissionCondition permission);

    public OrgPersonalAccountVO selectAccountById(String id);

    public int countPeopleByAgencyId(@Param("permission") DataPermissionCondition permissionCondition);

    public List<OrgPersonalAccountVO> findPeopleByAgencyId(@Param("permission") DataPermissionCondition permissionCondition);

	public List<OrgPersonnelExportSimpleVO> findSimpleExportPersonnel(@Param("query") OrgPersonnelExportQueryDTO query,
			@Param("permission") DataPermissionCondition permission);
	
	public List<Map<String, Object>> findExportPersonnelWithoutEducation(@Param("query") OrgPersonnelExportQueryDTO query,
			@Param("permission") DataPermissionCondition permission);
	
	public List<Map<String, Object>> findExportPersonnel(@Param("query") OrgPersonnelExportQueryDTO query,
			@Param("permission") DataPermissionCondition permission);

	public int updateAgencyAndDistrict(@Param("agency") OrgAgency agency,@Param("id") String personnelId);
}