package com.onteacher.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onteacher.service.CourseManageService;
import com.onteacher.service.TeacherService;
import com.onteacher.vo.Teacher;

@Controller
public class TeacherController {

	@Autowired
	CourseManageService courseManageService;

	@Autowired
	TeacherService teacherService;

	@RequestMapping(value = "/thjoin", method = RequestMethod.GET)
	public String thjoin(Model model) {
		model.addAttribute("page", "thjoin_form");
		return "template";
	}
	@RequestMapping(value = "/thjoin", method = RequestMethod.POST)
	public ModelAndView thjoin(@ModelAttribute Teacher teacher) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			teacherService.thjoin(teacher);
			modelAndView.addObject("page", "thlogin_form");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err", "회원가입 오류");
			modelAndView.addObject("page", "err");
		}
		modelAndView.setViewName("template");
		return modelAndView;
	}

	@RequestMapping(value = "/thlogin", method = RequestMethod.GET)
	public String thlogin(HttpServletRequest request, Model model) {
		model.addAttribute("page", "thlogin_form");
		return "template";
	}

	@RequestMapping(value = "/thlogin", method = RequestMethod.POST)
	public ModelAndView thlogin(@RequestParam(value = "email", required = true) String email,

			@RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			boolean success = teacherService.login(email, password);
			if (!success)
				throw new Exception("비밀번호 오류");
			request.getSession().setAttribute("email", email);
			modelAndView.addObject("page", "main");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("page", "thlogin_form");
		}
		modelAndView.setViewName("template");
		return modelAndView;
	}

	@RequestMapping(value = "/thlogout", method = RequestMethod.GET)
	public String thlogout(HttpServletRequest request, Model model) {
		request.getSession().removeAttribute("email");
		model.addAttribute("page", "thlogin_form");
		return "template";
	}

}
