package com.onteacher.vo;

import org.springframework.stereotype.Component;

@Component("lowCategory")
public class LowCategory {
	
	private int id; //PK
	private String name;
	private String highCategoryId; //FK
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHighCategoryId() {
		return highCategoryId;
	}
	public void setHighCategoryId(String highCategoryId) {
		this.highCategoryId = highCategoryId;
	}
	
}
