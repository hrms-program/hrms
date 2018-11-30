package com.paladin.hrms.model.syst;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.paladin.framework.common.UnDeleteBaseModel;

import tk.mybatis.mapper.annotation.IgnoreInMultipleResult;

public class SysAnnouncement extends UnDeleteBaseModel {
	@Id
	@GeneratedValue(generator = "UUID")
	private String id;
	
	private String title;
	
	private Integer type;
	
	@IgnoreInMultipleResult
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
