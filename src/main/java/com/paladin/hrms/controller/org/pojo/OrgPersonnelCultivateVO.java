package com.paladin.hrms.controller.org.pojo;

import java.util.Date;
import java.util.List;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.framework.core.container.AttachmentContainer;

/**   
 * @author 黄伟华
 * @version 2018年10月30日 下午2:38:54 
 */
public class OrgPersonnelCultivateVO{
    
    private String id;

    private String personnelId;

    private Integer cultivateType;

    private String cultivateUnit;

    private String cultivatePlace;

    private Date cultivateStartTime;

    private Date cultivateEndTime;

    private Integer endSituation;

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

    public Integer getCultivateType()
    {
        return cultivateType;
    }

    public void setCultivateType(Integer cultivateType)
    {
        this.cultivateType = cultivateType;
    }

    public String getCultivateUnit()
    {
        return cultivateUnit;
    }

    public void setCultivateUnit(String cultivateUnit)
    {
        this.cultivateUnit = cultivateUnit;
    }

    public String getCultivatePlace()
    {
        return cultivatePlace;
    }

    public void setCultivatePlace(String cultivatePlace)
    {
        this.cultivatePlace = cultivatePlace;
    }

    public Date getCultivateStartTime()
    {
        return cultivateStartTime;
    }

    public void setCultivateStartTime(Date cultivateStartTime)
    {
        this.cultivateStartTime = cultivateStartTime;
    }

    public Date getCultivateEndTime()
    {
        return cultivateEndTime;
    }

    public void setCultivateEndTime(Date cultivateEndTime)
    {
        this.cultivateEndTime = cultivateEndTime;
    }

    public Integer getEndSituation()
    {
        return endSituation;
    }

    public void setEndSituation(Integer endSituation)
    {
        this.endSituation = endSituation;
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
