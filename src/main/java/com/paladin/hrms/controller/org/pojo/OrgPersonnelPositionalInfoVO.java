package com.paladin.hrms.controller.org.pojo;

import java.util.Date;
import java.util.List;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.framework.core.container.AttachmentContainer;

/**   
 * @author 黄伟华
 * @version 2018年10月30日 下午2:38:16 
 */
public class OrgPersonnelPositionalInfoVO{
    private String id;

    private String personnelId;

    private String positionalTitleName;

    private Integer positionalTitleType;

    private Integer positionalLevelLevel;

    private Date positionalTitleTime;

    private String attachments;

    private Integer status;
    
    // 获取附件文件
    public List<SysAttachment> getAttachmentFiles() {
        if (attachments != null && attachments.length() != 0) {
            return AttachmentContainer.getAttachments(attachments.split(","));
        }
        return null;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPersonnelId()
    {
        return personnelId;
    }

    public void setPersonnelId(String personnelId)
    {
        this.personnelId = personnelId;
    }

    public String getPositionalTitleName()
    {
        return positionalTitleName;
    }

    public void setPositionalTitleName(String positionalTitleName)
    {
        this.positionalTitleName = positionalTitleName;
    }

    public Integer getPositionalTitleType()
    {
        return positionalTitleType;
    }

    public void setPositionalTitleType(Integer positionalTitleType)
    {
        this.positionalTitleType = positionalTitleType;
    }

    public Integer getPositionalLevelLevel()
    {
        return positionalLevelLevel;
    }

    public void setPositionalLevelLevel(Integer positionalLevelLevel)
    {
        this.positionalLevelLevel = positionalLevelLevel;
    }

    public Date getPositionalTitleTime()
    {
        return positionalTitleTime;
    }

    public void setPositionalTitleTime(Date positionalTitleTime)
    {
        this.positionalTitleTime = positionalTitleTime;
    }

    public String getAttachments()
    {
        return attachments;
    }

    public void setAttachments(String attachments)
    {
        this.attachments = attachments;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    
}
