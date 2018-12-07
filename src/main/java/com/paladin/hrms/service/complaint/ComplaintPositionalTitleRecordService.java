package com.paladin.hrms.service.complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paladin.hrms.mapper.complaint.ComplaintPositionalTitleMapper;
import com.paladin.hrms.mapper.complaint.ComplaintPositionalTitleRecordMapper;
import com.paladin.hrms.model.complaint.ComplaintPositionalTitle;
import com.paladin.hrms.model.complaint.ComplaintPositionalTitleRecord;
import com.paladin.hrms.model.org.OrgPersonnelPositionalInfo;
import com.paladin.hrms.service.org.OrgPersonnelPositionalInfoService;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;

@Service
public class ComplaintPositionalTitleRecordService extends ServiceSupport<ComplaintPositionalTitleRecord>{
    
    @Autowired
    ComplaintPositionalTitleRecordMapper complaintPositionalTitleRecordMapper;
    
    @Autowired
    ComplaintPositionalTitleMapper complaintPositionalTitleMapper;
    

    @Autowired
    OrgPersonnelPositionalInfoService  orgPersonnelPositionalInfoService;
    
    @Transactional
    public int complaintExamine(ComplaintPositionalTitleRecord record, Integer status){
        int effect = 0;
        
        if(status == ComplaintPositionalTitle.STATUS_SUCCESS){
            OrgPersonnelPositionalInfo info = new OrgPersonnelPositionalInfo();
            info.setId(record.getId());
            info.setPositionalTitleName(record.getNewPositionalTitleName());
            info.setPositionalLevelLevel(record.getNewPositionalLevelLevel());
            info.setPositionalTitleTime(record.getNewPositionalTitleTime());
            info.setAttachments(record.getNewAttachmentId());
            orgPersonnelPositionalInfoService.updateSelective(info);
        }
        if(status != ComplaintPositionalTitle.STATUS_SUCCESS && status != ComplaintPositionalTitle.STATUS_FAIL){
            throw new BusinessException("操作错误!");
        }
        save(record);
        
        ComplaintPositionalTitle title = new ComplaintPositionalTitle();
        title.setId(record.getId());
        title.setStatus(status);
        effect = complaintPositionalTitleMapper.updateByPrimaryKeySelective(title);
        return effect;
    }

}