package com.paladin.hrms.controller.syst.pojo;

import java.util.List;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.framework.core.container.AttachmentContainer;

/**   
 * @author 黄伟华
 * @version 2018年12月5日 下午12:34:34 
 */
public class SysAppVO{
    
    private String id;
    
    private String attachmentId;
    
    private String describes;
    
    private String version;
    
 // 获取附件文件
    public List<SysAttachment> getAttachmentFiles() {
        if (attachmentId != null && attachmentId.length() != 0) {
            return AttachmentContainer.getAttachments(attachmentId.split(","));
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

    public String getAttachmentId()
    {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId)
    {
        this.attachmentId = attachmentId;
    }

    public String getDescribes()
    {
        return describes;
    }

    public void setDescribes(String describes)
    {
        this.describes = describes;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }
    
    
}
