package com.paladin.hrms.controller.org.pojo;

import javax.validation.constraints.NotEmpty;

/**   
 * @author 黄伟华
 * @version 2018年11月7日 上午11:22:21 
 */
public class OrgAgencyManagerDTO{
    
    private String id;
    
    private String agencyId;
    
    private String name;
    
    @NotEmpty(message = "登录名不能为空")
    private String account;
    
    private String identificationNo;
    
    private String cellphone;
    
    private String sex;
    
    private String email;
    
    private String qq;
    
    private String departmentPosition;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIdentificationNo()
    {
        return identificationNo;
    }

    public void setIdentificationNo(String identificationNo)
    {
        this.identificationNo = identificationNo;
    }

    public String getCellphone()
    {
        return cellphone;
    }

    public void setCellphone(String cellphone)
    {
        this.cellphone = cellphone;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getQq()
    {
        return qq;
    }

    public void setQq(String qq)
    {
        this.qq = qq;
    }

    public String getDepartmentPosition()
    {
        return departmentPosition;
    }

    public void setDepartmentPosition(String departmentPosition)
    {
        this.departmentPosition = departmentPosition;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getAgencyId()
    {
        return agencyId;
    }

    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }
    
}
