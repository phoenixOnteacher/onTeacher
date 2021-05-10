package com.onteacher.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.onteacher.prop.UploadPath;
import com.onteacher.service.OcrService;

@Controller("Controller")
@RequestMapping("/ocr")
public class OcrController {
	@Autowired
	private UploadPath uploadPath;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET) // 호출 주소 : localhost:8090/ocr/main
	public String ocrMain(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("page", "ocr/ocrInsertForm");
		return "template";
	}

	@RequestMapping(value = "/ImageUpload", method = RequestMethod.POST)
	public String imageUpload(MultipartHttpServletRequest mtfRequest, Model model)
			throws IllegalStateException, IOException {
		try {
			MultipartFile orgfile = mtfRequest.getFile("file");

			if (!orgfile.isEmpty()) {

				//String path = mtfRequest.getServletContext().getRealPath("/ocrupload/");
				String path = uploadPath.getOcrPath();
				if(!uploadPath.isAws()) {
					path=mtfRequest.getServletContext().getRealPath(path);
				}

				File dir = new File(path);
				if (!dir.isDirectory()) {
					dir.mkdir();
				}
				File destFile = new File(path + orgfile.getOriginalFilename());

				orgfile.transferTo(destFile);
				String res = OcrService.ImageRecognize(path + orgfile.getOriginalFilename());
				// OcrService의 ImageRecognize 메소드를 처리한 후 res 변수에 저장한다.
				model.addAttribute("text", res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("page", "ocr/ocrRecognize");
		return "template";
	}

	@RequestMapping(value = "/savefile", method = RequestMethod.POST)
	public void saveFile(@RequestParam(value = "txtSave") String txtSave,
			@RequestParam(value = "filename") String filename, HttpServletRequest request,
			HttpServletResponse response) {

		//String path = mtfRequest.getServletContext().getRealPath("/ocrupload/");
		String path = uploadPath.getOcrPath();
		if(!uploadPath.isAws()) {
			path=request.getServletContext().getRealPath(path);
		}		
		
		File file = new File(path + filename);
		String sfilename = null;
		FileInputStream fis = null;

		try {

			FileWriter writer = new FileWriter(file);
			writer.write(txtSave);
			writer.close();
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

		} catch (IOException e) {
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