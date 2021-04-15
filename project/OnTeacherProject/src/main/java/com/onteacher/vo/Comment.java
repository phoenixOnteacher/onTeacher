package com.onteacher.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("comment")
public class Comment {
	
	private int id; //PK
	private String content;
	private String createdAt; //Date
	private int userId; //FK
	private int articleId; //FK
	
	
}
