package com.paladin.framework.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 开关状态
 * @author FM
 */
public enum SwitchState {

	NO("0", "否"), YES("1", "是");
	// 成员变量
	private String status;
	private String msg;

	// 构造方法
	private SwitchState(String status, String msg) {
		this.msg = msg;
		this.status = status;
	}

	// 普通方法
	public static String getMsg(String status) {
		if (StringUtils.isBlank(status)) {
			return null;
		}
		for (SwitchState c : SwitchState.values()) {
			if (StringUtils.equals(status, c.getStatus())) {
				return c.msg;
			}
		}
		return null;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
