package com.onteacher.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.onteacher.prop.UploadPath;
import com.onteacher.service.CourseManageService;
import com.onteacher.service.CourseService;
import com.onteacher.service.TeacherService;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.Homework;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.Matching;
import com.onteacher.vo.StudentReview;
import com.onteacher.vo.Teacher;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	CourseManageService courseManageService;

	@Autowired
	TeacherService teacherService;

	@Autowired
	CourseService courseService;
	
	@Autowired
	private UploadPath uploadPath;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String thjoin(Model model) {
		model.addAttribute("page", "thjoin_form");
		return "template";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ModelAndView thjoin(@RequestPart("file") List<MultipartFile> files, @ModelAttribute Teacher teacher,
			MultipartHttpServletRequest multi) {
		ModelAndView modelAndView = new ModelAndView();
		try {

			if (!files.isEmpty()) {
				if (files.get(0).getContentType().split("/")[0].equals("image")) { // 이미지 파일인지 체크
					String path = uploadPath.getThprofilePath(); // 파일 저장 경로
					if(!uploadPath.isAws()) {    //aws가 아닐 때 경로 지정
						path = multi.getServletContext().getRealPath(path);
					}
					File dir = new File(path); // 지정된 directory가 없을 때 directory 만들어주기
					if (!dir.isDirectory()) {
						dir.mkdir();
					}
					String origFileName = files.get(0).getOriginalFilename(); // 파일 이름 저장
					String saveFile = path + origFileName; // 파일 저장 경로 + 파일 이름 saveFile 변수에 저장
					File targetfile = new File(saveFile);

					try {
						files.get(0).transferTo(targetfile);
						teacher.setProfileImg(origFileName);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (!files.get(1).isEmpty()) {
					String path = uploadPath.getThcertiPath(); // 파일 저장 경로
					if(!uploadPath.isAws()) {    //aws가 아닐 때 경로 지정
						path = multi.getServletContext().getRealPath(path);
					}
					File dir = new File(path); // 지정된 directory가 없을 때 directory 만들어주기
					if (!dir.isDirectory()) {
						dir.mkdir();
					}
					String origFileName = files.get(1).getOriginalFilename(); // 파일 이름 저장
					String saveFile = path + origFileName; // 파일 저장 경로 + 파일 이름 saveFile 변수에 저장

					try {
						files.get(1).transferTo(new File(saveFile));
						teacher.setFileName(origFileName);

					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			teacherService.thjoin(teacher);
			modelAndView.addObject("page", "login_form");
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("template");
		return modelAndView;
	}

	/* 수업 관리 페이지로 이동 */
	@RequestMapping(value = "/course-manage", method = RequestMethod.GET)
	public String courseManage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("id");
		int userId = 399999;
		// 리스트 불러오기
		try {
			model.addAttribute("studyingList", courseManageService.queryStudyingCourseList(userId));
			model.addAttribute("matchingList", courseManageService.queryMatchingCourseList(userId));
			model.addAttribute("matchedList", courseManageService.queryMatchedCourseList(userId));
			model.addAttribute("endList", courseManageService.queryEndCourseList(userId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("page", "teacher/courseManage");
		return "template";
	}

	/* 특정 수업 관리 */
	@RequestMapping(value = "/course-manage/{course_id}", method = RequestMethod.GET)
	public String courseDetail(HttpServletRequest request, Model model, @PathVariable String course_id) {
		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("id");
		int userId = 1;
		int courseId = Integer.parseInt(course_id);
		try {
			// 선생님인지 확인하는 코드 추가하기
			Course course = courseService.queryCourseById(courseId);
			HighCategory highCategory = courseService.queryHighCategoryById(course.getHighCategoryId());
			LowCategory lowCategory = courseService.queryLowCategoryById(course.getLowCategoryId());
			Teacher teacher = courseService.queryTeacherById(course.getTeacherId());
			model.addAttribute("highCategory", highCategory);
			model.addAttribute("lowCategory", lowCategory);
			model.addAttribute("teacher", teacher);
			model.addAttribute("course", course);
			model.addAttribute("students", courseManageService.queryMatchingStudentList(courseId, userId));
			model.addAttribute("homeworks", courseService.queryHomeworkList(courseId));
			model.addAttribute("page", "teacher/courseManageDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}

	/* 과제 내기 폼 */
	@RequestMapping(value = "/course-manage/{course_id}/homework", method = RequestMethod.GET)
	public String homeworkForm(HttpServletRequest request, Model model, @PathVariable String course_id) {
		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("id");
		int userId = 399999;
		int courseId = Integer.parseInt(course_id);
		try {
			Course course = courseService.queryCourseById(courseId);
			if (course.getTeacherId() == userId) {
				model.addAttribute("course", course);
				model.addAttribute("page", "teacher/homeworkForm");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}

	/* 과제 내기 */
	@RequestMapping(value = "/course-manage/{course_id}/homework", method = RequestMethod.POST)
	public String homework(HttpServletRequest request, @RequestPart("file") MultipartFile file,
			@ModelAttribute Homework hw, @PathVariable String course_id, Model model,
			MultipartHttpServletRequest multi) {
		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("id");
		int userId = 399999;
		int courseId = Integer.parseInt(course_id);
		hw.setCourseId(courseId);

		// 파일 업로드
		if (!file.isEmpty()) {
			String path = multi.getServletContext().getRealPath("/homeworkupload/"); // 파일 저장 경로
			File dir = new File(path); // 지정된 directory가 없을 때 directory 만들어주기
			if (!dir.isDirectory()) {
				dir.mkdir();
			}

			// 파일 이름을 식별하기 위한 처리
			Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
			SimpleDateFormat date_format = new SimpleDateFormat("yyyyMMddHHmmss");
			// 000010002_20210504110616_filename의 형태로 저장됨
			String origFileName = String.format("%09d", courseId) + "_" + date_format.format(date_now) + "_"
					+ file.getOriginalFilename();
			String saveFile = path + origFileName; // 파일 저장 경로 + 파일 이름 saveFile 변수에 저장
			File targetfile = new File(saveFile);

			try {
				file.transferTo(targetfile);
				hw.setFilename(origFileName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			courseManageService.setHomework(hw);
			model.addAttribute("homework", hw);
			model.addAttribute("course", courseService.queryCourseById(courseId));
			model.addAttribute("page", "common/homeworkDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}

	/* 학생 후기 작성 폼 */
//	@RequestMapping(value="/{course_id}/review/{student_id}", method = RequestMethod.GET)
//	public String reviewForm(Model model, @PathVariable String course_id, @PathVariable String student_id) {
//		// request user가 선생님인지 확인하고 폼으로 이동하는 로직 추가
//		model.addAttribute("page", "teacher/studentReviewForm");
//		return "template";
//	}

	/* 학생 후기 작성 */
	@ResponseBody
	@RequestMapping(value = "/{course_id}/review/{student_id}", method = RequestMethod.POST)
	public void writeReview(HttpServletRequest request, @RequestBody Map<String, String> reqData, Model model,
			@PathVariable String course_id, @PathVariable String student_id) {
		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("id");
		int userId = 1;
		StudentReview sr = new StudentReview();
		sr.setTeacherId(userId);
		sr.setCourseId(Integer.parseInt(course_id));
		sr.setStudentId(Integer.parseInt(student_id));
		sr.setContent(reqData.get("content"));
		try {
			courseManageService.writeStudentReview(sr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 수업 시작 */
	@ResponseBody
	@RequestMapping(value = "/{course_id}/start", method = RequestMethod.POST)
	public void startCourse(HttpServletRequest request, @PathVariable String course_id) {
		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("id");
		int userId = 1;
		try {
			int courseId = Integer.parseInt(course_id);
			courseManageService.startCourse(courseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 수업 연장 */
	@RequestMapping(value = "/{course_id}/extend", method = RequestMethod.POST)
	public String extendCourse(HttpServletRequest request,
			@RequestParam(value = "extendDate", required = true) String extendDate, Model model,
			@PathVariable String course_id) {
		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("id");
		int userId = 1;
		try {
			int courseId = Integer.parseInt(course_id);
			courseManageService.extendCourse(courseId, extendDate);
			model.addAttribute("studyingList", courseManageService.queryStudyingCourseList(userId));
			model.addAttribute("matchingList", courseManageService.queryMatchingCourseList(userId));
			model.addAttribute("matchedList", courseManageService.queryMatchedCourseList(userId));
			model.addAttribute("endList", courseManageService.queryEndCourseList(userId));
			model.addAttribute("page", "teacher/courseManage");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}

	/* 수업 취소 */
	@ResponseBody
	@RequestMapping(value = "/{course_id}", method = RequestMethod.DELETE)
	public void cancelCourse(HttpServletRequest request, @PathVariable String course_id) {
		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("id");
		int userId = 1;
		try {
			int c_id = Integer.parseInt(course_id);
			Course course = new Course();
			course.setId(c_id);
			course.setTeacherId(userId);
			courseManageService.cancelCourse(course);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 매칭하기 */
	@ResponseBody
	@RequestMapping(value = "/{course_id}/matching", method = RequestMethod.POST)
	public void match(HttpServletRequest request, @RequestBody Map<String, List<String>> reqData,
			@PathVariable String course_id) {
		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("id");
		int userId = 1;
		int courseId = Integer.parseInt(course_id);
		List<String> selectedStudents = reqData.get("selectedStudents");
		try {
			if (selectedStudents.size() == 0)
				throw new Exception("선택된 학생이 없음");
			Course course = courseService.queryCourseById(courseId);
			if (!course.getStatus().equals("matching"))
				throw new Exception("매칭 대기 중인 수업만 매칭 가능");
			if (course.getTeacherId() != userId)
				throw new Exception("해당 수업의 선생님만 매칭 가능");
			courseManageService.match(courseId, selectedStudents);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 매칭 취소 */
	@ResponseBody
	@RequestMapping(value = "/{course_id}/matching", method = RequestMethod.DELETE)
	public void cancelMatching(HttpServletRequest request, @RequestBody Map<String, String> reqData,
			@PathVariable String course_id) {
		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("id");
		int userId = 1;
		int courseId = Integer.parseInt(course_id);
		int studentId = Integer.parseInt(reqData.get("studentId"));
		try {
			Course course = courseService.queryCourseById(courseId);
			if (!course.getStatus().equals("matched"))
				throw new Exception("매칭 완료된 수업만 매칭 취소 가능");
			Matching matching = new Matching();
			matching.setCourseId(courseId);
			matching.setStudentId(studentId);
			courseManageService.cancelMatching(matching);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Registering course start */
	@RequestMapping(value = "/courseregister", method = RequestMethod.GET)
	public String courseregister(Model model, HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("teacher_id", id);

		try {
			model.addAttribute("highCategory", courseManageService.getHighCategory());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("teacher_id", id);
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
	public String upload(@ModelAttribute Course course, MultipartHttpServletRequest multi, Model model,
			HttpServletRequest request) throws Exception {

		int teacher_id = 300001;

		MultipartFile origFile = course.getFile();

		if (!origFile.isEmpty()) {
			String path = uploadPath.getCoursePath(); // 파일 저장 경로
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
				course.setCurriculumFile(origFileName);
				System.out.println(course.getCurriculumFile());
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		course.setTeacherId(teacher_id);
		courseManageService.registerCourse(course);
		model.addAttribute("page", "teacher/course_register");
		return "template";
	}

	@RequestMapping(value = "/teacherDetail", method = RequestMethod.GET)
	public String teacherDetail(@RequestParam(value = "teacherId") int teacherId, Model model,
			HttpServletRequest request) {
		try {
			Teacher teacher = teacherService.teacherInfo(teacherId);
			String path = "/thprofileupload/";
			teacher.setProfileImg(path + teacher.getProfileImg());
			model.addAttribute("teacher", teacher);
			model.addAttribute("page", "teacher/teacherDetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "template";
	}

	@RequestMapping(value = "/certfiledownload", method = RequestMethod.GET)
	public void filedownload(@RequestParam(value = "filename") String filename, HttpServletRequest request,
			HttpServletResponse response) {
		String saveDir = request.getSession().getServletContext().getRealPath("/thcertiupload/");
		File file = new File(saveDir + filename);
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
			response.setHeader("Content-Transfer-Encoding", "binary");
			OutputStream out = response.getOutputStream();
			// 파일 카피 후 마무리
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
			out.flush();
		} catch (Exception e) {
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

	/* 자격증명 재업로드 폼 가져오기 - 기존 올렸던 파일 가져오도록 teacher객체 전달 */
	@RequestMapping(value = "/cert-reupload", method = RequestMethod.GET)
	public String teacherCertReupload(Model model) {
//		HttpSession session = request.getSession();	
//		int user_id = (int) session.getAttribute("id");
		int user_id = 300005;
		try {
			Teacher teacher = teacherService.teacherInfo(user_id);
			model.addAttribute("teacher", teacher);
			model.addAttribute("page", "teacher/CertificationReupload");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "template";
	}

	/* 자격증명 재업로드 기능 수행 */
	@RequestMapping(value = "/cert-reupload", method = RequestMethod.POST)
	public void teacherCertReupload(@ModelAttribute Teacher teacher, MultipartHttpServletRequest multi, Model model,
			HttpServletResponse response, HttpServletRequest request) throws IllegalStateException, IOException {
//		HttpSession session = request.getSession();	
//		int user_id = (int) session.getAttribute("id");
		int user_id = 300005;
		teacher.setId(user_id);
		try {
			MultipartFile origFile = teacher.getFile();

			String path = multi.getServletContext().getRealPath("/thcertiupload/"); // 파일 저장 경로
			File dir = new File(path); // 지정된 directory가 없을 때 directory 만들어주기
			if (!dir.isDirectory()) {
				dir.mkdir();
			}
			String origFileName = origFile.getOriginalFilename(); // 파일 이름 저장
			String saveFile = path + origFileName; // 파일 저장 경로 + 파일 이름 saveFile 변수에 저장
			try {
				origFile.transferTo(new File(saveFile));
				teacher.setFileName(origFileName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// fileName, description, status="submitted"로 update
			teacherService.updateTeacherCert(teacher);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
