package com.paladin.hrms.controller.org.pojo;

import java.util.Date;
import java.util.List;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.framework.core.container.AttachmentContainer;

/**   
 * @author 黄伟华
 * @version 2018年10月30日 下午2:12:43 
 */
public class OrgPersonnelWorkExperienceVO{
    
    private String id;

    private String personnelId;

    private String workUnit;

    private String workPlace;

    private Date workStartTime;

    private Date workEndTime;

    private Integer workDept;

    private Integer jobSituation;

    private Integer workPost;

    private Integer majorTechnologyWork;

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

    public String getWorkUnit()
    {
        return workUnit;
    }

    public void setWorkUnit(String workUnit)
    {
        this.workUnit = workUnit;
    }

    public String getWorkPlace()
    {
        return workPlace;
    }

    public void setWorkPlace(String workPlace)
    {
        this.workPlace = workPlace;
    }

    public Date getWorkStartTime()
    {
        return workStartTime;
    }

    public void setWorkStartTime(Date workStartTime)
    {
        this.workStartTime = workStartTime;
    }

    public Date getWorkEndTime()
    {
        return workEndTime;
    }

    public void setWorkEndTime(Date workEndTime)
    {
        this.workEndTime = workEndTime;
    }

    public Integer getWorkDept()
    {
        return workDept;
    }

    public void setWorkDept(Integer workDept)
    {
        this.workDept = workDept;
    }

    public Integer getJobSituation()
    {
        return jobSituation;
    }

    public void setJobSituation(Integer jobSituation)
    {
        this.jobSituation = jobSituation;
    }

    public Integer getWorkPost()
    {
        return workPost;
    }

    public void setWorkPost(Integer workPost)
    {
        this.workPost = workPost;
    }

    public Integer getMajorTechnologyWork()
    {
        return majorTechnologyWork;
    }

    public void setMajorTechnologyWork(Integer majorTechnologyWork)
    {
        this.majorTechnologyWork = majorTechnologyWork;
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
