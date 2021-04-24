package com.onteacher.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.onteacher.service.CourseManageService;
import com.onteacher.service.CourseService;
import com.onteacher.service.TeacherService;
import com.onteacher.vo.Course;
import com.onteacher.vo.Homework;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.StudentReview;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	CourseManageService courseManageService;

	@Autowired
	TeacherService teacherService;
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping(value="/course-manage", method=RequestMethod.GET)
	public String courseManage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
//		int teacherId = Integer.parseInt((String) session.getAttribute("id"));
		int teacherId = 1;
		// 리스트 불러오기
		try {
			model.addAttribute("studyingList", courseManageService.queryStudyingCourseList(teacherId));
			model.addAttribute("matchingList", courseManageService.queryMatchingCourseList(teacherId));
			model.addAttribute("matchedList", courseManageService.queryMatchedCourseList(teacherId));
			model.addAttribute("endList", courseManageService.queryEndCourseList(teacherId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("page", "teacher/courseManage");
		return "template";
	}
	
	// 특정 수업 관리
	@RequestMapping(value="/course-manage/{course_id}", method=RequestMethod.GET)
	public String courseDetail(HttpServletRequest request, Model model, @PathVariable String course_id) {
		HttpSession session = request.getSession();
//		int teacherId = Integer.parseInt((String) session.getAttribute("id"));
		int teacherId = 1;
		int courseId = Integer.parseInt(course_id);
		// 리스트 불러오기
		try {
			model.addAttribute("course", courseService.queryCourseById(courseId));
			model.addAttribute("students", courseManageService.queryMatchingStudentList(courseId));
			model.addAttribute("homeworks", courseManageService.queryHomeworkList(courseId));
			model.addAttribute("page", "teacher/courseManageDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page","index");
		}
		return "template";
	}
	
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
	@ResponseBody
	@RequestMapping(value="/{course_id}", method=RequestMethod.DELETE)
	public void cancelCourse(HttpServletRequest request, Model model, @PathVariable String course_id) {
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
//		model.addAttribute("page", "index");
//		return "template";
	}

	/* Registering course start */
	@RequestMapping(value = "/courseregister.do", method = RequestMethod.GET)
	public String courseregister(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			model.addAttribute("highCategory", courseManageService.getHighCategory());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("page", "course_register");
		return "template";
	}

	@ResponseBody
	@RequestMapping(value = "/highcategory", method = RequestMethod.GET)
	public void subcategory(HttpServletResponse res,
			@RequestParam(value = "high_category_id", required = true) Integer high_category_id) {
		res.setCharacterEncoding("UTF-8");
		List<LowCategory> lowcategory;
		try {
			lowcategory = courseManageService.getLowCategory(high_category_id);

			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < lowcategory.size(); i++) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", lowcategory.get(i).getId());
				jsonObj.put("name", lowcategory.get(i).getName());
				jsonArray.put(jsonObj);
			}

			System.out.println(jsonArray.toString());
			PrintWriter pw = res.getWriter();
			pw.print(jsonArray.toString());
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* toss data to database including attachments */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@ModelAttribute Course course, MultipartHttpServletRequest multi, Model model) throws Exception {

		/*
		 * String id = request.getParameter("id"); HttpSession session =
		 * request.getSession();
		 */

		MultipartFile origFile = course.getFile();
		System.out.println(origFile);
		if (origFile.isEmpty() == false) {
			String path = multi.getServletContext().getRealPath("/courseupload/"); // 파일 저장 경로
			File dir = new File(path);    // 지정된 directory가 없을 때 directory 만들어주기
			if(!dir.isDirectory()) {
				dir.mkdir();
			}
			String origFileName = origFile.getOriginalFilename(); // 파일 이름 저장
			String saveFile = path + origFileName; // 파일 저장 경로 + 파일 이름 saveFile 변수에 저장
			System.out.println(saveFile);
			try {
				origFile.transferTo(new File(saveFile));
				course.setCurriculumFile(origFileName);
				System.out.println(course.getCurriculumFile());
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		courseManageService.registerCourse(course);
		model.addAttribute("page", "course_register");
		return "template";
	}
}
