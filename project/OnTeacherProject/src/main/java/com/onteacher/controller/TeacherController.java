package com.onteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onteacher.service.CourseManageService;
import com.onteacher.service.TeacherService;
import com.onteacher.vo.Homework;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	CourseManageService courseManageService;
	
	@Autowired
	TeacherService teacherService;
	
	@RequestMapping(value="/{course_id}/homework", method=RequestMethod.GET)
	public String homeworkForm(@ModelAttribute Homework hw, Model model, @PathVariable String course_id) {
		model.addAttribute("course_id", Integer.parseInt(course_id));
		model.addAttribute("page", "homeworkForm");
		return "template";
	}
	
	@RequestMapping(value="/{course_id}/homework", method=RequestMethod.POST)
	public String homework(@ModelAttribute Homework hw, Model model, @PathVariable String course_id) {
		hw.setCourseId(Integer.parseInt(course_id));
		try {
			courseManageService.setHomework(hw);
			model.addAttribute("homework", hw);
			model.addAttribute("page", "homeworkDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}
}
