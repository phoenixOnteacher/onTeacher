package com.onteacher.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("article")
public class Article {
	
	private int id; //PK
	private String title;
	private String content;
	private String file;
	private String createdAt; //Date
	private int hit;
	private int userId; //FK

	
	
}
