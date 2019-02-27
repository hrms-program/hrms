package com.paladin.hrms.service.complaint;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.complaint.ComplaintPersonnelArchivesCheckRecordMapper;
import com.paladin.hrms.model.complaint.ComplaintCheckCount;
import com.paladin.hrms.model.complaint.ComplaintPersonnelArchivesCheckRecord;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckQueryDTO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckRecordVO;
import com.paladin.hrms.service.complaint.dto.ComplaintPersonnelArchivesCheckVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintPersonnelArchivesCheckRecordService extends ServiceSupport<ComplaintPersonnelArchivesCheckRecord>{
	@Autowired
	private ComplaintPersonnelArchivesCheckRecordMapper complaintPersonnelArchivesCheckRecordMapper;
	
	public ComplaintCheckCount indexShowCount(){
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, null);
		return complaintPersonnelArchivesCheckRecordMapper.indexShowCount(permission);
	}

	public PageResult<ComplaintPersonnelArchivesCheckVO> searchCheckRecord(ComplaintPersonnelArchivesCheckQueryDTO query) {
		Page<ComplaintPersonnelArchivesCheckVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		complaintPersonnelArchivesCheckRecordMapper.showCheckRecords(query, permission);
		return new PageResult<>(page);
	}

	public ComplaintPersonnelArchivesCheckRecordVO getArchivesCheckRecordByRecordId(String recordId, Integer recordType) {
		return complaintPersonnelArchivesCheckRecordMapper.showCheckRecordByRecordId(recordId,recordType);
	}


	public ComplaintCheckCount indexDistrictShowCount() {
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, null);
		return complaintPersonnelArchivesCheckRecordMapper.indexDistrictShowCount(permission);
	}
}