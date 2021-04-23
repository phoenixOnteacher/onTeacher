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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.onteacher.service.CourseManageService;
import com.onteacher.service.TeacherService;
import com.onteacher.vo.Course;
import com.onteacher.vo.LowCategory;

@Controller
public class TeacherController {

	@Autowired
	CourseManageService courseManageService;

	@Autowired
	TeacherService teacherService;

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

		MultipartFile origFile = multi.getFile("file");
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
