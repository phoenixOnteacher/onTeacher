package com.onteacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.onteacher.service.UserService;
import com.onteacher.vo.Course;

@Controller("commonController")
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/searchCourse", method = RequestMethod.GET)
	public ModelAndView searchCourseDefault() {
		
		
		ModelAndView modelAndView = new ModelAndView();
		List<Course> courses = userService.queryCourseForSearch();
		modelAndView.addObject("courses",courses);
		modelAndView.addObject("page","search");
		modelAndView.setViewName("template");
		return modelAndView;
	}


}
