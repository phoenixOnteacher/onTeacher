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
	
	// ���� ����
	@RequestMapping(value="/{course_id}/homework", method=RequestMethod.GET)
	public String homeworkForm(Model model, @PathVariable String course_id) {
		// request user�� �������� �´��� Ȯ�� �� �� �ѱ�� �ڵ� �߰�
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

	// �л� �ı� �ۼ�
	@RequestMapping(value="/{course_id}/review/{student_id}", method=RequestMethod.GET)
	public String reviewForm(Model model, @PathVariable String course_id, @PathVariable String student_id) {
		// request user�� ���������� Ȯ���ϰ� ������ �̵��ϴ� ���� �߰�
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
	
	// ���� ����
	@RequestMapping(value="/{course_id}/start", method=RequestMethod.POST)
	public String startCourse(Model model, @PathVariable String course_id) {
		try {
			int c_id = Integer.parseInt(course_id);
			courseManageService.startCourse(c_id);
//			model.addAttribute("page", ""); // ���� ���� �޴� ������
		} catch (Exception e) {
			e.printStackTrace();
//			model.addAttribute("page", "index");
		}
		model.addAttribute("page", "index");
		return "template";
	}

	// ���� ����
	// ��޷� �� �����ָ� GET �ʿ� ���� ��
	@RequestMapping(value="/{course_id}/extend", method=RequestMethod.POST)
	public String extendCourse(@RequestParam(value="extendDate", required=true) String extendDate, Model model, @PathVariable String course_id) {
		try {
			int c_id = Integer.parseInt(course_id);
			courseManageService.extendCourse(c_id, extendDate);
//			model.addAttribute("page", ""); // ���� �� ������
		} catch (Exception e) {
			e.printStackTrace();
//			model.addAttribute("page", "index");
		}
		model.addAttribute("page", "index");
		return "template";
	}
	
	// ���� ���
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
//			model.addAttribute("page", ""); // ���� ���� �޴� ������
		} catch (Exception e) {
			e.printStackTrace();
//			model.addAttribute("page", "index");
		}
		model.addAttribute("page", "index");
		return "template";
	}
}
