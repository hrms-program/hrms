package com.paladin.hrms.mapper.assess;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.assess.pojo.PhysicianAssessQuery;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.assess.AssessPhysicianRecord;
import com.paladin.hrms.service.assess.dto.AssessPhysicianRecordExport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssessPhysicianRecordMapper extends CustomMapper<AssessPhysicianRecord>{

    public  List<AssessPhysicianRecord> findAssessPhysicianRecord(@Param("query") PhysicianAssessQuery query, @Param("permission")DataPermissionCondition permission);

    public List<AssessPhysicianRecordExport> findExportPersonnelByQuery(@Param("query") PhysicianAssessQuery query, @Param("permission") DataPermissionCondition permission);
}