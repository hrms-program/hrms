package com.paladin.hrms.mapper.registe;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.registe.RegisteAgencyInfor;
import com.paladin.hrms.service.registe.dto.RegisteAgencyInforQueryDTO;
import com.paladin.hrms.service.registe.vo.RegisteAgencyInforVO;

public interface RegisteAgencyInforMapper extends CustomMapper<RegisteAgencyInfor>{

    public List<RegisteAgencyInforVO> findRegisteAgencyPage(RegisteAgencyInforQueryDTO query);
    
    public List<RegisteAgencyInforVO> findRegisteAgencyCheckPage(@Param("query") RegisteAgencyInforQueryDTO query,@Param("permission") DataPermissionCondition permission);
}