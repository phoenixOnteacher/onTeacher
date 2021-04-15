package com.onteacher.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("student")
public class Student {
	
	private int id; //PK
	private String email;
	private String name;
	private String password;
	private String phoneNumber;
	private String grade;
	private String address;
	private String birthday; //Date
	private String gender;
	private String profileImg;
	private String introduction;
	
	
}
