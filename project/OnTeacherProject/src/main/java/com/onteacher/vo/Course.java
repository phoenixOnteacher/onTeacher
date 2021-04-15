package com.onteacher.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("course")
public class Course {
	
	private int id; //PK
	private String target;
	private boolean isOneday;
	private String startDate; //Date
	private String endDate; //Date
	private String studyDay;
	private String studyTime;
	private boolean isOnline;
	private String location;
	private boolean isGroup;
	private int minStudent;
	private int maxStudent;
	private String title;
	private String curriculum;
	private String curriculumFile;
	private String status;
	private int teacherId; //FK
	private int highCategoryId; //FK
	private int lowCategoryId; //FK
		
	
}