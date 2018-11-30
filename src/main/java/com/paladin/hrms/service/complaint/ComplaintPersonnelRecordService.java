package com.paladin.hrms.service.complaint;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.mapper.complaint.ComplaintPersonnelRecordMapper;
import com.paladin.hrms.model.complaint.ComplaintPersonnelRecord;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelRecordQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelRecordSimpleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintPersonnelRecordService extends ServiceSupport<ComplaintPersonnelRecord>{

    @Autowired
    private ComplaintPersonnelRecordMapper complaintPersonnelRecordMapper;

    public PageResult<ComplaintPersonnelRecordSimpleVO> findComplaintRecordPage(ComplaintPersonnelRecordQueryDTO query) {
        Page<ComplaintPersonnelRecordSimpleVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionUtil.DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        complaintPersonnelRecordMapper.findComplaintPersonnel(query, permission);
        return new PageResult<>(page);
    }

}