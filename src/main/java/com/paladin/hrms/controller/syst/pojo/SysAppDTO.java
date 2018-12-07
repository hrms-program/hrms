package com.paladin.hrms.controller.syst.pojo;


/**   
 * @author 黄伟华
 * @version 2018年12月5日 下午1:49:08 
 */
public class SysAppDTO{
    
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
