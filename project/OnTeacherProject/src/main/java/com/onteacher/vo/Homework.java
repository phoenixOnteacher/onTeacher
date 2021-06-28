package com.onteacher.vo;

import org.springframework.stereotype.Component;

@Component("homework")
public class Homework {
	
	private int id; //PK
	private String title;
	private String content;
	private String filename;
	private String deadline; //Date
	private int courseId; //FK
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	@Override
	public String toString() {
		return "Homework [id=" + id + ", title=" + title + ", content=" + content + ", filename=" + filename
				+ ", deadline=" + deadline + ", courseId=" + courseId + "]";
	}
	
}
