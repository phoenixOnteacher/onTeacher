package com.onteacher.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("homeworkAnswer")
public class HomeworkAnswer {
	
	private String fileName;
	private String createdAt; //Date
	private int studentId; //FK
	private int homeworkId; //FK
	
	
}
