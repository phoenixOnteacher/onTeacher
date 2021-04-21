package com.onteacher.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onteacher.service.CourseManageService;
import com.onteacher.service.TeacherService;
import com.onteacher.vo.Course;
import com.onteacher.vo.Homework;
import com.onteacher.vo.StudentReview;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	CourseManageService courseManageService;
	
	@Autowired
	TeacherService teacherService;
	
	// 과제 내기
	@RequestMapping(value="/{course_id}/homework", method=RequestMethod.GET)
	public String homeworkForm(Model model, @PathVariable String course_id) {
		// request user가 선생님이 맞는지 확인 후 폼 넘기는 코드 추가
		model.addAttribute("course_id", Integer.parseInt(course_id));
		model.addAttribute("page", "teacher/homeworkForm");
		return "template";
	}
	@RequestMapping(value="/{course_id}/homework", method=RequestMethod.POST)
	public String homework(@ModelAttribute Homework hw, Model model, @PathVariable String course_id) {
		hw.setCourseId(Integer.parseInt(course_id));
		try {
			courseManageService.setHomework(hw);
			model.addAttribute("homework", hw);
			model.addAttribute("page", "teacher/homeworkDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}

	// 학생 후기 작성
	@RequestMapping(value="/{course_id}/review/{student_id}", method=RequestMethod.GET)
	public String reviewForm(Model model, @PathVariable String course_id, @PathVariable String student_id) {
		// request user가 선생님인지 확인하고 폼으로 이동하는 로직 추가
		model.addAttribute("page", "teacher/studentReviewForm");
		return "template";
	}
	@RequestMapping(value="/{course_id}/review/{student_id}", method=RequestMethod.POST)
	public String writeReview(HttpServletRequest request, @ModelAttribute StudentReview sr, Model model, @PathVariable String course_id, @PathVariable String student_id) {
		HttpSession session = request.getSession();
//		int teacher_id = Integer.parseInt((String) session.getAttribute("id"));
		int teacher_id = 1;
		sr.setTeacherId(teacher_id);
		sr.setCourseId(Integer.parseInt(course_id));
		sr.setStudentId(Integer.parseInt(student_id));
		try {
			courseManageService.writeStudentReview(sr);
			model.addAttribute("studentReview", sr);
			model.addAttribute("page", "teacher/studentReviewDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}
	
	// 수업 시작
	@RequestMapping(value="/{course_id}/start", method=RequestMethod.POST)
	public String startCourse(Model model, @PathVariable String course_id) {
		try {
			int c_id = Integer.parseInt(course_id);
			courseManageService.startCourse(c_id);
//			model.addAttribute("page", ""); // 수업 관리 메뉴 페이지
		} catch (Exception e) {
			e.printStackTrace();
//			model.addAttribute("page", "index");
		}
		model.addAttribute("page", "index");
		return "template";
	}

	// 수업 연장
	// 모달로 폼 보여주면 GET 필요 없을 듯
	@RequestMapping(value="/{course_id}/extend", method=RequestMethod.POST)
	public String extendCourse(@RequestParam(value="extendDate", required=true) String extendDate, Model model, @PathVariable String course_id) {
		try {
			int c_id = Integer.parseInt(course_id);
			courseManageService.extendCourse(c_id, extendDate);
//			model.addAttribute("page", ""); // 수업 상세 페이지
		} catch (Exception e) {
			e.printStackTrace();
//			model.addAttribute("page", "index");
		}
		model.addAttribute("page", "index");
		return "template";
	}
	
	// 수업 취소
	@RequestMapping(value="/{course_id}", method=RequestMethod.DELETE)
	public String cancelCourse(HttpServletRequest request, Model model, @PathVariable String course_id) {
		HttpSession session = request.getSession();
//		int teacher_id = Integer.parseInt((String) session.getAttribute("id"));
		int teacher_id = 1;
		try {
			int c_id = Integer.parseInt(course_id);
			Course course = new Course();
			course.setId(c_id);
			course.setTeacherId(teacher_id);
			courseManageService.cancelCourse(course);
//			model.addAttribute("page", ""); // 수업 관리 메뉴 페이지
		} catch (Exception e) {
			e.printStackTrace();
//			model.addAttribute("page", "index");
		}
		model.addAttribute("page", "index");
		return "template";
	}
}
