package com.onteacher.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("homeworkAnswer")
public class HomeworkAnswer {
	
	private String filename;
	private String createdAt; //Date
	private int studentId; //FK
	private int homeworkId; //FK
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}
	
	@Override
	public String toString() {
		return "HomeworkAnswer [filename=" + filename + ", createdAt=" + createdAt + ", studentId=" + studentId
				+ ", homeworkId=" + homeworkId + "]";
	}
}
