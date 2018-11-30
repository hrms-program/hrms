package com.paladin.hrms.mapper.assess;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.paladin.hrms.controller.assess.pojo.PhysicianAssessQuery;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.assess.AssessPhysicianRecord;
import com.paladin.framework.mybatis.CustomMapper;

public interface AssessPhysicianRecordMapper extends CustomMapper<AssessPhysicianRecord>{

    public  List<AssessPhysicianRecord> findAssessPhysicianRecord(@Param("query") PhysicianAssessQuery query, @Param("permission")DataPermissionCondition permission);
}