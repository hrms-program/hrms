package com.paladin.hrms.crawler.pojo;

import java.util.Date;
import java.util.List;

/**   
 * @author 黄伟华
 * @version 2018年12月14日 下午5:12:04 
 */
public class OrgPersonnelRewardInfoCrawler{
    
    private String id;
    
    private String personnelId; 
    
    private String rewardProject;

    private String rewardLevel;

    private Integer rewardType;

    private Date rewardTime;

    private String awardPrizeDept;

    private Integer isRevoke;

    private String rewardMoney;

    private String rewardReason;

    private String rewardIllustrate;

    private String attachments;

    private Integer status;
    
    private List<String> url;
    
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

    public String getRewardProject()
    {
        return rewardProject;
    }

    public void setRewardProject(String rewardProject)
    {
        this.rewardProject = rewardProject;
    }

    public String getRewardLevel()
    {
        return rewardLevel;
    }

    public void setRewardLevel(String rewardLevel)
    {
        this.rewardLevel = rewardLevel;
    }

    public Integer getRewardType()
    {
        return rewardType;
    }

    public void setRewardType(Integer rewardType)
    {
        this.rewardType = rewardType;
    }

    public Date getRewardTime()
    {
        return rewardTime;
    }

    public void setRewardTime(Date rewardTime)
    {
        this.rewardTime = rewardTime;
    }

    public String getAwardPrizeDept()
    {
        return awardPrizeDept;
    }

    public void setAwardPrizeDept(String awardPrizeDept)
    {
        this.awardPrizeDept = awardPrizeDept;
    }

    public Integer getIsRevoke()
    {
        return isRevoke;
    }

    public void setIsRevoke(Integer isRevoke)
    {
        this.isRevoke = isRevoke;
    }

    public String getRewardMoney()
    {
        return rewardMoney;
    }

    public void setRewardMoney(String rewardMoney)
    {
        this.rewardMoney = rewardMoney;
    }

    public String getRewardReason()
    {
        return rewardReason;
    }

    public void setRewardReason(String rewardReason)
    {
        this.rewardReason = rewardReason;
    }

    public String getRewardIllustrate()
    {
        return rewardIllustrate;
    }

    public void setRewardIllustrate(String rewardIllustrate)
    {
        this.rewardIllustrate = rewardIllustrate;
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

    public List<String> getUrl()
    {
        return url;
    }

    public void setUrl(List<String> url)
    {
        this.url = url;
    }
    
}
