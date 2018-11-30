package com.paladin.framework.utils;

public class SysContants {
	
	public static final String C_PREFIX="C_";

	/***
	 * 是否有效，Y-有效，N-无效
	 */
	public static final String Y = "Y";

	public static final String N = "N";
	/***
	 * 是否有效，Y-有效，N-无效
	 */
	public static final String READ = "1";
	
	public static final String NOT_READ = "0";
	
	/***
	 * 是否有效，1-有效，99-无效
	 */
	public static final String ACTIVE = "1";

	public static final String INACTIVE = "0";

	public static final String DELETED = "99";
	/***
	 * 是否有效，1-有效，99-无效
	 */
	public static final Integer ACTIVE_INT = 1;
	
	public static final Integer INACTIVE_INT = 0;
	
	public static final Integer DELETED_INT = 99;

	/***
	 * 短信发送状态  1 发送成功 2草稿箱 99发送失败
	 */
	public static final String MSG_SEND_SUCCESS = "1";

	public static final String MSG_SEND_FAIL = "99";
	/**
	 * 短信额度状态：加值：1，减值：-1
	 */
	public static final String QUOTA_PLUS="1";
	
	public static final String QUOTA_MINUS="-1";
	
	/**
	 * 短信状态：1成功，2草稿，99失败
	 */
	public static final String SMS_STATUS_SUCCESS="1";
	
	public static final String SMS_STATUS_DRAFT="2";
	
	public static final String SMS_STATUS_FAILURE="99";
	
	/**
	 * 符号：0.空白
	 */
	public static final String BLANK="";
	/**
	 * 英文符号:1.逗号
	 */
	public static final String EN_COMMA = ",";
	/**
	 * 英文符号:2.横杠
	 */
	public static final String EN_BAR = "-";
	/**
	 * 英文符号:2.横杠
	 */
	public static final String EN_UNDER_LINE = "_";
	/**
	 * 英文符号:3.点号
	 */
	public static final String EN_DOT = ".";
	/**
	 * 英文符号:4.冒号
	 */
	public static final String EN_COLON=":";
	/**
	 * 英文符号:5.斜杠
	 */
	public static final String EN_SLASH="/";
	/**
	 * 英文符号：6.左中括号
	 */
	public static final String LEFT_BRACKET="[";
	/**
	 * 英文符号：7.右中括号
	 */
	public static final String RIGHT_BRACKET="]";
	
	/*超数据权限用户或角色**/
	public static final String SYS_ADMIN="系统管理员";
	
	public static final String ADMIN="admin";
	
	/*根据所处模块查询公告信息列表*/
	public static final String ATTACHMENT_FIVE="5";
	public static final String ATTACHMENT_TEN="10";
	public static final String ATTACHMENT_ALL="ALL";
	
	/**
	 * isDelete
	 */
	public static final String IS_DELETE="0";
	
	public static final String DELETE="1";
}
