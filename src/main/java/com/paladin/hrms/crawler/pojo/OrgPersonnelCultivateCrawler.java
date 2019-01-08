package com.paladin.hrms.crawler.pojo;

import java.util.Date;
import java.util.List;

/**   
 * @author 黄伟华
 * @version 2018年12月12日 上午9:45:09 
 */
public class OrgPersonnelCultivateCrawler{
    
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
    
    private List<String> url;

    public List<String> getUrl()
    {
        return url;
    }

    public void setUrl(List<String> url)
    {
        this.url = url;
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

}
