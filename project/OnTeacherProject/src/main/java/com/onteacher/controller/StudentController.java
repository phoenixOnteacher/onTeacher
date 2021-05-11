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

			if (!origFile.isEmpty() && origFile.getContentType().split("/")[0].equals("image")) { // 이미지 파일인지 체크
				String path = uploadPath.getStprofilePath(); // 파일 저장 경로
				if(!uploadPath.isAws()) {    //aws가 아닐 때 경로 지정
					path = multi.getServletContext().getRealPath(path);
				}
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
	
	/* 수업 관리 페이지 - 대기중, 진행중, 종료된 수업 목록 조회 */
	@RequestMapping(value="/course-manage", method=RequestMethod.GET)
	public String courseManage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		List<Course> matchingList = myCourseService.queryMatchingcourseListByStudentId(userId); // matching
 		List<Course> matchedList = myCourseService.queryMatchedcourseListByStudentId(userId); // matched 나눠서
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
			// if(ie){
			// 브라우저 정보에 따라 utf-8변경
			if (request.getHeader("User-Agent").indexOf("MSIE") > -1) {
				sfilename = URLEncoder.encode(file.getName(), "utf-8");
			} else {
				sfilename = new String(file.getName().getBytes("utf-8"), "ISO-8859-1");
			} // end if;

			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + sfilename + "\";");
			OutputStream out = response.getOutputStream();
			// 파일 카피 후 마무리
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
		} // try end;
	}


	
	/* 수업 관리 디테일 페이지 - 수업 관리 페이지에서 특정 수업을 클릭 했을 때 */
	@RequestMapping(value="/course-manage/{course_id}", method=RequestMethod.GET)
	public String courseDetail(HttpServletRequest request, Model model, @PathVariable String course_id) {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		int courseId = Integer.parseInt(course_id);
		Course course = null;
		try {
			course = courseService.queryCourseById(courseId);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int highCategoryId = course.getHighCategoryId();
		int lowCategoryId = course.getLowCategoryId();
		int teacherId = course.getTeacherId();
		HighCategory highCategory = courseService.queryHighCategoryById(highCategoryId);
		LowCategory lowCategory = courseService.queryLowCategoryById(lowCategoryId);
		try {
			// 선생님인지 확인하는 코드 추가하기
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
	
	/* 신청 취소 - (대기중인 수업) */
	@ResponseBody
	@RequestMapping(value="/applyCancel", method=RequestMethod.DELETE)
	public void applyCancel(@RequestParam(value = "courseId",required = true)int courseId,
									HttpServletRequest request) {
		ModelAndView modelAndView= new ModelAndView();
		//1. 매개변수 받아오기. courseId는 쿼리스트링으로.
		HttpSession session = request.getSession();
		int studentId = (int) session.getAttribute("id");
		//2. db에서 delete문 실행
		myCourseService.cancelMatching(studentId,courseId);
	}

	/* 후기 작성 - (종료된 수업) */
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

	/* 숙제 작성. - (진행중인 수업) 숙제 목록은 commonController에 */
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
				path = request.getServletContext().getRealPath(path); // 파일 저장 경로
			}
			File dir = new File(path); // 지정된 directory가 없을 때 directory 만들어주기
			if (!dir.isDirectory()) {
				dir.mkdir();
			}
			String origFileName = origFile.getOriginalFilename(); // 파일 이름 저장
			String saveFile = path + origFileName; // 파일 저장 경로 + 파일 이름 saveFile 변수에 저장
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
	public ResponseEntity<String> courseApply(@RequestParam(value = "courseId",required = true)int courseId,
									HttpServletRequest request) {
		//1. 매개변수 받아오기. courseId는 쿼리스트링으로.
		HttpSession session = request.getSession();
		int studentId = (int) session.getAttribute("id");
		Matching matching = new Matching();
		matching.setStudentId(studentId);
		matching.setCourseId(courseId);
		try {
			matchingService.insertMatching(matching);
			return new ResponseEntity<String>("신청이 완료되었습니다.",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("이미 신청한 수업입니다.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
