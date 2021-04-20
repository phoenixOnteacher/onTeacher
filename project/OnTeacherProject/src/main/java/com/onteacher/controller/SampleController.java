package com.onteacher.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller("sampleController")
@RequestMapping("/sample")
public class SampleController {
	@RequestMapping(value="/main.do", method=RequestMethod.GET)  // 주소: localhost:8090/sample/main.do 로 들어가면 확인가능
	public String main(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("page", "index");
		return "template";
	}
	
	@RequestMapping(value="/ocr.do", method=RequestMethod.GET) // 주소 : localhost:8090/sample/ocr.do
	public String join(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("page", "ocrInsertForm");
		return "template";		
	}
	
	@RequestMapping(value = "/ocrImageUpload", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest mtfRequest, Model model) {
		String src = mtfRequest.getParameter("src");
		System.out.println("src value : " + src);
		MultipartFile mf = mtfRequest.getFile("file");
		String path="c:\\img\\";
		String originFileName = mf.getOriginalFilename(); // 원본 파일 명
		String safeFile = path + originFileName;

		try {
			mf.transferTo(new File(safeFile));
			model.addAttribute("page", "ocrInsertSuccess");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "template";
	}
	
}