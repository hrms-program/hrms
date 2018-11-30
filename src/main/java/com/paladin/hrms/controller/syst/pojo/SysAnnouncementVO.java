package com.paladin.hrms.controller.syst.pojo;

import java.util.Date;
import java.util.List;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.framework.core.container.AttachmentContainer;

public class SysAnnouncementVO {

	private String id;

	private String title;

	private Integer type;

	private String content;

	private Date publishedTime;

	private String attachments;

	@SuppressWarnings("unused")
	private List<SysAttachment> attachmentFiles;

	// 获取附件文件
	public List<SysAttachment> getAttachmentFiles() {
		if (attachments != null && attachments.length() != 0) {
			return AttachmentContainer.getAttachments(attachments.split(","));
		}
		return null;
	}

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
