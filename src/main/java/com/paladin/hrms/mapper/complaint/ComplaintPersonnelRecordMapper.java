package com.paladin.hrms.mapper.complaint;

import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.complaint.ComplaintPersonnelRecord;
import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelRecordQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelRecordSimpleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComplaintPersonnelRecordMapper extends CustomMapper<ComplaintPersonnelRecord>{

    public List<ComplaintPersonnelRecordSimpleVO> findComplaintPersonnel(@Param("query") ComplaintPersonnelRecordQueryDTO query, @Param("permission") DataPermissionCondition permission);

}