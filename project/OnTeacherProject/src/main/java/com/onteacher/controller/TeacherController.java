package com.onteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.onteacher.service.CourseManageService;
import com.onteacher.service.TeacherService;

@Controller
public class TeacherController {
	
	@Autowired
	CourseManageService courseManageService;
	
	@Autowired
	TeacherService teacherService;
	
	
}
