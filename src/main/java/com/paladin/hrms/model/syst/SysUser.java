package com.paladin.hrms.model.syst;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

import com.paladin.framework.common.BaseModel;

public class SysUser extends BaseModel implements Serializable {

	private static final long serialVersionUID = -1534400185542562200L;

	/**
	 * 启用状态
	 */
	public final static int STATE_ENABLED = 1;
	/**
	 * 停用状态
	 */
	public final static int STATE_DISABLED = 0;
    /**
     * 待启用状态
     */
    public final static int STATE_WAITING_ENABLED = 2;
    /**
     * 禁用状态
     */
    public final static int STATE_DISABLE = 3;
    /**
     * 注销状态
     */
    public final static int STATE_LOGOUT = 4;
    /**
     * 锁定状态
     */
    public final static int STATE_LOCKING = 5;


	/**
	 * 系统管理员账号
	 */
	public final static int TYPE_SYSTEM_ADMIN = 1;
	/**
	 * 地区管理员
	 */
	public final static int TYPE_DISTRICT_MANAGER = 2;
	/**
	 * 机构管理员
	 */
	public final static int TYPE_AGENCY_MANAGER = 3;
	/**
	 * 个人用户账号
	 */
	public final static int TYPE_PERSONAL_USER = 4;
	
	

	public final static String COLUMN_FIELD_ACCOUNT = "account";
	public final static String COLUMN_FIELD_USER_ID = "userId";
	public final static String COLUMN_FIELD_STATE = "state";

	@Id
	@GeneratedValue(generator = "UUID")
	private String id;
	private String account;
	private String password;
	private String salt;
	private String userId;
	private Integer state;
	private Integer type;
	private Long times;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }
}
