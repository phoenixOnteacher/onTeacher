package com.onteacher.controller;

import java.security.Provider.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onteacher.vo.Article;

@Controller
public class BoardController {

	@Autowired
	private Service boardService;

	// 게시판 메인페이지( 리스트)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) {
		model.addAttribute("page", "listArticles");
		return "template";
	}

	// 글쓰기
	@RequestMapping(value = "/articleForm", method = RequestMethod.GET)
	public String articleForm(Model model) {
		model.addAttribute("page", "articleForm");
		return "template";
	}

	@RequestMapping(value = "/articleForm", method = RequestMethod.POST)
	public String addNewArticle(@ModelAttribute addNewArticle addNewArticle, Model model) {
		try {
			boardService.addNewArticle(addNewArticle);
			model.addAttribute("page", "articleForm");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "글 쓰기 실패");
			model.addAttribute("page", "err");
		}
		return "template";
	}

	// 글보기
	@RequestMapping(value = "/viewArticle", method = RequestMethod.GET)
	public String viewArticle(Model model) {
		model.addAttribute("page", "viewArticle");
		return "template";
	}

	@RequestMapping(value = "/viewArticle", method = RequestMethod.POST)
	public String articleForm(@ModelAttribute viewArticle viewArticle, Model model) {
		try {
			boardService.viewArticle(viewArticle);
			model.addAttribute("page", "viewArticle");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "글 쓰기 실패");
			model.addAttribute("page", "err");
		}
		return "template";
	}

	// 글 수정 삭제는 따로 화면view를 안만들어서 일단 보류

	// 글 수정
	@RequestMapping(value = "/modArticle", method = RequestMethod.GET)
	public String modArticle(Model model) {

	}

	// 글 삭제
	@RequestMapping(value="/removeArticle", method=RequestMethod.GET)
			public String removeArticle(Model model) {
		
			}
}



// 이 밑으로는
// 참고해볼것들--------------------------------------------------------------------------------------
/*
 * // 글 수정
 * 
 * @RequestMapping(value = "/modArticle") public String
 * boardUpdate(HttpServletRequest request, ModelMap modelMap) throws Exception {
 * 
 * String articleNO = request.getParameter("articleNO");
 * 
 * boardVO modArticle = boardService.modArticle(articleNO);
 * 
 * modelMap.addAttribute("modArticle", modArticle);
 * 
 * return "board/modArticle"; }
 * 
 * @RequestMapping(value = "/boardUpdateSave") public String
 * board1UpdateSave(@ModelAttribute boardVO boardInfo) throws Exception {
 * 
 * boardSvc.updateBoard(boardInfo);
 * 
 * return "redirect:/boardList"; }
 * 
 * // 글 읽기
 * 
 * @RequestMapping(value = "/boardRead") public String
 * boardRead(HttpServletRequest request, ModelMap modelMap) throws Exception {
 * 
 * String brdno = request.getParameter("brdno");
 * 
 * boardVO boardInfo = boardSvc.selectBoardOne(brdno);
 * 
 * modelMap.addAttribute("boardInfo", boardInfo);
 * 
 * return "board/boardRead"; }
 * 
 * // 글 삭제
 * 
 * @RequestMapping(value = "/board1Delete") public String
 * boardDelete(HttpServletRequest request) throws Exception {
 * 
 * String brdno = request.getParameter("brdno");
 * 
 * boardSvc.deleteBoardOne(brdno);
 * 
 * return "redirect:/board1List"; }
 * 
 * 
 * 
 * =====================================================================
 * 
 *//**
	 * 댓글 저장.
	 */
/*
 * @RequestMapping(value = "/boardReplySave") public String
 * board5ReplySave(HttpServletRequest request, BoardReplyVO boardReplyInfo) {
 * 
 * boardSvc.insertBoardReply(boardReplyInfo);
 * 
 * return "redirect:/boardRead?brdno=" + boardReplyInfo.getBrdno(); }
 * 
 *//**
	 * 댓글 삭제.
	 */
/*
 * @RequestMapping(value = "/boardReplyDelete") public String
 * board5ReplyDelete(HttpServletRequest request, BoardReplyVO boardReplyInfo) {
 * 
 * boardSvc.deleteBoard5Reply(boardReplyInfo.getReno());
 * 
 * return "redirect:/boardRead?brdno=" + boardReplyInfo.getBrdno(); } }
 */
