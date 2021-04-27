package com.onteacher.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onteacher.service.CourseService;
import com.onteacher.vo.Homework;

@Controller
public class CommonController {

	@Autowired
	CourseService courseService;
	
	// homework detail page로 이동
	@RequestMapping(value="/homework/{homework_id}", method=RequestMethod.GET)
	public String courseManage(HttpServletRequest request, Model model, @PathVariable String homework_id) {
		HttpSession session = request.getSession();
//		int user_id = Integer.parseInt((String) session.getAttribute("id"));
		int user_id = 3; 
		int homeworkId = Integer.parseInt(homework_id);
		try {
			Homework hw = courseService.queryHomework(homeworkId);
			// model.addAttribute("homeworkAnswerList",);
			model.addAttribute("homework", hw);
			model.addAttribute("page", "common/homeworkDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}

}
