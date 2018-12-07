package com.paladin.hrms.mapper.syst;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.model.syst.SysApp;

/**   
 * @author 黄伟华
 * @version 2018年12月5日 上午9:57:15 
 */
public interface SysAppMapper extends CustomMapper<SysApp>{
    
    SysApp sysAppById();
    
}
