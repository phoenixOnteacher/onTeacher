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
import com.onteacher.vo.Comment;

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

			MultipartFile orgfile = article.getFile();
			System.out.println(orgfile.getOriginalFilename());
			if (orgfile != null && orgfile.getOriginalFilename().trim() != "") {
				String path = request.getServletContext().getRealPath("/boardupload/");
				File dir = new File(path);
				if (!dir.isDirectory()) {
					dir.mkdir();
				}

				File destFile = new File(path + orgfile.getOriginalFilename());
				orgfile.transferTo(destFile);
				article.setFilename(orgfile.getOriginalFilename());
			} else {
				article.setFilename("");

			}

			boardService.addArticle(article);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "redirect:/listArticle";
	}

	@RequestMapping(value = "/listArticle", method = RequestMethod.GET)
	public String listArticle(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model) {

		try {
			Article article = new Article();
			article.setS_row((page - 1) * 10 + 1);
			article.setE_row(page * 10);
			List<Article> listarticle = boardService.listArticles(article);
			model.addAttribute("articlesList", listarticle);
			model.addAttribute("currentPage", page);
			model.addAttribute("pageCnt", boardService.articleCount() / 10 + 1);
			model.addAttribute("page", "board/listArticles");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "template";

	}

	@RequestMapping(value = "/viewArticle", method = RequestMethod.GET)
	public String viewArticle(@RequestParam(value = "no") int no, Model model) {

		try {
			Article article = boardService.viewArticle(no);
			List<Comment> commentList = boardService.commentList(no);
			model.addAttribute("article", article);
			model.addAttribute("commentList", commentList);
			model.addAttribute("page", "board/viewArticle");

		} catch (Exception e) {
			e.printStackTrace();

		}

		return "template";
	}

	// 댓글 기능
	// comment------------------------------------------------------------------

	// 댓글쓰기

	@RequestMapping(value = "/commentForm", method = RequestMethod.GET)
	public String commentForm(Model model) {
		model.addAttribute("page", "board/commentForm");
		return "template";
	}

	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public String addComment(@ModelAttribute Comment comment, Model model) {
		System.out.println(comment.getArticle_id());
		System.out.println(comment.getUser_id());
		try {
			boardService.addComment(comment);
			model.addAttribute("no", comment.getArticle_id());
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "redirect:viewArticle?no=" + comment.getArticle_id();
	}

	// 댓글 리스트 보기

	@RequestMapping(value = "/commentList", method = RequestMethod.GET)
	public String commentList(Model model) {
		model.addAttribute("page", "viewComment");
		return "template";

	}

	
}
