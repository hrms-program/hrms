package com.paladin.hrms.mapper.complaint;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.complaint.pojo.ComplaintIdentificationBindVO;
import com.paladin.hrms.controller.complaint.pojo.IdentificationBindQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.model.complaint.ComplaintIdentificationBindRecord;
import com.paladin.hrms.service.complaint.dto.ComplaintIdentificationBindSimpleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComplaintIdentificationBindRecordMapper extends CustomMapper<ComplaintIdentificationBindRecord>{

    List<ComplaintIdentificationBindSimpleVO> findComplaintPersonnel(@Param("query") IdentificationBindQuery query, @Param("permission") DataPermissionUtil.DataPermissionCondition permission);

    List<ComplaintIdentificationBindSimpleVO> findComplaintRecord(@Param("query") IdentificationBindQuery query, @Param("permission") DataPermissionUtil.DataPermissionCondition permission);

    int updateState(@Param("bind") ComplaintIdentificationBindVO bind);

    ComplaintIdentificationBindVO getComplaintPersonnel(@Param("id") String id);
}