package com.onteacher.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("lowCategory")
public class LowCategory {
	
	private int id; //PK
	private String name;
	private String highCategoryId; //FK

	
	
}
