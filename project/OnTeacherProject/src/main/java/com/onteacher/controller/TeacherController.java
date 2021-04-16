package com.onteacher.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onteacher.service.CourseManageService;
import com.onteacher.service.TeacherService;

@Controller
public class TeacherController {
	
	@Autowired
	CourseManageService courseManageService;
	
	@Autowired
	TeacherService teacherService;
	
	@RequestMapping(value="/{highcategory}")
	@ResponseBody
	public void subcategory(HttpServletResponse res, @PathVariable String highcategory) {
		List<Lowcategory> lowcategory = courseManageService.findLowcategory(highcategory);
        List<String> lowcateList = new ArrayList();

        for (int i = 0; i < lowcategory.size(); i++) {
            lowcateList.add(lowcategory.get(i).getLowcate2());
        }

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < lowcateList.size(); i++) {
            jsonArray.put(lowcateList.get(i));
        }

        PrintWriter pw = res.getWriter();
        pw.print(jsonArray.toString());
        pw.flush();
        pw.close();
	}
	
	@RequestMapping(value="/courseregister.do", method=RequestMethod.GET)  // 주소: localhost:8090/sample/class.do 로 들어가면 확인가능
	public String courseregister(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("page", "course_register");
		return "template";
	}
}
