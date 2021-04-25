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

import com.onteacher.service.AdminService;
import com.onteacher.vo.Admin;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@RequestMapping(value = "/adjoin", method = RequestMethod.GET)
	public String adjoin(Model model) {
		model.addAttribute("page", "adjoin_form");
		return "template";
	}

	@RequestMapping(value = "/adjoin", method = RequestMethod.POST)
	public ModelAndView adjoin(@ModelAttribute Admin admin) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			adminService.adjoin(admin);
			modelAndView.addObject("page", "adlogin_form");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err", "회원가입 오류");
			modelAndView.addObject("page", "err");
		}
		modelAndView.setViewName("template");
		return modelAndView;
	}

	@RequestMapping(value = "/adlogin", method = RequestMethod.GET)
	public String adlogin(HttpServletRequest request, Model model) {
		model.addAttribute("page", "adlogin_form");
		return "template";
	}

	@RequestMapping(value = "/adlogin", method = RequestMethod.POST)
	public ModelAndView adlogin(@RequestParam(value = "email", required = true) String email,

			@RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			boolean success = adminService.login(email, password);
			if (!success)
				throw new Exception("비밀번호 오류");
			request.getSession().setAttribute("email", email);
			modelAndView.addObject("page", "main");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("page", "adlogin_form");
		}
		modelAndView.setViewName("template");
		return modelAndView;
	}

	@RequestMapping(value = "/adlogout", method = RequestMethod.GET)
	public String adlogout(HttpServletRequest request, Model model) {
		request.getSession().removeAttribute("email");
		model.addAttribute("page", "adlogin_form");
		return "template";
	}

}
