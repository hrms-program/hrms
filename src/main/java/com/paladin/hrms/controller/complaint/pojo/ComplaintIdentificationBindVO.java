package com.paladin.hrms.controller.complaint.pojo;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.framework.core.container.AttachmentContainer;

import java.util.Date;
import java.util.List;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/11/21 14:43
 */
public class ComplaintIdentificationBindVO {

    private String personnelId;

    private String nowIdentificationNo;

    private Integer usedIdentificationType;

    private String usedIdentificationNo;

    private String attachments;

    private  Integer status;

    private  Integer result;

    private  String  illustrate;

    private  String checkPeople;

    private Date checkDate;


    // 获取附件文件
    public List<SysAttachment> getAttachmentFiles() {
        if (attachments != null && attachments.length() != 0) {
            return AttachmentContainer.getAttachments(attachments.split(","));
        }
        return null;
    }

    public Integer getUsedIdentificationType() {
        return usedIdentificationType;
    }

    public void setUsedIdentificationType(Integer usedIdentificationType) {
        this.usedIdentificationType = usedIdentificationType;
    }

    public String getUsedIdentificationNo() {
        return usedIdentificationNo;
    }

    public void setUsedIdentificationNo(String usedIdentificationNo) {
        this.usedIdentificationNo = usedIdentificationNo;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    public String getCheckPeople() {
        return checkPeople;
    }

    public void setCheckPeople(String checkPeople) {
        this.checkPeople = checkPeople;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(String personnelId) {
        this.personnelId = personnelId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getNowIdentificationNo() {
        return nowIdentificationNo;
    }

    public void setNowIdentificationNo(String nowIdentificationNo) {
        this.nowIdentificationNo = nowIdentificationNo;
    }
}
