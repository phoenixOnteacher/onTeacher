package com.onteacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onteacher.service.UserService;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;

@Controller("commonController")
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	UserService userService;
	@RequestMapping(value="/searchCourse", method = RequestMethod.GET) 
	public ModelAndView searchCourseDefault() {
		ModelAndView modelAndView = new ModelAndView();
		List<HighCategory> highCategory = userService.highcategoryList();
		modelAndView.addObject("highCategory", highCategory);
		modelAndView.addObject("page","search");
		modelAndView.setViewName("template");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/searchCourse", method = RequestMethod.POST)
	public ModelAndView searchCourseDefault(
			@RequestParam(value="highcategory") int highcategory_id,
			@RequestParam(value="lowcategory") int lowcategory_id,
			@RequestParam(value="target") String target,
			@RequestParam(value="isonline") char isonline) {
		
		
		Course course = new Course();
		course.setHighCategoryId(highcategory_id);
		course.setLowCategoryId(lowcategory_id);
		course.setTarget(target);
		course.setIsOnline(isonline);
		
		ModelAndView modelAndView = new ModelAndView();
		List<Course> courses = userService.queryCourseForSearch(course);
		List<HighCategory> highCategory = userService.highcategoryList();
		modelAndView.addObject("highCategory", highCategory);
		modelAndView.addObject("highcategory_id", highcategory_id);
		modelAndView.addObject("lowcategory_id", lowcategory_id);
		modelAndView.addObject("target", target);
		modelAndView.addObject("isonline", isonline);		
		modelAndView.addObject("courses",courses);
		modelAndView.addObject("page","search");
		modelAndView.setViewName("template");
		return modelAndView;
	}


}
