package com.paladin.hrms.crawler.pojo;

import java.util.Date;
import java.util.List;

/**   
 * @author 黄伟华
 * @version 2018年12月17日 上午10:16:17 
 */
public class OrgPersonnelScienceEducationCrawler{
    
    private String id;
    
    private String personnelId; 
    
    private Integer scienceEducationType;

    private String name;

    private String element;

    private String oneselfRanking;

    private String topicNumber;

    private String topicSource;

    private String year;

    private String money;

    private Date endTime;

    private String dept;

    private String prizeIssuingOrgan;

    private String prizeGrade;

    private String awardProject;

    private Date awardTime;

    private String awardTopic;

    private String paperMagazine;

    private String sciInfluenceFactor;

    private String paperFirstAuthor;

    private String paperCommunicationAuthor;

    private Date paperReleaseTime;

    private Integer paperType;

    private String paperRelationTopic;

    private String attachments;
    
    private Integer status;
   
    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    private List<String> url;
    
    public List<String> getUrl()
    {
        return url;
    }

    public void setUrl(List<String> url)
    {
        this.url = url;
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

    public Integer getScienceEducationType()
    {
        return scienceEducationType;
    }

    public void setScienceEducationType(Integer scienceEducationType)
    {
        this.scienceEducationType = scienceEducationType;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getElement()
    {
        return element;
    }

    public void setElement(String element)
    {
        this.element = element;
    }

    public String getOneselfRanking()
    {
        return oneselfRanking;
    }

    public void setOneselfRanking(String oneselfRanking)
    {
        this.oneselfRanking = oneselfRanking;
    }

    public String getTopicNumber()
    {
        return topicNumber;
    }

    public void setTopicNumber(String topicNumber)
    {
        this.topicNumber = topicNumber;
    }

    public String getTopicSource()
    {
        return topicSource;
    }

    public void setTopicSource(String topicSource)
    {
        this.topicSource = topicSource;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getMoney()
    {
        return money;
    }

    public void setMoney(String money)
    {
        this.money = money;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public String getDept()
    {
        return dept;
    }

    public void setDept(String dept)
    {
        this.dept = dept;
    }

    public String getPrizeIssuingOrgan()
    {
        return prizeIssuingOrgan;
    }

    public void setPrizeIssuingOrgan(String prizeIssuingOrgan)
    {
        this.prizeIssuingOrgan = prizeIssuingOrgan;
    }

    public String getPrizeGrade()
    {
        return prizeGrade;
    }

    public void setPrizeGrade(String prizeGrade)
    {
        this.prizeGrade = prizeGrade;
    }

    public String getAwardProject()
    {
        return awardProject;
    }

    public void setAwardProject(String awardProject)
    {
        this.awardProject = awardProject;
    }

    public Date getAwardTime()
    {
        return awardTime;
    }

    public void setAwardTime(Date awardTime)
    {
        this.awardTime = awardTime;
    }

    public String getAwardTopic()
    {
        return awardTopic;
    }

    public void setAwardTopic(String awardTopic)
    {
        this.awardTopic = awardTopic;
    }

    public String getPaperMagazine()
    {
        return paperMagazine;
    }

    public void setPaperMagazine(String paperMagazine)
    {
        this.paperMagazine = paperMagazine;
    }

    public String getSciInfluenceFactor()
    {
        return sciInfluenceFactor;
    }

    public void setSciInfluenceFactor(String sciInfluenceFactor)
    {
        this.sciInfluenceFactor = sciInfluenceFactor;
    }

    public String getPaperFirstAuthor()
    {
        return paperFirstAuthor;
    }

    public void setPaperFirstAuthor(String paperFirstAuthor)
    {
        this.paperFirstAuthor = paperFirstAuthor;
    }

    public String getPaperCommunicationAuthor()
    {
        return paperCommunicationAuthor;
    }

    public void setPaperCommunicationAuthor(String paperCommunicationAuthor)
    {
        this.paperCommunicationAuthor = paperCommunicationAuthor;
    }

    public Date getPaperReleaseTime()
    {
        return paperReleaseTime;
    }

    public void setPaperReleaseTime(Date paperReleaseTime)
    {
        this.paperReleaseTime = paperReleaseTime;
    }

    public Integer getPaperType()
    {
        return paperType;
    }

    public void setPaperType(Integer paperType)
    {
        this.paperType = paperType;
    }

    public String getPaperRelationTopic()
    {
        return paperRelationTopic;
    }

    public void setPaperRelationTopic(String paperRelationTopic)
    {
        this.paperRelationTopic = paperRelationTopic;
    }

    public String getAttachments()
    {
        return attachments;
    }

    public void setAttachments(String attachments)
    {
        this.attachments = attachments;
    }
    
    
}
