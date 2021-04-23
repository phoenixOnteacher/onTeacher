package com.onteacher.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String ocrMain(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("page", "ocrInsertForm");
		return "template";		
	}
	
	@RequestMapping(value = "/ocrImageUpload", method = RequestMethod.POST)
	public String imageUpload(MultipartHttpServletRequest mtfRequest, Model model)
			throws IllegalStateException, IOException {
		String path = mtfRequest.getServletContext().getRealPath("/upload/");
		System.out.println(path);
		File dir = new File(path);
		if (!dir.isDirectory()) {
			dir.mkdir();
		}
		MultipartFile orgfile = mtfRequest.getFile("file");
		File destFile = new File(path + orgfile.getOriginalFilename());
		System.out.println(destFile.getPath());
		orgfile.transferTo(destFile);
		model.addAttribute("page", "ocrInsertSuccess");
		model.addAttribute("file", orgfile);
		return "template";
	}

	@RequestMapping(value = "/filedownload", method = RequestMethod.GET)
	public void imageDownload(@RequestParam(value = "filename") String filename, HttpServletRequest request, HttpServletResponse response) {
		String saveDir = request.getSession().getServletContext().getRealPath("/upload/");
		File file = new File(saveDir + filename);
		System.out.println(file);
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
}	
