package com.onteacher.vo;

import org.springframework.stereotype.Component;

@Component("notification")
public class Notification {
	
	private int id; // PK
	private int toId;
	private String content;
	private String createdAt; // Date
	private boolean isChecked;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getToId() {
		return toId;
	}
	public void setToId(int toId) {
		this.toId = toId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	@Override
	public String toString() {
		return "Notification [id=" + id + ", toId=" + toId + ", content=" + content + ", createdAt=" + createdAt + "]";
	}
	
}
