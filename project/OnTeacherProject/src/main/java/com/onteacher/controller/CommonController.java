package com.onteacher.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onteacher.service.CourseManageService;
import com.onteacher.service.CourseService;
import com.onteacher.service.UserService;
import com.onteacher.vo.Homework;
import com.onteacher.vo.HomeworkAnswer;

@Controller
@RequestMapping
public class CommonController {

	@Autowired
	CourseService courseService;
	
	@Autowired
	UserService userService;

	@Autowired
	CourseManageService courseManageService;
	
	@RequestMapping(value="/main")
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
	public ModelAndView login(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			int id = userService.login(email,password);
			if (id==0) {
				modelAndView.addObject("msg", false);
				throw new Exception("로그인 실패");
			}
			request.getSession().setAttribute("id", id);
			
			modelAndView.setViewName("redirect:/main");;
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
		model.addAttribute("page", "login_form");
		return "template";
	}
	
	// homework detail page로 이동
	@RequestMapping(value="/homework/{homework_id}", method=RequestMethod.GET)
	public String homeworkDetail(HttpServletRequest request, Model model, @PathVariable String homework_id) {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
//		int userId = 399999;
		int homeworkId = Integer.parseInt(homework_id);
		try {
			HomeworkAnswer ha = courseService.queryHomeworkAnswer(homeworkId,userId);
			Homework hw = courseService.queryHomework(homeworkId);
			int courseId = hw.getCourseId();
			// model.addAttribute("homeworkAnswerList",);
			model.addAttribute("user_id", userId);
			model.addAttribute("students", courseManageService.queryStudentListAndHomeworkAnswer(homeworkId));
			model.addAttribute("course", courseService.queryCourseById(courseId));
			model.addAttribute("homework", hw);
			model.addAttribute("homeworkAnswer",ha);
			model.addAttribute("page", "common/homeworkDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}
	
	/* homework file 다운로드 */
	@RequestMapping(value="/hwfiledownload",  method=RequestMethod.GET) 
	public void homeworkfiledownload(@RequestParam(value="filename") String filename, HttpServletRequest request, HttpServletResponse response) {
		String saveDir = request.getSession().getServletContext().getRealPath("/homeworkupload/");
		File file = new File(saveDir + filename);
		String sfilename = null;
		FileInputStream fis = null;
		try {
			String downloadFileName = file.getName().substring(25);
			// 브라우저 정보에 따라 utf-8 변경
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
		}	
	}

}
