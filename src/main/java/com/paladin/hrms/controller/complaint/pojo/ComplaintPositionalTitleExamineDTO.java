package com.paladin.hrms.controller.complaint.pojo;


/**   
 * @author 黄伟华
 * @version 2018年10月23日 上午10:33:50 
 */
public class ComplaintPositionalTitleExamineDTO{
   
    
    private String id;
    
    private String remarks;

    private Integer status;
    
 
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

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }
}
