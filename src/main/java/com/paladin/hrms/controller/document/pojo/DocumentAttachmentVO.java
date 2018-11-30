package com.paladin.hrms.controller.document.pojo;

import java.util.Date;

public class DocumentAttachmentVO {
	 private String id;

	    private String attachName;

	    private String originalName;

	    private String url;

	    private String type;

	    private String suffix;

	    private String createdId;

	    private Date createdAt;

	    private String updatedId;

	    private Date updatedAt;

	    private String remarks;

	    private String state;

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id == null ? null : id.trim();
	    }

	    public String getAttachName() {
	        return attachName;
	    }

	    public void setAttachName(String attachName) {
	        this.attachName = attachName == null ? null : attachName.trim();
	    }

	    public String getOriginalName() {
	        return originalName;
	    }

	    public void setOriginalName(String originalName) {
	        this.originalName = originalName == null ? null : originalName.trim();
	    }

	    public String getUrl() {
	        return url;
	    }

	    public void setUrl(String url) {
	        this.url = url == null ? null : url.trim();
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type == null ? null : type.trim();
	    }

	    public String getSuffix() {
	        return suffix;
	    }

	    public void setSuffix(String suffix) {
	        this.suffix = suffix == null ? null : suffix.trim();
	    }

	    public String getCreatedId() {
	        return createdId;
	    }

	    public void setCreatedId(String createdId) {
	        this.createdId = createdId == null ? null : createdId.trim();
	    }

	    public Date getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(Date createdAt) {
	        this.createdAt = createdAt;
	    }

	    public String getUpdatedId() {
	        return updatedId;
	    }

	    public void setUpdatedId(String updatedId) {
	        this.updatedId = updatedId == null ? null : updatedId.trim();
	    }

	    public Date getUpdatedAt() {
	        return updatedAt;
	    }

	    public void setUpdatedAt(Date updatedAt) {
	        this.updatedAt = updatedAt;
	    }

	    public String getRemarks() {
	        return remarks;
	    }

	    public void setRemarks(String remarks) {
	        this.remarks = remarks == null ? null : remarks.trim();
	    }

	    public String getState() {
	        return state;
	    }

	    public void setState(String state) {
	        this.state = state == null ? null : state.trim();
	    }
}
