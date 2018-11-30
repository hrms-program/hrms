package com.paladin.hrms.mapper.complaint;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.hrms.controller.complaint.pojo.ComplaintPositionalTitleConfirmQuery;
import com.paladin.hrms.controller.complaint.pojo.ComplaintPositionalTitleQuery;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.complaint.ComplaintPositionalTitle;
import com.paladin.hrms.model.complaint.ComplaintPositionalTitleRecord;
import com.paladin.hrms.service.complaint.dto.ComplaintPositionalTitleVO;
import com.paladin.framework.mybatis.CustomMapper;

public interface ComplaintPositionalTitleMapper extends CustomMapper<ComplaintPositionalTitle>{
    
    public ComplaintPositionalTitleVO complaintPositionalTitleDetail(@Param("id")String id);
    
    public ComplaintPositionalTitleRecord complaintPositionalTitleRecordDetail(@Param("id")String id);
    
    public List<ComplaintPositionalTitleVO>complaintPositionalTitleFind(@Param("query")ComplaintPositionalTitleConfirmQuery query,@Param("permission")DataPermissionCondition permission);
    
    public List<ComplaintPositionalTitleVO>complaintPositionalTitleAll(@Param("query")ComplaintPositionalTitleQuery query,@Param("permission")DataPermissionCondition permission);
}