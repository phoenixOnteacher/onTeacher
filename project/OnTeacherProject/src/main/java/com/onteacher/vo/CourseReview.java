package com.onteacher.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("courseReview")
public class CourseReview {
	
	private int id; //PK
	private String content;
	private String createdAt; //Date
	private int courseId; //FK
	private int teacherId; //FK
	private int studentId; //FK

	
	
}
