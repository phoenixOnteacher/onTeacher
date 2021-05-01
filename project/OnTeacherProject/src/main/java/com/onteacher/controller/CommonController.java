package com.onteacher.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onteacher.service.UserService;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.PagingVO;

@Controller("commonController")
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	UserService userService;
	@RequestMapping(value="/searchCourse", method = RequestMethod.GET) 
	public ModelAndView searchCourseDefault() {
		ModelAndView modelAndView = new ModelAndView();
		List<HighCategory> highCategory = userService.highcategoryList();
		modelAndView.addObject("highCategory", highCategory);
		modelAndView.addObject("page","search");
		modelAndView.setViewName("template");
		return modelAndView;
	}
	
	@RequestMapping(value="/searchCourse", method = RequestMethod.POST)
	public ModelAndView searchCourseDefault(
			@RequestParam(value="highcategory") int highcategory_id,
			@RequestParam(value="lowcategory") int lowcategory_id,
			@RequestParam(value="target") String target,
			@RequestParam(value="isonline") char isonline) {		
		
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
		modelAndView.addObject("courses",courses);
		modelAndView.addObject("page","search");
		modelAndView.setViewName("template");
		return modelAndView;
	}
	@GetMapping("boardList")
	public String boardList(PagingVO vo, Model model
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		int total = UserService.countBoard();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "5";
		}
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", UserService.selectBoard(vo));
		return "board/boardPaging";
	}
}