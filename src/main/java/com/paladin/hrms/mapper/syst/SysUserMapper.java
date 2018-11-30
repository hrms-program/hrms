package com.paladin.hrms.mapper.syst;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.model.syst.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends CustomMapper<SysUser>{

  public  int updateStateByAccount(@Param("account") String account, @Param("state") int state);
}
