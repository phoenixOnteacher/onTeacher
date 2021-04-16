package com.onteacher.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("sampleController")
@RequestMapping("/sample")
public class SampleController {
	@RequestMapping(value="/main.do", method=RequestMethod.GET)  // 주소: localhost:8090/sample/main.do 로 들어가면 확인가능
	public String main(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("page", "index");
		return "template";
	}
}
