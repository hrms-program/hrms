package com.paladin.hrms.service.complaint;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.copy.SimpleBeanCopier;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.hrms.controller.complaint.pojo.IdentificationBindQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.HrmsUserSession;
import com.paladin.hrms.mapper.complaint.ComplaintIdentificationBindRecordMapper;
import com.paladin.hrms.model.complaint.ComplaintIdentificationBind;
import com.paladin.hrms.model.complaint.ComplaintIdentificationBindRecord;
import com.paladin.hrms.model.complaint.ComplaintModel;
import com.paladin.hrms.service.complaint.dto.ComplaintIdentificationBindSimpleVO;
import com.paladin.hrms.controller.complaint.pojo.ComplaintIdentificationBindVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ComplaintIdentificationBindService extends ComplaintServiceSupport<ComplaintIdentificationBind>{

    @Autowired
    private ComplaintIdentificationBindRecordMapper complaintIdentificationBindRecordMapper;

    @Autowired
    private ComplaintIdentificationBindRecordService complaintIdentificationBindRecordService;

    public PageResult<ComplaintIdentificationBindSimpleVO> findComplaintPersonnelPage(IdentificationBindQuery query) {
        Page<ComplaintIdentificationBindSimpleVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionUtil.DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        complaintIdentificationBindRecordMapper.findComplaintPersonnel(query, permission);
        return new PageResult<>(page);
    }


    public int updateState(ComplaintIdentificationBindVO bind) {
        return complaintIdentificationBindRecordMapper.updateState(bind);
    }

    public ComplaintIdentificationBind appCheck(String personnelId){
        List<ComplaintIdentificationBind> bind =
            searchAll(new GeneralCriteriaBuilder.Condition[] {
                new GeneralCriteriaBuilder.Condition(ComplaintIdentificationBind.COLUMN_FIELD_PERSONNEL_ID, QueryType.EQUAL,
                    personnelId),
                new GeneralCriteriaBuilder.Condition(ComplaintIdentificationBind.COLUMN_STATUS, QueryType.EQUAL, 0)});
        return (bind != null && bind.size() > 0) ? bind.get(0) : null;
    }

    public ComplaintIdentificationBindVO getComplaintPersonnel(String id) {

        return complaintIdentificationBindRecordMapper.getComplaintPersonnel(id);
    }


    /**
     * 审核
     *
     * @param id
     * @param illustrate
     * @param success
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean check(String id, String illustrate, boolean success) {
        ComplaintIdentificationBindVO complaintPersonnel= getComplaintPersonnel(id);
        if (complaintPersonnel.getStatus() == null) {
            throw new BusinessException("找不到审核对象");
        }
        int status = success ? ComplaintModel.STATUS_SUCCESS : ComplaintModel.STATUS_FAIL;
        complaintPersonnel.setStatus(status);
        complaintPersonnel.setIllustrate(illustrate);
        complaintPersonnel.setCheckDate(new Date());
        complaintPersonnel.setCheckPeople(HrmsUserSession.getCurrentUserSession().getUserName());
        if (updateState(complaintPersonnel) > 0) {
            ComplaintIdentificationBindRecord record = new ComplaintIdentificationBindRecord();
            SimpleBeanCopier.SimpleBeanCopyUtil.simpleCopy(complaintPersonnel, record);
            record.setResult(status);
            complaintIdentificationBindRecordService.save(record);
            return true;
        }
        return false;
    }
}