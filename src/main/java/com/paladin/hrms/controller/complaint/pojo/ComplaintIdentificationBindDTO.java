package com.paladin.hrms.controller.complaint.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/11/21 14:44
 */
public class ComplaintIdentificationBindDTO {

    private String personnelId;

    @NotNull(message = "请认真选择证件类型!")
    private Integer usedIdentificationType;

    @NotEmpty(message = "证件号码不能为空!")
    private String usedIdentificationNo;

    private String attachments;

    private  String  illustrate;

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

    public String getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(String personnelId) {
        this.personnelId = personnelId;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }
}
