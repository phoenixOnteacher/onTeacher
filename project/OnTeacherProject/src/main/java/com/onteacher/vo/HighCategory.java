package com.onteacher.vo;

import org.springframework.stereotype.Component;

@Component("highCategory")
public class HighCategory {
	
	private int id; //PK
	private String name;
	
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
	
}
