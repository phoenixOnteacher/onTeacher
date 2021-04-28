package com.onteacher.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.onteacher.service.BoardService;
import com.onteacher.vo.Article;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 게시판 메인페이지( 리스트)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) {
		model.addAttribute("page", "listArticles");
		return "template";
	}

	// 글쓰기
	@RequestMapping(value = "/articleForm", method = RequestMethod.GET)
	public String articleForm(Model model) {
		model.addAttribute("page", "board/articleForm");
		return "template";
	}

	@RequestMapping(value = "/addArticle", method = RequestMethod.POST)
	public String addArticle(@ModelAttribute Article article, HttpServletRequest request) {


		
		try {
			String path= request.getServletContext().getRealPath("/boardupload/");
			File dir = new File(path);
			if(!dir.isDirectory()) {
				dir.mkdir();
			}
			MultipartFile orgfile = article.getFile();
			File destFile = new File(path+orgfile.getOriginalFilename());
			orgfile.transferTo(destFile);
			article.setFilename(orgfile.getOriginalFilename());
			boardService.addArticle(article);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "redirect:/listArticle";
	}
	
	@RequestMapping(value = "/listArticle", method = RequestMethod.GET)
	public String listArticle(@RequestParam(value="page") int page, Model model) {
		
		if(page<=0) page=1;
				
		
		
	
		try {
			Article article= new Article();
			article.setS_row((page-1)*10+1);
			article.setE_row(page*10);
			List<Article> listarticle = boardService.listArticles(article);
			model.addAttribute("articlesList", listarticle);
			model.addAttribute("page", "board/listArticles");

			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "template";

	
	}
	
	@RequestMapping(value = "/viewArticle", method = RequestMethod.GET)
	public String viewArticle(@RequestParam(value="no") int no, Model model) {

		try {
			Article article = boardService.viewArticle(no); 
			model.addAttribute("article", article);
			model.addAttribute("page", "board/viewArticle");
			
			
		} catch (Exception e) {
				e.printStackTrace();
				
			}
		
		return "template";
	}
	
	
	
	
	
	
	
	
	
/*
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
		
			}*/
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
