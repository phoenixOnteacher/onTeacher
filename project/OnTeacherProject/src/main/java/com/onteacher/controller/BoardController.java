package com.onteacher.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.onteacher.prop.UploadPath;
import com.onteacher.service.BoardService;
import com.onteacher.service.StudentService;
import com.onteacher.vo.Article;
import com.onteacher.vo.Comment;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private UploadPath uploadPath;

	@Autowired
	private StudentService studentService;
	// 게시판 메인페이지
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) {
		model.addAttribute("page", "listArticles");
		return "template";
	}

	// 글쓰기
	@RequestMapping(value = "/articleForm", method = RequestMethod.GET)
	public String articleForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Integer id = (Integer)session.getAttribute("id");
		
		
		try {
			String name = studentService.studentName(id);
			model.addAttribute("user_name", name);
			model.addAttribute("page", "board/articleForm");
		} catch (Exception e) {
			e.printStackTrace(); 
			
		}
		
		return "template";
	}

	@RequestMapping(value = "/addArticle", method = RequestMethod.POST)
	public String addArticle(@ModelAttribute Article article, HttpServletRequest request) {

		
		
		
		
		try {
		
			MultipartFile orgfile = article.getFile();
			System.out.println(orgfile.getOriginalFilename());
			if (orgfile != null && orgfile.getOriginalFilename().trim() != "") {
				String path = uploadPath.getBoardPath();
				
				if(!uploadPath.isAws()) {
					path = request.getServletContext().getRealPath(path);
				}
						
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
			Model model, HttpServletRequest request) {
			//임시처리 승빈님 자료합치면 99 line 까지 지움.
			HttpSession session = request.getSession();
			session.setAttribute("id", 200001);
			
		try {
			Article article = new Article();
			article.setS_row((page - 1) * 10 + 1);
			article.setE_row(page * 10);
			List<Article> listarticle = boardService.listArticles(article);
			//                        = boardService.updatehits(id)
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
	public String viewArticle(@RequestParam(value = "no") int no, @RequestParam(value = "pageNo", required = false) int pageNo,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			boardService.plusHits(no);
			Article article = boardService.viewArticle(no);
			List<Comment> commentList = boardService.commentList(no);
			
			model.addAttribute("article", article);
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("commentList", commentList);
			model.addAttribute("page", "board/viewArticle");
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}

		return "template";
	}
	
	@RequestMapping(value = "/articleDownload", method = RequestMethod.GET)
	public void articleDownload(@RequestParam(value="filename") String filename, HttpServletRequest request, HttpServletResponse response) {
		String saveDir = uploadPath.getBoardPath();
		
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
			response.setHeader("Content-Disposition", "attachment; filename=\"" + sfilename + "\";");
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

	
	
	
	
	
	
	
	//글 수정
	@RequestMapping(value = "/modArticle", method = RequestMethod.POST)
	public String modArticle(@ModelAttribute Article article,
			@RequestParam(value = "pageNo") int pageNo) {
		try {
			boardService.modArticle(article);
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return "redirect:listArticle?page=" + pageNo;
	}
	
	//글 삭제
	@RequestMapping(value = "/deleteArticle", method = RequestMethod.GET)
	public String deleteArticle(@RequestParam(value = "no") int no) {
		try {
			boardService.deleteArticle(no);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return "redirect:listArticle";

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
	
		try {
			boardService.addComment(comment);
			model.addAttribute("no", comment.getArticle_id());
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "redirect:viewArticle?no=" + comment.getArticle_id()+ "&pageNo=1";
	}

	// 댓글 리스트 보기

	@RequestMapping(value = "/commentList", method = RequestMethod.GET)
	public String commentList(Model model) {
		model.addAttribute("page", "viewComment");
		return "template";

	}

	
}
