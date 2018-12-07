package com.paladin.hrms.model.syst;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**   
 * @author 黄伟华
 * @version 2018年12月5日 上午9:54:34 
 */
public class SysApp{
    
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;
    
    private String attachmentId;
    
    private String describes;
    
    private String version;

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
