package com.onteacher.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("studentReview")
public class StudentReview {
	
	private int id; //PK
	private String content;
	private String createdAt; //Date
	private int courseId; //FK
	private int teacherId; //FK
	private int studentId; //FK
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	@Override
	public String toString() {
		return "StudentReview [id=" + id + ", content=" + content + ", createdAt=" + createdAt + ", courseId="
				+ courseId + ", teacherId=" + teacherId + ", studentId=" + studentId + "]";
	}
	
}
