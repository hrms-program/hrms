package com.paladin.hrms.mapper.registe;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.registe.pojo.RegistePersonnelInforDTO;
import com.paladin.hrms.controller.registe.pojo.RegistePersonnelInforQuery;
import com.paladin.hrms.model.registe.RegistePersonnelInfor;

public interface RegistePersonnelInforMapper extends CustomMapper<RegistePersonnelInfor>{

	Page<RegistePersonnelInforDTO> searchTableList(@Param("query")RegistePersonnelInforQuery query);

}
