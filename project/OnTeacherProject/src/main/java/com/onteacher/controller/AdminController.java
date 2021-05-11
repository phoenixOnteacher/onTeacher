package com.onteacher.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onteacher.prop.UploadPath;
import com.onteacher.service.AdminService;
import com.onteacher.service.TeacherService;
import com.onteacher.vo.Teacher;

@Controller("AdminController")
@RequestMapping("/admin")
public class AdminController {
 
	@Autowired
	AdminService adminService;

	@Autowired
	TeacherService teacherService;
	
	@Autowired
	private UploadPath uploadPath;

	@RequestMapping(value = "/cert", method = RequestMethod.GET)
	public ModelAndView certConfirm() {
		ModelAndView modelAndView = new ModelAndView();
		try {
			List<Teacher> tea = teacherService.certConfirm();
			
			/*
			 * MAPPER 체크 테스트 DATA 넘어오는 것을 체크하는 테스트 코드 정상적으로 DB에서 데이터가 넘어오면 콘솔에 찍힘.
			 * for(Teacher t : tea) {
			 * System.out.println(t.getId()+t.getName()+t.getPhoneNumber()+t.getEmail()); }
			 */
			 
			modelAndView.addObject("tea", tea);
			modelAndView.addObject("page", "admin/boardList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("template");
		return modelAndView;
	}	

	@RequestMapping(value = "/certApproved", method = RequestMethod.POST)
	public String certApproved( @RequestParam(value = "email",required = true) String email) {

		try {
			//TEACHER TABLE STATUS = approved로 업데이트하기
			//TEACHER TABLE ACTIVE = 1로 업데이트하기
			teacherService.certApproved(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:cert"; // "localhost:8090/admin/cert URL로 리다이렉트 요청"   
	}
	
	@RequestMapping(value = "/certRejected", method = RequestMethod.POST)
	public String certRejected( @RequestParam(value = "email",required = true) String email, @RequestParam(value = "message",required = true) String message) {
		try {
			//TEACHER TABLE STATUS = rejected로 업데이트하기
			teacherService.certRejected(email, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:cert"; // "localhost:8090/admin/cert URL로 리다이렉트 요청"   
	}
	
	@RequestMapping(value = "/certiDownload", method = RequestMethod.GET)
	public void articleDownload(@RequestParam(value="fileName") String filename, HttpServletRequest request, HttpServletResponse response) {
		String saveDir = uploadPath.getThcertiPath();
		
		if(!uploadPath.isAws()) {
			saveDir = request.getServletContext().getRealPath(saveDir);
		}
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
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + sfilename + "\";");
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
}