package com.onteacher.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onteacher.prop.UploadPath;
import com.onteacher.service.CourseManageService;
import com.onteacher.service.CourseService;
import com.onteacher.service.TeacherService;
import com.onteacher.service.UserService;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.Homework;
import com.onteacher.vo.HomeworkAnswer;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.Teacher;

@Controller
@RequestMapping
public class CommonController {

	@Autowired
	CourseService courseService;

	@Autowired
	UserService userService;

	@Autowired
	CourseManageService courseManageService;

	@Autowired
	TeacherService teacherService;

	@Autowired
	private UploadPath uploadPath;
	
	@GetMapping("/fileview/thprofile/{filename}")
	public void fileview(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response) {
		String path = uploadPath.getThprofilePath();
		if (!uploadPath.isAws()) {
			path = request.getServletContext().getRealPath(path);
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
		} catch (Exception e) {
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
	

	@GetMapping("/fileview/stprofile/{filename}")
	public void fileviewStProfile(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response) {
		String path = uploadPath.getThprofilePath();
		if (!uploadPath.isAws()) {
			path = request.getServletContext().getRealPath(path);
		}
		File file = new File(path + filename);
		String sfilename = null;
		FileInputStream fis = null;
		try {
			if (request.getHeader("User-Agent").indexOf("MSIE") > -1) {
				sfilename = URLEncoder.encode(file.getName(), "utf-8");
			} else {
				sfilename = new String(file.getName().getBytes("utf-8"), "ISO-8859-1");
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + sfilename + "\";");
			OutputStream out = response.getOutputStream();
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
		}
	}

	@RequestMapping(value = "/main")
	public String main(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("courses", courseService.selectCourseForIndex());
		model.addAttribute("page", "index");
		return "template";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		model.addAttribute("page", "login_form");
		return "template";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		try {
			int id = userService.login(email, password);
			if (id == 0) {
				modelAndView.addObject("msg", false);
				throw new Exception("????????? ??????");
			}
			session.setAttribute("id", id);
			// ???????????? ??????
			// ACTIVE??? ?????? ??????(????????????, ??????????????? ?????? => ?????? ??????)
			if (id >= 300000 && id < 400000) {
				Teacher teacher = teacherService.teacherInfo(id);
				if (teacher.isActive() == true) {
					session.setAttribute("teacherActive", 1);
				} else {
					session.setAttribute("teacherActive", 0);
				}
			}
			modelAndView.setViewName("redirect:/main");
			;
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("page", "login_form");
			modelAndView.setViewName("template");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String thlogout(HttpServletRequest request, Model model) {
		request.getSession().removeAttribute("id");
		request.getSession().removeAttribute("teacherActive");
		model.addAttribute("page", "login_form");
		return "template";
	}

	/* homework detail page??? ?????? */
	@RequestMapping(value = "/homework/{homework_id}", method = RequestMethod.GET)
	public String homeworkDetail(HttpServletRequest request, Model model, @PathVariable String homework_id) {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		int homeworkId = Integer.parseInt(homework_id);
		try {
			HomeworkAnswer ha = courseService.queryHomeworkAnswer(homeworkId, userId);
			Homework hw = courseService.queryHomework(homeworkId);
			int courseId = hw.getCourseId();
			model.addAttribute("user_id", userId);
			model.addAttribute("students", courseManageService.queryStudentListAndHomeworkAnswer(homeworkId));
			model.addAttribute("course", courseService.queryCourseById(courseId));
			model.addAttribute("homework", hw);
			model.addAttribute("homeworkAnswer", ha);
			model.addAttribute("page", "common/homeworkDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}

	/* homework file ???????????? */
	@RequestMapping(value = "/hwfiledownload", method = RequestMethod.GET)
	public void homeworkfiledownload(@RequestParam(value = "filename") String filename, HttpServletRequest request,
			HttpServletResponse response) {

		String saveDir = uploadPath.getHomeworkPath();
		if (!uploadPath.isAws()) {
			saveDir = request.getServletContext().getRealPath(saveDir); // ?????? ?????? ??????
		}
		File file = new File(saveDir + filename);
		String sfilename = null;
		FileInputStream fis = null;
		try {
			String downloadFileName = file.getName().substring(25);
			// ???????????? ????????? ?????? utf-8 ??????
			if (request.getHeader("User-Agent").indexOf("MSIE") > -1) {
				sfilename = URLEncoder.encode(downloadFileName, "utf-8");
			} else {
				sfilename = new String(downloadFileName.getBytes("utf-8"), "ISO-8859-1");
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + sfilename + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			OutputStream out = response.getOutputStream();
			// ?????? ?????? ??? ?????????
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
		}
	}
	
	/* homework answer file ???????????? */
	@RequestMapping(value = "/hafiledownload", method = RequestMethod.GET)
	public void homeworkanswerfiledownload(@RequestParam(value = "filename") String filename, HttpServletRequest request,
			HttpServletResponse response) {
		String saveDir = uploadPath.getHomeworkanswerPath();
		if (!uploadPath.isAws()) {
			saveDir = request.getServletContext().getRealPath(saveDir); // ?????? ?????? ??????
		}
		File file = new File(saveDir + filename);
		String sfilename = null;
		FileInputStream fis = null;
		try {
			String downloadFileName = file.getName();
			// ???????????? ????????? ?????? utf-8 ??????
			if (request.getHeader("User-Agent").indexOf("MSIE") > -1) {
				sfilename = URLEncoder.encode(downloadFileName, "utf-8");
			} else {
				sfilename = new String(downloadFileName.getBytes("utf-8"), "ISO-8859-1");
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + sfilename + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			OutputStream out = response.getOutputStream();
			// ?????? ?????? ??? ?????????
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
		}
	}

	@RequestMapping(value = "/searchCourse", method = RequestMethod.GET)
	public ModelAndView searchCourseDefault() {
		ModelAndView modelAndView = new ModelAndView();
		List<HighCategory> highCategory = userService.highcategoryList();
		List<Course> courses = courseService.selectCourseForSearch();
		modelAndView.addObject("courses", courses);
		modelAndView.addObject("highCategory", highCategory);
		modelAndView.addObject("page", "search");
		modelAndView.setViewName("template");
		return modelAndView;
	}

	@RequestMapping(value = "/searchCourse", method = RequestMethod.POST)
	public ModelAndView searchCourseDefault(@RequestParam(value = "highcategory") int highcategory_id,
			@RequestParam(value = "lowcategory") int lowcategory_id, @RequestParam(value = "target") String target,
			@RequestParam(value = "isonline") char isonline) {
		Course course = new Course();
		course.setHighCategoryId(highcategory_id);
		course.setLowCategoryId(lowcategory_id);
		course.setTarget(target);
		course.setIsOnline(isonline);

		ModelAndView modelAndView = new ModelAndView();
		List<Course> courses = userService.queryCourseForSearch(course);
		List<HighCategory> highCategory = userService.highcategoryList();
		modelAndView.addObject("highCategory", highCategory);
		modelAndView.addObject("highcategory_id", highcategory_id);
		modelAndView.addObject("lowcategory_id", lowcategory_id);
		modelAndView.addObject("target", target);
		modelAndView.addObject("isonline", isonline);
		modelAndView.addObject("courses", courses);
		modelAndView.addObject("page", "search");
		modelAndView.setViewName("template");
		return modelAndView;
	}

	@RequestMapping(value = "/searchCourse/detail", method = RequestMethod.GET)
	public ModelAndView searchCourseDetail(@RequestParam(value = "courseId", required = true) int courseId) {
		ModelAndView modelAndView = new ModelAndView();
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
			modelAndView.addObject("page", "common/courseDetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("template");
		return modelAndView;

	}

	/* ?????? ?????? */
	@ResponseBody
	@RequestMapping(value = "/notification", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> viewNotification(HttpServletRequest request) {
		Integer userId = (Integer) request.getSession().getAttribute("id");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap.put("state", "success");
			resultMap.put("data", userService.queryNotificationList(userId));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("state", "fail");
			resultMap.put("data", null);
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	/* ????????? ?????? ?????? ?????? ?????? ??? ?????? ?????? */
	@Scheduled(cron = "0 0 0 * * *") // ?????? ?????? ?????? 0??? 0??? 0?????? ?????? (??????)
//	@Scheduled(cron = "0/5 * * * * *") // ?????? ?????? ?????? ?????? ?????? 5??? ?????? ?????? 
//	@Scheduled(cron = "0 0/10 * * * *")
    public void updateCourseStatus () {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(new Date(System.currentTimeMillis()));
        System.out.println(today + "?????? ?????? ??????/?????? ??????");
        try {
        	// ?????? ??????
        	List<Course> startCourseList = courseService.queryCourseListByStartDate(today);
        	for (Course course : startCourseList) {
        		courseManageService.startCourse(course.getId());
        	}
        	// ?????? ??????
        	List<Course> finishCourseList = courseService.queryCourseListByEndDate(today);
        	for (Course course : finishCourseList) {
        		courseManageService.finishCourse(course.getId());
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
