package com.paladin.hrms.service.complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.hrms.controller.complaint.pojo.ComplaintPositionalTitleConfirmQuery;
import com.paladin.hrms.controller.complaint.pojo.ComplaintPositionalTitleQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.complaint.ComplaintPositionalTitleMapper;
import com.paladin.hrms.mapper.complaint.ComplaintPositionalTitleRecordMapper;
import com.paladin.hrms.model.complaint.ComplaintModel;
import com.paladin.hrms.model.complaint.ComplaintPositionalTitle;
import com.paladin.hrms.model.complaint.ComplaintPositionalTitleRecord;
import com.paladin.hrms.service.complaint.dto.ComplaintPositionalTitleVO;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;

@Service
public class ComplaintPositionalTitleService extends ServiceSupport<ComplaintPositionalTitle>{
    
    @Autowired
    ComplaintPositionalTitleMapper complaintPositionalTitleMapper;
    
    @Autowired
    ComplaintPositionalTitleRecordMapper complaintPositionalTitleRecordMapper;
    
    /**
     * 查找职称变更申请
     * @param query
     * @return
     */
    public PageResult<ComplaintPositionalTitleVO> complaintPositionalTitleAll(ComplaintPositionalTitleQuery query) {
        Page<ComplaintPositionalTitleVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        complaintPositionalTitleMapper.complaintPositionalTitleAll(query, permission);
        return new PageResult<>(page);
    }
    
    
    /**职称变更申诉申请
     * 详情
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
   public ComplaintPositionalTitleVO complaintPositionalTitleDetail(String id){
        return this.complaintPositionalTitleMapper.complaintPositionalTitleDetail(id);
    }
   
   
	/**
	 * 新增或修改申请
	 * 
	 * @param model
	 * @return
	 */
	public int applyOrModifyComplaint(ComplaintPositionalTitle model) {
		String id = model.getId();
		if (id == null || id.length() == 0) {
			throw new BusinessException("没有选择对应修改职称");
		}

		model.setStatus(ComplaintModel.STATUS_WAITING);
		saveModelWrap(model);
		
		if(get(id) != null){
		    return update(model);
		}else{
		   return save(model); 
		}
	}

   /**职称变更确认
    * 首页
    * @return
    * @see [类、类#方法、类#成员]
    */
   public PageResult<ComplaintPositionalTitleVO> complaintPositionalTitleFind(ComplaintPositionalTitleConfirmQuery query){
       Page<ComplaintPositionalTitleVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
       String agencyId = query.getAgencyId();
       String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
       DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
       complaintPositionalTitleMapper.complaintPositionalTitleFind(query, permission);
       return new PageResult<>(page);
   }
   
   /**
    * 职称变更确认
    * 详情
    * @param id
    * @return
    * @see [类、类#方法、类#成员]
    */
   public ComplaintPositionalTitleRecord complaintPositionalTitleRecordDetail(String id){
       return this.complaintPositionalTitleMapper.complaintPositionalTitleRecordDetail(id);
   }
   
}