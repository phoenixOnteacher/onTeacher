package com.onteacher.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("homework")
public class Homework {
	
	private int id; //PK
	private String title;
	private String content;
	private String fileName;
	private String deadLine; //Date
	private int courseId; //FK
	
	
}
