package com.paladin.hrms.model.complaint;

public class ComplaintCheckCount {
	private int pendCount;
	private int nopassCount;
	private int passCount;
	private int checkedCount;
	public int getPassCount() {
		return passCount;
	}
	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}
	public int getNopassCount() {
		return nopassCount;
	}
	public void setNopassCount(int nopassCount) {
		this.nopassCount = nopassCount;
	}
	public int getPendCount() {
		return pendCount;
	}
	public void setPendCount(int pendCount) {
		this.pendCount = pendCount;
	}
	public int getCheckedCount() {
		return checkedCount;
	}
	public void setCheckedCount(int checkedCount) {
		this.checkedCount = checkedCount;
	}
}
