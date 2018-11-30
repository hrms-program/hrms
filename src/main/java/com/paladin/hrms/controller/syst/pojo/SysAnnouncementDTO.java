package com.paladin.hrms.controller.syst.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.framework.core.container.AttachmentContainer;

public class SysAnnouncementDTO {
	
	private String id;
	@NotEmpty(message = "公告标题不能为空")
	private String title;
	@NotNull(message = "公告类型不能为空")
	private Integer type;
	@NotEmpty(message = "公告内容不能为空")
	private String content;
	private Date publishedTime;
	private String attachments;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishedTime() {
		return publishedTime;
	}
	public void setPublishedTime(Date publishedTime) {
		this.publishedTime = publishedTime;
	}
	public String getAttachments() {
		return attachments;
	}
	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

}
