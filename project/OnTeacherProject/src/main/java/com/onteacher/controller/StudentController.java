package com.onteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.onteacher.service.MyCourseService;
import com.onteacher.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	MyCourseService myCourseService;

}
