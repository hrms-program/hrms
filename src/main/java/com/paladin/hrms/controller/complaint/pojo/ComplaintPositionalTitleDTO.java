package com.paladin.hrms.controller.complaint.pojo;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**   
 * @author 黄伟华
 * @version 2018年10月22日 上午9:29:41 
 */
public class ComplaintPositionalTitleDTO{
    
    private String id;
    
    @NotNull(message = "修改类型不能为空")
    private Integer modifyType;

    private String newPositionalTitleName;
   
    private Integer newPositionalLevelLevel;
    
    private Date newPositionalTitleTime;

    private String attachmentId;//附件
    
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Date getNewPositionalTitleTime()
    {
        return newPositionalTitleTime;
    }

    public void setNewPositionalTitleTime(Date newPositionalTitleTime)
    {
        this.newPositionalTitleTime = newPositionalTitleTime;
    }

    public String getNewPositionalTitleName()
    {
        return newPositionalTitleName;
    }

    public void setNewPositionalTitleName(String newPositionalTitleName)
    {
        this.newPositionalTitleName = newPositionalTitleName;
    }

    public Integer getNewPositionalLevelLevel()
    {
        return newPositionalLevelLevel;
    }

    public void setNewPositionalLevelLevel(Integer newPositionalLevelLevel)
    {
        this.newPositionalLevelLevel = newPositionalLevelLevel;
    }

    public String getAttachmentId()
    {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId)
    {
        this.attachmentId = attachmentId;
    }

	public Integer getModifyType() {
		return modifyType;
	}

	public void setModifyType(Integer modifyType) {
		this.modifyType = modifyType;
	}
    
    
}
