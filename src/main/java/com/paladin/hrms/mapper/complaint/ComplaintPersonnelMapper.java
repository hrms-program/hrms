package com.paladin.hrms.mapper.complaint;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.complaint.pojo.ComplaintPersonnelVO;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.complaint.ComplaintPersonnel;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelSimpleVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ComplaintPersonnelMapper extends CustomMapper<ComplaintPersonnel> {

	public List<ComplaintPersonnelSimpleVO> findComplaintPersonnel(@Param("query") ComplaintPersonnelQueryDTO query,
			@Param("permission") DataPermissionCondition permission);

	public ComplaintPersonnelVO getComplaintPersonnelById(@Param("id") String id);

	public int checkComplaintPersonnel(@Param("id") String id, @Param("status") Integer status, @Param("illustrate") String illustrate);
}