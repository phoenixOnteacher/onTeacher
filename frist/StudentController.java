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

import com.onteacher.service.MyCourseService;
import com.onteacher.service.StudentService;
import com.onteacher.vo.Student;

@Controller
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	MyCourseService myCourseService;
	
	@RequestMapping(value = "/stjoin", method = RequestMethod.GET)
	public String stjoin(Model model) {
		model.addAttribute("page", "stjoin_form");
		return "template";
	}
	@RequestMapping(value = "/stjoin", method = RequestMethod.POST)
	public ModelAndView stjoin(@ModelAttribute Student student) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			studentService.stjoin(student);
			modelAndView.addObject("page","stlogin_form");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err", "회원가입 오류");
			modelAndView.addObject("page", "err");
		}
		modelAndView.setViewName("template");
		return modelAndView;
	}
	@RequestMapping(value = "/stlogin", method = RequestMethod.GET)
	public String stlogin(HttpServletRequest request, Model model) {
		model.addAttribute("page","thlogin_form");
		return "template";
	}
	@RequestMapping(value = "/stlogin", method = RequestMethod.POST)
	public ModelAndView stlogin(@RequestParam(value = "email", required = true)String email,
			@RequestParam(value = "password", required = true)String password, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			boolean success = studentService.login(email, password);
			if(!success) throw new Exception("비밀번호 오류");
			request.getSession().setAttribute("email", email);
			modelAndView.addObject("page","main"); //어느 폼으로갈지 정하기
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("page","stlogin_form");
		}
		modelAndView.setViewName("template");
		return modelAndView;
	}
	@RequestMapping(value = "/stlogout", method = RequestMethod.GET)
	public String thlogout(HttpServletRequest request, Model model) {
		request.getSession().removeAttribute("email");
		model.addAttribute("page","stlogin_form");
		return "template";
	}
}
