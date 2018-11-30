package com.paladin.hrms.service.complaint;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paladin.hrms.controller.complaint.pojo.ComplaintIcExchangeQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.complaint.ComplaintIcExchangeMapper;
import com.paladin.hrms.model.complaint.ComplaintIcExchange;
import com.paladin.hrms.model.org.OrgPersonnel;
import com.paladin.hrms.service.complaint.dto.ComplaintIcExchangeDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;

@Service
public class ComplaintIcExchangeService extends ServiceSupport<ComplaintIcExchange>{
      
      @Autowired
      private ComplaintIcExchangeMapper complaintIcExchangeMapper;

      
      public PageResult<ComplaintIcExchangeDTO> searchTableList(ComplaintIcExchangeQuery query) {
            Page<ComplaintIcExchangeDTO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
            DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, null);
            complaintIcExchangeMapper.searchTableList(query, permission);
            return new PageResult<>(page);
        }

      public List<OrgPersonnel> validate(String idcard) {
            DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, null);
            return complaintIcExchangeMapper.validate(idcard, permission) ;
      }
      
      


    

}