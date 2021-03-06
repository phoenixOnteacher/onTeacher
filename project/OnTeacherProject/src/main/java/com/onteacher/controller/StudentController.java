package com.onteacher.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.onteacher.prop.UploadPath;
import com.onteacher.service.CourseService;
import com.onteacher.service.MatchingService;
import com.onteacher.service.MyCourseService;
import com.onteacher.service.StudentService;
import com.onteacher.vo.Course;
import com.onteacher.vo.CourseReview;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.HomeworkAnswer;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.Matching;
import com.onteacher.vo.Student;
import com.onteacher.vo.Teacher;

@Controller("studentController")
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@Autowired
	MyCourseService myCourseService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	MatchingService matchingService;
	
	@Autowired
	private UploadPath uploadPath;
	
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

			if (!origFile.isEmpty() && origFile.getContentType().split("/")[0].equals("image")) { // ????????? ???????????? ??????
				String path = uploadPath.getStprofilePath(); // ?????? ?????? ??????
				if(!uploadPath.isAws()) {    //aws??? ?????? ??? ?????? ??????
					path = multi.getServletContext().getRealPath(path);
				}
				File dir = new File(path); // ????????? directory??? ?????? ??? directory ???????????????
				if (!dir.isDirectory()) {
					dir.mkdir();
				}
				String origFileName = origFile.getOriginalFilename(); // ?????? ?????? ??????
				String saveFile = path + origFileName; // ?????? ?????? ?????? + ?????? ?????? saveFile ????????? ??????
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
	
	/* ?????? ?????? ????????? - ?????????, ?????????, ????????? ?????? ?????? ?????? */
	@RequestMapping(value="/course-manage", method=RequestMethod.GET)
	public String courseManage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		List<Course> matchingList = myCourseService.queryMatchingcourseListByStudentId(userId); // matching
 		List<Course> matchedList = myCourseService.queryMatchedcourseListByStudentId(userId); // matched ?????????
		List<Course> studyingList = myCourseService.queryStudyingcourseListByStudentId(userId);
		List<Course> endList = myCourseService.queryEndcourseListByStudentId(userId);

		model.addAttribute("matchingList", matchingList);
		model.addAttribute("matchedList", matchedList);
		model.addAttribute("studyingList", studyingList);
		model.addAttribute("endList", endList);
		model.addAttribute("page", "student/myCourseManage");
		return "template";
	}

	@GetMapping("/fileview/thprofile/{filename}")
	public void fileview(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response) {
		String path = uploadPath.getThprofilePath();
		if(!uploadPath.isAws()) {
			path= request.getServletContext().getRealPath(path);
		}	
		File file = new File(path + filename);
		String sfilename = null;
		FileInputStream fis = null;
		try {
			// ???????????? ????????? ?????? utf-8??????
			if (request.getHeader("User-Agent").indexOf("MSIE") > -1) {
				sfilename = URLEncoder.encode(file.getName(), "utf-8");
			} else {
				sfilename = new String(file.getName().getBytes("utf-8"), "ISO-8859-1");
			}

			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + sfilename + "\";");
			OutputStream out = response.getOutputStream();
			// ?????? ?????? ??? ?????????
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
			out.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	/* ?????? ?????? ????????? ????????? - ?????? ?????? ??????????????? ?????? ????????? ?????? ?????? ??? */
	@RequestMapping(value="/course-manage/{course_id}", method=RequestMethod.GET)
	public String courseDetail(HttpServletRequest request, Model model, @PathVariable String course_id) {
		int courseId = Integer.parseInt(course_id);
		Course course = null;
		try {
			course = courseService.queryCourseById(courseId);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int highCategoryId = course.getHighCategoryId();
		int lowCategoryId = course.getLowCategoryId();
		HighCategory highCategory = courseService.queryHighCategoryById(highCategoryId);
		LowCategory lowCategory = courseService.queryLowCategoryById(lowCategoryId);
		try {
			// ??????????????? ???????????? ?????? ????????????
			model.addAttribute("course", courseService.queryCourseById(courseId));
			model.addAttribute("teacher", myCourseService.queryMatchingTeacher(courseId));
			model.addAttribute("homeworks", courseService.queryHomeworkList(courseId));
			model.addAttribute("highCategory", highCategory);
			model.addAttribute("lowCategory", lowCategory);
			model.addAttribute("page", "student/myCourseManageDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}
	
	/* ?????? ?????? - (???????????? ??????) */
	@ResponseBody
	@RequestMapping(value="/applyCancel", method=RequestMethod.DELETE)
	public void applyCancel(@RequestParam(value = "courseId",required = true)int courseId, HttpServletRequest request) {
		//1. ???????????? ????????????. courseId??? ?????????????????????.
		HttpSession session = request.getSession();
		int studentId = (int) session.getAttribute("id");
		//2. db?????? delete??? ??????
		myCourseService.cancelMatching(studentId,courseId);
	}

	/* ?????? ?????? - (????????? ??????) */
	@ResponseBody
	@RequestMapping(value="/{course_id}/review", method = RequestMethod.POST)
	public void writeReview(HttpServletRequest request, @RequestBody Map<String, String> reqData, Model model,
			@PathVariable String course_id) {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		Course course;
		try {
			course = courseService.queryCourseById(Integer.parseInt(course_id));
			CourseReview cr = new CourseReview();
			cr.setStudentId(userId);
			cr.setCourseId(Integer.parseInt(course_id));
			cr.setTeacherId(course.getTeacherId());
			cr.setContent(reqData.get("content"));
			myCourseService.writeCourseReview(cr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* ?????? ??????. - (???????????? ??????) ?????? ????????? commonController??? */
	@RequestMapping(value = "/{homework_id}/homeworkanswer", method = RequestMethod.POST)
	public String homeworkAnswer(@ModelAttribute HomeworkAnswer ha, HttpServletRequest request, Model model, @PathVariable String homework_id, MultipartHttpServletRequest multi) {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		ha.setStudentId(userId);
		ha.setHomeworkId(Integer.parseInt(homework_id));
		try {
			MultipartFile origFile = ha.getFile();
			String path = uploadPath.getHomeworkanswerPath();
			if(!uploadPath.isAws()) {
				path = request.getServletContext().getRealPath(path); // ?????? ?????? ??????
			}
			File dir = new File(path); // ????????? directory??? ?????? ??? directory ???????????????
			if (!dir.isDirectory()) {
				dir.mkdir();
			}
			String origFileName = origFile.getOriginalFilename(); // ?????? ?????? ??????
			String saveFile = path + origFileName; // ?????? ?????? ?????? + ?????? ?????? saveFile ????????? ??????
			try {
				origFile.transferTo(new File(saveFile));
				ha.setFilename(origFileName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			myCourseService.createHomeworkAnswer(ha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/homework/"+homework_id;
	}

	@RequestMapping(value="/studentDetail", method=RequestMethod.GET)
	public String studentDetail(@RequestParam(value = "studentId") int studentId, Model model,
			HttpServletRequest request) {
		try {
			Student student = studentService.studentInfo(studentId);
//			String path = uploadPath.getStprofilePath();
//			student.setProfileImg(path+student.getProfileImg());
			model.addAttribute("student",student);
			model.addAttribute("page","student/studentDetail");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "template";
	}
	
	@RequestMapping(value="/searchCourse/apply", method = RequestMethod.GET)
	public ModelAndView searchCourseDetailApply(@RequestParam(value="courseId", required = true) int courseId) {
		ModelAndView modelAndView= new ModelAndView();
		Course course;
		try {
			course = courseService.queryCourseById(courseId);
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
			modelAndView.addObject("page","common/courseDetailApply");
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("template");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/courseApply", method=RequestMethod.POST)
	public ResponseEntity<String> courseApply(@RequestParam(value = "courseId",required = true)int courseId, HttpServletRequest request) {
		//1. ???????????? ????????????. courseId??? ?????????????????????.
		HttpSession session = request.getSession();
		int studentId = (int) session.getAttribute("id");
		Matching matching = new Matching();
		matching.setStudentId(studentId);
		matching.setCourseId(courseId);
		try {
			matchingService.insertMatching(matching);
			return new ResponseEntity<String>("????????? ?????????????????????.",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("?????? ????????? ???????????????.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
