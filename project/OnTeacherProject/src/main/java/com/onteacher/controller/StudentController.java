package com.onteacher.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onteacher.service.CourseService;
import com.onteacher.service.MyCourseService;
import com.onteacher.service.StudentService;
import com.onteacher.vo.Course;
import com.onteacher.vo.Student;
import com.onteacher.vo.Homework;

//@Controller
@Controller("studentController")
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	MyCourseService myCourseService;
	
	@Autowired
	CourseService courseService;
	
	//test 코드 : 코드 test를 위한 샘플 로그인
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		System.out.println("login_get");
		model.addAttribute("page", "student/login_form");
		return "template";
	}

	//test 코드 : 로그인 이후 메인
	@RequestMapping(value = "/afterlogin", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "email",required = true)String email,
							  @RequestParam(value="password",required = true) String password,
							  HttpServletRequest request) {
		System.out.println("login_post");
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		modelAndView.addObject("page", "index");
		modelAndView.setViewName("template");
		return modelAndView;
	}
	
	//로그인한 email로 student 객체를 가져오고, studentId를 return하는 함수.
	public int getStudentIdBySessionEmail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		Student student = studentService.queryStudentByEmail(email);
		return student.getId();
	}
	
	//대기중인 수업 목록 조회
	@RequestMapping(value="/courseWaitingList", method=RequestMethod.GET)
	public ModelAndView courseWaitingList(HttpServletRequest request) {
		ModelAndView modelAndView= new ModelAndView();
		int studentId = getStudentIdBySessionEmail(request);
		List<Course> courses = courseService.courseWaitingList(studentId);
		modelAndView.addObject("courses", courses);
		modelAndView.addObject("page", "student/courseWaitingList");
		modelAndView.setViewName("template");
		return modelAndView;
	}
	
	//진행중인 수업 목록 조회
	@RequestMapping(value="/courseStudyingList", method=RequestMethod.GET)
	public ModelAndView courseStudyingList(HttpServletRequest request) {
		ModelAndView modelAndView= new ModelAndView();
		int studentId = getStudentIdBySessionEmail(request);
		List<Course> courses = courseService.courseStudyingList(studentId);
		modelAndView.addObject("courses", courses);
		modelAndView.addObject("page", "student/courseStudyingList");
		modelAndView.setViewName("template");
		return modelAndView;
	}
	
	//종료된 수업 목록 조회
	@RequestMapping(value="/courseEndList", method=RequestMethod.GET)
	public ModelAndView courseEndList(HttpServletRequest request) {
		ModelAndView modelAndView= new ModelAndView();
		int studentId = getStudentIdBySessionEmail(request);
		List<Course> courses = courseService.courseEndList(studentId);
		modelAndView.addObject("courses", courses);
		modelAndView.addObject("page", "student/courseEndList");
		modelAndView.setViewName("template");
		return modelAndView;
	}
	
	//대기중인 수업 - 신청 취소
	@RequestMapping(value="/applyCancle", method=RequestMethod.GET)
	public ModelAndView applyCancle(@RequestParam(value = "courseId",required = true)int courseId,
									HttpServletRequest request) {
		ModelAndView modelAndView= new ModelAndView();
		//1. 매개변수 받아오기. courseId는 쿼리스트링으로.
		int studentId = getStudentIdBySessionEmail(request);
		//2. db에서 delete문 실행
		courseService.cancleMatching(studentId,courseId);
		//3. view 정의. 대기중인 수업 목록 조회 화면 그대로.
		List<Course> courses = courseService.courseWaitingList(studentId);
		modelAndView.addObject("courses", courses);
		modelAndView.addObject("page", "student/courseWaitingList");
		modelAndView.setViewName("template");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/{course_id}/homeworkanswer", method=RequestMethod.POST)
	public String homeworkAnswer(@ModelAttribute Homework hw, Model model, @PathVariable String course_id) {
		hw.setCourseId(Integer.parseInt(course_id));
		try {
//			myCourseService.setHomework(hw);
			model.addAttribute("homework", hw);
			model.addAttribute("page", "homeworkDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}
}
