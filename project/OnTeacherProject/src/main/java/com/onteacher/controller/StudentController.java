package com.onteacher.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.onteacher.service.CourseService;
import com.onteacher.service.MyCourseService;
import com.onteacher.service.StudentService;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.Student;
import com.onteacher.vo.Teacher;
import com.onteacher.vo.Homework;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.Student;

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
		Student student = studentService.queryStudentByEmail(email); //여기서 못가져와
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
	
	@RequestMapping(value="/courseStudyingDetail", method=RequestMethod.GET)
	public ModelAndView courseStudyingDetail(@RequestParam(value = "courseId",required = true)int courseId,
									HttpServletRequest request) {
		ModelAndView modelAndView= new ModelAndView();
		try {
			Course course = courseService.queryCourseById(courseId);
			int highCategoryId = course.getHighCategoryId();
			int lowCategoryId = course.getLowCategoryId();
			int teacherId = course.getTeacherId();
			HighCategory highCategory = courseService.queryHighCategoryById(highCategoryId);
			LowCategory lowCategory = courseService.queryLowCategoryById(lowCategoryId);
			Teacher teacher = courseService.queryTeacherById(teacherId);
			modelAndView.addObject("course", course);
			modelAndView.addObject("highCategory", highCategory);
			modelAndView.addObject("lowCategory", lowCategory);
			modelAndView.addObject("teacher", teacher);
			modelAndView.addObject("page","student/courseStudyingDetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("template");
		return modelAndView;
	}

			
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String stjoin(Model model) {
		model.addAttribute("page", "stjoin_form");
		return "template";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ModelAndView stjoin(@ModelAttribute Student student, MultipartHttpServletRequest multi) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			MultipartFile origFile = student.getFile();

			if (!origFile.isEmpty() && origFile.getContentType().split("/")[0].equals("image")) { // 이미지 파일인지 체크
				String path = multi.getServletContext().getRealPath("/stprofileupload/"); // 파일 저장 경로
				File dir = new File(path); // 지정된 directory가 없을 때 directory 만들어주기
				if (!dir.isDirectory()) {
					dir.mkdir();
				}
				String origFileName = origFile.getOriginalFilename(); // 파일 이름 저장
				String saveFile = path + origFileName; // 파일 저장 경로 + 파일 이름 saveFile 변수에 저장
				try {
					origFile.transferTo(new File(saveFile));
					student.setProfileImg(origFileName);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			studentService.stjoin(student);
			modelAndView.addObject("page", "login_form");
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("template");
		return modelAndView;
	}
	

	@RequestMapping(value = "/{course_id}/homeworkanswer", method = RequestMethod.POST)
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
