package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.board.BoardDAO;
import spring.model.board.BoardDTO;
import spring.model.board.BoardMgr;
import spring.model.board.BreplyDAO;
import spring.model.board.BreplyDTO;
import spring.utility.webtest.utility;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	@Autowired
	private BreplyDAO rdao;
	@Autowired
	private BoardMgr mgr;

	@RequestMapping("/board/list")
	public String list(HttpServletRequest request, Model model) {
		String col = utility.checkNull(request.getParameter("col"));
		String word = utility.checkNull(request.getParameter("word"));
		if (col.equals("total"))
			word = "";

		int nowPage = 1;
		int recordPerPage = 5;

		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}

	   int sno = ((nowPage-1)*recordPerPage) + 1;
	   int eno = nowPage * recordPerPage;
	   
	   Map map = new HashMap();
	   map.put("col", col);
	   map.put("word", word);
	   map.put("sno", sno);
	   map.put("eno", eno);

		// 1. model 사용
		List<BoardDTO> list = dao.getList(map);
		int total = dao.getTotal(map);
		String paging = utility.paging3(total, nowPage, recordPerPage, col, word);
		
		// 2. request 저장
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		
		return "/board/list";
	}

	
	@RequestMapping("/board/read")
	public String read(int num, Model model, HttpServletRequest request) {
		dao.viewCount(num);
		BoardDTO dto = dao.read(num);

		model.addAttribute("dto", dto);
		model.addAttribute("content", dto.getContent().replaceAll("\r\n", "<br>"));
		
		
		/***댓글 처리***/
		
		String url = "read"; //댓글페이지의 매개변수
		
		int nPage = 1;
		
		if(request.getParameter("nPage")!=null) { 
			nPage=Integer.parseInt(request.getParameter("nPage"));
		}
		
		int recordPerPage = 3;
		int sno = ((nPage-1)*recordPerPage)+1;
		int eno = nPage * recordPerPage;
		
		Map map = new HashMap();
		
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("num", num);
		
		List<BreplyDTO> rlist = rdao.list(map);
		int total = rdao.total(num);
		
		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
		String col = request.getParameter("col");
		String word = request.getParameter("word");
		
		String paging =utility.bpaging(total, nowPage, recordPerPage, col, word, nPage, url, num);
		
		model.addAttribute("rlist", rlist);
		model.addAttribute("nPage", nPage);
		model.addAttribute("paging", paging);
		
		/*** 댓글 처리 end ***/
				
		
		return "/board/read";
	}
	
	@RequestMapping("/board/rupdate")
	public String rupdate(BreplyDTO dto, Model model, String nowPage, String col, String word, String nPage ) {
		
		if(rdao.update(dto)) {
			model.addAttribute("num",dto.getNum());
			model.addAttribute("nowPage",nowPage);
			model.addAttribute("col",col);
			model.addAttribute("word",word);
			model.addAttribute("nPage",nPage);
			return "redirect:/board/read";
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping("/board/rcreate")
	public String bcreate(BreplyDTO dto, Model model, String col, String word, String nowPage) {
		if(rdao.create(dto)) {
			model.addAttribute("num", dto.getNum());
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			
			return "redirect:/board/read";
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping(value="/board/rdelete")
	public String rdelete(BreplyDTO dto, int rnum, Model model, int nPage, String nowPage,
			String col, String word, int num) {
		int total = rdao.total(dto.getNum());
		int totalPage = (int) (Math.ceil((double) total / 3));

		if(rdao.delete(rnum)) {
			if (nPage != 1 && nPage == totalPage && total % 3 == 1) {
				nPage = nPage - 1;
			}
			model.addAttribute("nPage", nPage);
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("num", num);
			
			return "redirect:/board/read";
		} else {
			return "/error/error";
		}
	}

	
	@RequestMapping(value="/board/delete", method=RequestMethod.GET)
	public String delete(int num, Model model) {
		
		boolean flag = dao.checkRefnum(num);
		
		model.addAttribute("flag", flag);
		
		return "/board/delete";
	}
	@RequestMapping(value="/board/delete", method=RequestMethod.POST)
	public String delete(int num, String passwd, String oldfile,
			Model model, HttpServletRequest request) {
		
		String basePath = request.getRealPath("/board/storage");
		
		Map map = new HashMap();
		map.put("num", num);
		map.put("passwd", passwd);
		
		boolean pflag = dao.passwdCheck(map);
		
		model.addAttribute("col", request.getParameter("col"));
		model.addAttribute("word", request.getParameter("word"));
		model.addAttribute("nowPage", request.getParameter("nowPage"));
		
		if(pflag) {
			dao.delete(num);
			utility.deleteFile(basePath, oldfile);
			return "redirect:/board/list";
		}else {
			return "/error/passwdError";
		}
	}
	
	@RequestMapping(value="/board/reply", method=RequestMethod.GET)
	public String reply(int num, Model model) {
		BoardDTO dto = dao.replyRead(num);
		model.addAttribute("dto", dto);
		
		return "/board/reply";
	}
	@RequestMapping(value="/board/reply", method=RequestMethod.POST)
	public String reply(BoardDTO dto, Model model, HttpServletRequest request) {
		String basePath = request.getRealPath("/board/storage");
		dto.setFilename(utility.saveFileSpring(dto.getFilenameMF(), basePath));
		dto.setFilesize((int)dto.getFilenameMF().getSize());
		dto.setIp(request.getRemoteAddr());
		
		model.addAttribute("col", request.getParameter("col"));
		model.addAttribute("word", request.getParameter("word"));
		model.addAttribute("nowPage", request.getParameter("nowPage"));
		
		boolean flag = mgr.reply(dto);
		if(flag) {
			return "redirect:/board/list";
		} else {
			return "/error/error";
		}
	}
	
	@RequestMapping(value = "/board/update", method = RequestMethod.GET)
	public String update(int num, Model model) {
		BoardDTO dto = dao.read(num);

		model.addAttribute("dto", dto);

		return "/board/update";
	}

	@RequestMapping(value = "/board/update", method = RequestMethod.POST)
	public String update(BoardDTO dto, HttpServletRequest request, String oldfile, Model model) {

		String basePath = request.getRealPath("/board/storage");
		dto.setFilename(utility.saveFileSpring(dto.getFilenameMF(), basePath));
		dto.setFilesize((int) dto.getFilenameMF().getSize());

		Map map = new HashMap();
		map.put("num", dto.getNum());
		map.put("passwd", dto.getPasswd());

		boolean pflag = dao.passwdCheck(map);
		boolean flag = false;

		if (pflag) {
			flag = dao.update(dto);
		}
		
		model.addAttribute("col", request.getParameter("col"));
		model.addAttribute("word", request.getParameter("word"));
		model.addAttribute("nowPage", request.getParameter("nowPage"));
		
		String str = "/error/error";
		
		if (pflag) {
			if (flag) {
				if (dto.getFilesize() > 0) {
					utility.deleteFile(basePath, oldfile);
				}
				str = "redirect:/board/list";
			} else {
				if (dto.getFilesize() > 0) {
					utility.deleteFile(basePath, dto.getFilename());
				}
				str = "/error/error";
			}
		} else {
			if (dto.getFilesize() > 0) {
				utility.deleteFile(basePath, dto.getFilename());
			}
			str = "/error/passwdError";

		}

		return str;

	}
/*	@RequestMapping("/board/read")
	public String read(int num, Model model) {

		dao.viewCount(num);
		BoardDTO dto = dao.read(num);
		model.addAttribute("dto", dto);
		model.addAttribute("content", dto.getContent().replaceAll("\r\n", "<br>"));

		return "/board/read";
	}*/

	@RequestMapping(value = "/board/create", method = RequestMethod.GET)
	public String create() {
		return "/board/create";
	}

	@RequestMapping(value = "/board/create", method = RequestMethod.POST)
	public String create(BoardDTO dto, HttpServletRequest request) {

		String upDir = request.getRealPath("/board/storage");

		dto.setFilename(utility.saveFileSpring(dto.getFilenameMF(), upDir));
		dto.setFilesize((int) dto.getFilenameMF().getSize());
		dto.setIp(request.getRemoteAddr());

		boolean flag = dao.create(dto);

		if (flag) {
			return "redirect:/board/list";
		} else {
			return "/error/error";
		}
	}

}
