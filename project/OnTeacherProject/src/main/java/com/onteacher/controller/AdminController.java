package com.onteacher.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onteacher.service.AdminService;
import com.onteacher.service.MyCourseService;
import com.onteacher.service.StudentService;
import com.onteacher.service.TeacherService;
import com.onteacher.vo.Teacher;

@Controller("AdminController")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	TeacherService teacherService;
	
	@RequestMapping(value="/cert", method=RequestMethod.GET) // 테스트 호출 주소 : localhost:8090/admin/cert
	public ModelAndView certMain(Model model) {
		ModelAndView modelAndView= new ModelAndView();
		try {
			System.out.println("controller");
			List<Teacher> tea = teacherService.certConfirm(); 
			System.out.println(tea.get(0).getId());
			modelAndView.addObject("tea", tea);
			modelAndView.addObject("page", "admin/boardList");
		} catch(Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("template");
		return modelAndView;
	}
	
}