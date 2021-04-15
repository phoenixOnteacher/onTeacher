package com.onteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.onteacher.service.CourseService;
import com.onteacher.service.MyCourseService;
import com.onteacher.service.StudentService;

@Controller
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
}
