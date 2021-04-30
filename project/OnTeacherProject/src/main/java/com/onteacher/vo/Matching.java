package com.onteacher.vo;

import org.springframework.stereotype.Component;

@Component("matching")
public class Matching {
	
	private int studentId; //PK, FK
	private int courseId; //PK, FK

	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	@Override
	public String toString() {
		return "Matching [studentId=" + studentId + ", courseId=" + courseId + "]";
	}

}
