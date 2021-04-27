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
import org.springframework.web.servlet.ModelAndView;

import com.onteacher.service.CourseManageService;
import com.onteacher.service.CourseService;
import com.onteacher.service.TeacherService;
import com.onteacher.vo.Course;
import com.onteacher.vo.Homework;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.StudentReview;
import com.onteacher.vo.Teacher;

@Controller
@RequestMapping
public class TeacherController {

	@Autowired
	CourseManageService courseManageService;

	@Autowired
	TeacherService teacherService;
	
	@Autowired
	CourseService courseService;
	
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
			modelAndView.addObject("page", "login_form");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err", "회원가입 오류");
			modelAndView.addObject("page", "err");
		}
		modelAndView.setViewName("template");
		return modelAndView;
	}
	
	@RequestMapping(value="/course-manage", method=RequestMethod.GET)
	public String courseManage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
//		int teacherId = Integer.parseInt((String) session.getAttribute("id"));
		int teacherId = 1;
		// 由ъ뒪�듃 遺덈윭�삤湲�
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
	
	// �듅�젙 �닔�뾽 愿�由�
	@RequestMapping(value="/course-manage/{course_id}", method=RequestMethod.GET)
	public String courseDetail(HttpServletRequest request, Model model, @PathVariable String course_id) {
		HttpSession session = request.getSession();
//		int teacherId = Integer.parseInt((String) session.getAttribute("id"));
		int teacherId = 1;
		int courseId = Integer.parseInt(course_id);
		// 由ъ뒪�듃 遺덈윭�삤湲�
		try {
			model.addAttribute("course", courseService.queryCourseById(courseId));
			model.addAttribute("students", courseManageService.queryMatchingStudentList(courseId));
			model.addAttribute("homeworks", courseService.queryHomeworkList(courseId));
			model.addAttribute("page", "teacher/courseManageDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page","index");
		}
		return "template";
	}
	
	// 怨쇱젣 �궡湲�
	@RequestMapping(value="/course-manage/{course_id}/homework", method=RequestMethod.GET)
	public String homeworkForm(HttpServletRequest request, Model model, @PathVariable String course_id) {
		HttpSession session = request.getSession();
//		int teacherId = Integer.parseInt((String) session.getAttribute("id"));
		int teacherId = 1;
		int courseId = Integer.parseInt(course_id);
		try {
			Course course = courseService.queryCourseById(courseId);
			if (course.getTeacherId() == teacherId) {
				model.addAttribute("course", course);
				model.addAttribute("page", "teacher/homeworkForm");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}
	
	@RequestMapping(value="/course-manage/{course_id}/homework", method=RequestMethod.POST)
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

	// �븰�깮 �썑湲� �옉�꽦
	@RequestMapping(value="/{course_id}/review/{student_id}", method=RequestMethod.GET)
	public String reviewForm(Model model, @PathVariable String course_id, @PathVariable String student_id) {
		// request user媛� �꽑�깮�떂�씤吏� �솗�씤�븯怨� �뤌�쑝濡� �씠�룞�븯�뒗 濡쒖쭅 異붽�
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
	
	// �닔�뾽 �떆�옉
	@RequestMapping(value="/{course_id}/start", method=RequestMethod.POST)
	public String startCourse(Model model, @PathVariable String course_id) {
		try {
			int c_id = Integer.parseInt(course_id);
			courseManageService.startCourse(c_id);
//			model.addAttribute("page", ""); // �닔�뾽 愿�由� 硫붾돱 �럹�씠吏�
		} catch (Exception e) {
			e.printStackTrace();
//			model.addAttribute("page", "index");
		}
		model.addAttribute("page", "index");
		return "template";
	}

	// �닔�뾽 �뿰�옣
	@RequestMapping(value="/{course_id}/extend", method=RequestMethod.POST)
	public String extendCourse(@RequestParam(value="extendDate", required=true) String extendDate, Model model, @PathVariable String course_id) {
		try {
			int c_id = Integer.parseInt(course_id);
			courseManageService.extendCourse(c_id, extendDate);
//			model.addAttribute("page", ""); // �닔�뾽 �긽�꽭 �럹�씠吏�
		} catch (Exception e) {
			e.printStackTrace();
//			model.addAttribute("page", "index");
		}
		model.addAttribute("page", "index");
		return "template";
	}
	
	// �닔�뾽 痍⑥냼
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Registering course start */
	@RequestMapping(value = "/courseregister.do", method = RequestMethod.GET)
	public String courseregister(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			model.addAttribute("highCategory", courseManageService.getHighCategory());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("page", "teacher/course_register");
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
			String path = multi.getServletContext().getRealPath("/courseupload/"); // �뙆�씪 ���옣 寃쎈줈
			File dir = new File(path);    // 吏��젙�맂 directory媛� �뾾�쓣 �븣 directory 留뚮뱾�뼱二쇨린
			if(!dir.isDirectory()) {
				dir.mkdir();
			}
			String origFileName = origFile.getOriginalFilename(); // �뙆�씪 �씠由� ���옣
			String saveFile = path + origFileName; // �뙆�씪 ���옣 寃쎈줈 + �뙆�씪 �씠由� saveFile 蹂��닔�뿉 ���옣
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
		model.addAttribute("page", "teacher/course_register");
		return "template";
	}
}
