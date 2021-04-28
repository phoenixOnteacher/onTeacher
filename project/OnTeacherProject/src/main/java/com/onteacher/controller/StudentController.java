package com.onteacher.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.onteacher.service.MyCourseService;
import com.onteacher.service.StudentService;
import com.onteacher.vo.Homework;
import com.onteacher.vo.Student;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@Autowired
	MyCourseService myCourseService;

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
				String path = multi.getServletContext().getRealPath("/stprofileupload/"); // 파일 저장 경로
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

	@RequestMapping(value = "/{course_id}/homeworkanswer", method = RequestMethod.POST)
	public String homeworkAnswer(@ModelAttribute Homework hw, Model model, @PathVariable String course_id) {
		hw.setCourseId(Integer.parseInt(course_id));
		try {
//			myCourseService.setHomework(hw);
			model.addAttribute("homework", hw);
			model.addAttribute("page", "homeworkDetail");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("page", "index");
		}
		return "template";
	}
}
