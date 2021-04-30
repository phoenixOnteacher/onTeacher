package com.onteacher.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onteacher.service.CourseService;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.Homework;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.Teacher;
import com.onteacher.service.UserService;
import com.onteacher.vo.Homework;
import com.onteacher.vo.User;


@Controller
@RequestMapping
public class CommonController {

	@Autowired
	CourseService courseService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/main")
	public String main(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("courses", courseService.selectCourseForIndex());
		model.addAttribute("page", "index");
		return "template";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		model.addAttribute("page", "login_form");
		return "template";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			int id = userService.login(email,password);
			if (id==0) {
				modelAndView.addObject("msg", false);
				throw new Exception("로그인 실패");
			}
			request.getSession().setAttribute("id", id);
			
			modelAndView.setViewName("redirect:/main");;
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("page", "login_form");
			modelAndView.setViewName("template");
		} 
		return modelAndView;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String thlogout(HttpServletRequest request, Model model) {
		request.getSession().removeAttribute("id");
		model.addAttribute("page", "login_form");
		return "template";
	}
	
	// homework detail page로 이동
	@RequestMapping(value="/homework/{homework_id}", method=RequestMethod.GET)
	public String homeworkDetail(HttpServletRequest request, Model model, @PathVariable String homework_id) {
		HttpSession session = request.getSession();
//		int user_id = Integer.parseInt((String) session.getAttribute("id"));
		int user_id = 3;
		int homeworkId = Integer.parseInt(homework_id);
		try {
			Homework hw = courseService.queryHomework(homeworkId);
			int courseId = hw.getCourseId();
			// model.addAttribute("homeworkAnswerList",);
			model.addAttribute("user_id", user_id);
			model.addAttribute("course", courseService.queryCourseById(courseId));
			model.addAttribute("homework", hw);
			model.addAttribute("page", "common/homeworkDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}
	

}
