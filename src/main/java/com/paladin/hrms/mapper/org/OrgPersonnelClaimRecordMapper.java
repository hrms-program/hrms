package com.paladin.hrms.mapper.org;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelClaimQuery;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.org.OrgPersonnelClaimDetail;
import com.paladin.hrms.model.org.OrgPersonnelClaimRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrgPersonnelClaimRecordMapper extends CustomMapper<OrgPersonnelClaimRecord> {

    public List<OrgPersonnelClaimDetail> findAllPeople(@Param("query") OrgPersonnelClaimQuery query, @Param("status") List<Integer> status,@Param("permission") DataPermissionCondition permission);
}