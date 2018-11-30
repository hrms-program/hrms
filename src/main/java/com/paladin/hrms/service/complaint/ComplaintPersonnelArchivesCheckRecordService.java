package com.paladin.hrms.service.complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.complaint.ComplaintPersonnelArchivesCheckRecordMapper;
import com.paladin.hrms.model.complaint.ComplaintCheckCount;
import com.paladin.hrms.model.complaint.ComplaintPersonnelArchivesCheckRecord;
import com.paladin.framework.core.ServiceSupport;

@Service
public class ComplaintPersonnelArchivesCheckRecordService extends ServiceSupport<ComplaintPersonnelArchivesCheckRecord>{
	@Autowired
	private ComplaintPersonnelArchivesCheckRecordMapper complaintPersonnelArchivesCheckRecordMapper;
	
	public ComplaintCheckCount indexShowCount(){
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, null);
		return complaintPersonnelArchivesCheckRecordMapper.indexShowCount(permission);
	}
}