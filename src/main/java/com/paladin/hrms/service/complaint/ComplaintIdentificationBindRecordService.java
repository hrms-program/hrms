package com.paladin.hrms.service.complaint;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.hrms.controller.complaint.pojo.IdentificationBindQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.mapper.complaint.ComplaintIdentificationBindRecordMapper;
import com.paladin.hrms.model.complaint.ComplaintIdentificationBindRecord;
import com.paladin.hrms.service.complaint.dto.ComplaintIdentificationBindSimpleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintIdentificationBindRecordService extends ServiceSupport<ComplaintIdentificationBindRecord>{

    @Autowired
    private ComplaintIdentificationBindRecordMapper complaintIdentificationBindRecordMapper;

    public PageResult<ComplaintIdentificationBindSimpleVO> findComplaintRecordPage(IdentificationBindQuery query) {
        Page<ComplaintIdentificationBindSimpleVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionUtil.DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        complaintIdentificationBindRecordMapper.findComplaintRecord(query, permission);
        return new PageResult<>(page);
    }

}