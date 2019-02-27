package com.paladin.hrms.service.registe;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.hrms.controller.registe.pojo.RegistePersonnelInforDTO;
import com.paladin.hrms.controller.registe.pojo.RegistePersonnelInforQuery;
import com.paladin.hrms.mapper.registe.RegistePersonnelInforMapper;
import com.paladin.hrms.model.registe.RegistePersonnelInfor;

@Service
public class RegistePersonnelInforService extends ServiceSupport<RegistePersonnelInfor>{

	@Autowired
	private RegistePersonnelInforMapper registePersonnelInforMapper;

	public PageResult<RegistePersonnelInforDTO> searchTableList(RegistePersonnelInforQuery query) {
	    Page<RegistePersonnelInforDTO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
//        String agencyId = query.getAgencyId();
//        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
//        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
	    registePersonnelInforMapper.searchTableList(query);
	    return new PageResult<>(page);
	}

	
	@Transactional
    public int saveOrUpdate(RegistePersonnelInfor model) {
		int effect=0;
		UserSession userSession = UserSession.getCurrentUserSession();
		if(StringUtils.isNotBlank(model.getId())){
			//先删除后新增
			RegistePersonnelInfor dbEntity=this.get(model.getId());
			if(dbEntity==null||dbEntity.getIsDelete()==RegistePersonnelInfor.IS_DELETED){
				throw new BusinessException("数据库中已不存在该记录，请刷新后重试！");
			}
			if(RegistePersonnelInfor.AGENCY_CHECK_PASS.equals(dbEntity.getAgencyCheckStatus())){
				if(RegistePersonnelInfor.ADMIN_UNCHECK.equals(dbEntity.getAdminCheckStatus())){
					throw new BusinessException("机构审核通过，等待行政审核中，不允许做修改操作！");
				}
			}
			if(RegistePersonnelInfor.ADMIN_CHECK_PASS.equals(dbEntity.getAdminCheckStatus())){
				throw new BusinessException("行政审核通过，不允许做修改操作！");
			}
			dbEntity.setUpdateUserId(userSession.getUserId());
			dbEntity.setUpdateTime(new Date());
			dbEntity.setIsDelete(RegistePersonnelInfor.IS_DELETED);
			registePersonnelInforMapper.updateByPrimaryKeySelective(dbEntity);
			model.setId(UUIDUtil.createUUID());
			model.setIdentificationNo(userSession.getAccount());
			model.setCreateUserId(dbEntity.getUpdateUserId());
			model.setCreateTime(dbEntity.getUpdateTime());
			model.setUpdateUserId(dbEntity.getUpdateUserId());
			model.setUpdateTime(dbEntity.getUpdateTime());
			model.setApplyDate(dbEntity.getUpdateTime());
			model.setIsDelete(RegistePersonnelInfor.UN_DELETED);
			model.setAgencyCheckStatus(RegistePersonnelInfor.AGENCY_UNCHECK);
			model.setAdminCheckStatus(RegistePersonnelInfor.ADMIN_UNCHECK);
			effect=registePersonnelInforMapper.insert(model);
		}else{
			saveModelWrap(model);
			model.setId(UUIDUtil.createUUID());
			model.setBusiId(model.getId());
			model.setIdentificationNo(userSession.getAccount());
			model.setIsDelete(RegistePersonnelInfor.UN_DELETED);
			model.setApplyDate(model.getCreateTime());
			model.setAgencyCheckStatus(RegistePersonnelInfor.AGENCY_UNCHECK);
			model.setAdminCheckStatus(RegistePersonnelInfor.ADMIN_UNCHECK);
			effect=registePersonnelInforMapper.insert(model);
		}
		return effect;
    }

	@Transactional
	public int remove(String id) {
		int effect=0;
		if(StringUtils.isBlank(id)){
			throw new BusinessException("删除条件为空！");
		}
		//删除
		RegistePersonnelInfor dbEntity=this.get(id);
		if(dbEntity==null||dbEntity.getIsDelete()==RegistePersonnelInfor.IS_DELETED){
			throw new BusinessException("数据库中已不存在该记录，请刷新后重试！");
		}
		if(RegistePersonnelInfor.AGENCY_UNCHECK.equals(dbEntity.getAgencyCheckStatus())){
			throw new BusinessException("机构已审，不允许做修改操作！");
		}
		if(RegistePersonnelInfor.ADMIN_UNCHECK.equals(dbEntity.getAdminCheckStatus())){
			throw new BusinessException("行政已审，不允许做修改操作！");
		}
		dbEntity.setUpdateUserId(UserSession.getCurrentUserSession().getUserId());
		dbEntity.setUpdateTime(new Date());
		dbEntity.setIsDelete(RegistePersonnelInfor.IS_DELETED);
		return registePersonnelInforMapper.updateByPrimaryKeySelective(dbEntity);
	}


	@Transactional
	public int check(RegistePersonnelInfor model) {
		return registePersonnelInforMapper.updateByPrimaryKeySelective(model);
	}

}
