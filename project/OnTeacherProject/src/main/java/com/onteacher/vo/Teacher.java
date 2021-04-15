package com.onteacher.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("teacher")
public class Teacher {
	
	private int id; //PK
	private String email;
	private String name;
	private String password;
	private String phoneNumber;
	private String address;
	private String birthday; //Date
	private String gender;
	private String profileImg;
	private String introduction;
	private boolean active;
	private String fileName;
	private String description;
	private String status;
	private String message;
	
	
	
}
