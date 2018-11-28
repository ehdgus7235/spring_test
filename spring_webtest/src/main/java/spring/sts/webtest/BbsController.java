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

import spring.model.bbs.BbsDAO;
import spring.model.bbs.BbsDTO;
import spring.model.bbs.BbsService;
import spring.model.bbs.ReplyDAO;
import spring.model.bbs.ReplyDTO;
import spring.utility.webtest.utility;

@Controller
public class BbsController {
	
	@Autowired
	private BbsDAO dao;
	
	@Autowired
	private ReplyDAO rdao;
	
	@Autowired
	private BbsService mgr;
	
	@RequestMapping("/bbs/list")
	public String list(HttpServletRequest request, Model model) {
		
		String col = utility.checkNull(request.getParameter("col"));
		String word = utility.checkNull(request.getParameter("word"));

		if (col.equals("total")) {
			word = "";
		}

		int nowPage = 1;
		int recordPerPage = 5;

		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage")); //위험함! numberformatException 가능성	
		}

		int sno = ((nowPage - 1) * recordPerPage) + 1; //공식임 ㅎ
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		List<BbsDTO> list = dao.list(map);
		//전체 레코드 개수(col, word)
		int totalRecord = dao.total(map);

		String paging = utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("rdao", rdao);
		
		return "/bbs/list";
	}
	
	@RequestMapping(value="/bbs/create", method=RequestMethod.GET)
	public String create() {
		
		return "/bbs/create";
	}
	
	@RequestMapping(value="/bbs/create", method=RequestMethod.POST)
	public String create(BbsDTO dto, HttpServletRequest request) {
		
		int filesize = (int)dto.getFilenameMF().getSize();
		String upDir = request.getRealPath("/bbs/storage");
		String filename = "";
		
		if(filesize>0)
			filename = utility.saveFileSpring(dto.getFilenameMF(), upDir);
		dto.setFilesize(filesize);
		dto.setFilename(filename);
		
		boolean flag = dao.create(dto);
		
		if(flag) {
			return "redirect:/bbs/list";
		}else {
			if(!filename.equals(""))
				utility.deleteFile(upDir, filename);
			
			return "/error/error";
		}
	}
	
	@RequestMapping("/bbs/read")
	public String read(int bbsno, Model model, HttpServletRequest request) {
		dao.updateviewcnt(bbsno);
		
		BbsDTO dto = dao.read(bbsno);
		
		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");
		
		dto.setContent(content);
		
		model.addAttribute("dto", dto);
		
		/**댓글 처리**/
		String url = "read"; //댓글 페이지에 매개변수
		int nPage = 1;
		if(request.getParameter("nPage") != null) {
			nPage = Integer.parseInt(request.getParameter("nPage"));
		}
		
		int recordPerPage = 3;
		int sno = ((nPage-1) * recordPerPage) + 1;
		int eno = nPage * recordPerPage;
		
		Map map = new HashMap();
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("bbsno", bbsno);
		
		List<ReplyDTO> rlist = rdao.list(map);
		int total = rdao.total(bbsno);
		
		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
		String col = request.getParameter("col");
		String word = request.getParameter("word");
		String paging = utility.rpaging(total, nowPage, recordPerPage, col, word, nPage, url, bbsno);
		
		model.addAttribute("rlist", rlist);
		model.addAttribute("nPage", nPage);
		model.addAttribute("paging", paging);
		
		/**댓글 처리**/
		
		return "/bbs/read";
	}
	
	@RequestMapping(value="/bbs/update", method=RequestMethod.GET)
	public String update(int bbsno, Model model) {
		BbsDTO dto = dao.read(bbsno);
		
		model.addAttribute("dto", dto);
		
		return "/bbs/update";
	}

	@RequestMapping(value="/bbs/update", method=RequestMethod.POST)
	public String update(BbsDTO dto, String oldfile, HttpServletRequest request, Model model) {
		String upDir = request.getRealPath("/bbs/storage");
		
		Map map = new HashMap();
		map.put("bbsno", dto.getBbsno());
		map.put("passwd", dto.getPasswd());
		
		int filesize = (int)dto.getFilenameMF().getSize();
		String filename = "";
		
		boolean pflag = dao.passCheck(map);
		
		if (pflag) { 
			if(filesize>0){
				if(oldfile!=null)
					utility.deleteFile(upDir, oldfile);
				filename = utility.saveFileSpring(dto.getFilenameMF(), upDir);
			}
			
			dto.setFilename(filename);
			dto.setFilesize(filesize);

			
			if(dao.update(dto)) {
				model.addAttribute("col", request.getAttribute("col"));
				model.addAttribute("word", request.getAttribute("word"));
				model.addAttribute("nowPage", request.getAttribute("nowPage"));
				return "redirect:/bbs/list";
			}else {
				return "/error/error";
			}
		}else {
			return "/error/passwdError";
		}
	}
	
	@RequestMapping(value="/bbs/reply", method=RequestMethod.GET)
	public String reply(int bbsno, Model model) {
		BbsDTO dto = dao.replyRead(bbsno);
		
		model.addAttribute("dto", dto);
		
		return "/bbs/reply";
	}
	
	@RequestMapping(value="/bbs/reply", method=RequestMethod.POST)
	public String reply(BbsDTO dto, HttpServletRequest request) {
		String upDir = request.getRealPath("/bbs/storage");
		
		int filesize = (int)dto.getFilenameMF().getSize();
		String filename = "";
		if(filesize>0)
			filename = utility.saveFileSpring(dto.getFilenameMF(), upDir);
		
		dto.setFilename(filename);
		dto.setFilesize(filesize);
		
		try {
			mgr.reply(dto);
			
			return "redirect:/bbs/list";
		} catch (Exception e) {
			e.printStackTrace();
			
			utility.deleteFile(upDir, filename);
			
			return "/error/error";
		}
	}

	@RequestMapping(value="/bbs/delete", method=RequestMethod.GET)
	public String delete(int bbsno, HttpServletRequest request) {
		boolean flag = dao.checkRefnum(bbsno);
		
		request.setAttribute("flag", flag);
		
		return "/bbs/delete";
	}
	
	@RequestMapping(value="/bbs/delete", method=RequestMethod.POST)
	public String delete(int bbsno, String passwd, String oldfile, HttpServletRequest request, Model model) {
		String upDir = request.getRealPath("/bbs/storage");
		
		Map map = new HashMap();
		map.put("bbsno", bbsno);
		map.put("passwd", passwd);
		
		boolean pflag = dao.passCheck(map);
		if(pflag) {
			try {
				mgr.delete(bbsno);
				utility.deleteFile(upDir, oldfile);
				model.addAttribute("col", request.getAttribute("col"));
				model.addAttribute("word", request.getAttribute("word"));
				model.addAttribute("nowPage", request.getAttribute("nowPage"));
				return "redirect:/bbs/list";
			}catch(Exception e) {
				e.printStackTrace();
				return "/error/error";
			}
		}else {
			return "/error/passwdError";
		}
	}
	
	@RequestMapping("/bbs/rcreate")
	public String rcreate(ReplyDTO dto, Model model, String nowPage, String col, String word) {
		if(rdao.create(dto)) {
			model.addAttribute("bbsno", dto.getBbsno());
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			
			return "redirect:/bbs/read";
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping("/bbs/rupdate")
	public String rupdate(ReplyDTO dto, Model model, String nowPage, String col, String word, String nPage) {
		if(rdao.update(dto)) {
			model.addAttribute("bbsno", dto.getBbsno());
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("nPage", nPage);
			
			return "redirect:/bbs/read";
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping("/bbs/rdelete")
	public String rdelete(int rnum, int bbsno, String nowPage, String col, String word, int nPage, Model model) {
		
		int total = rdao.total(bbsno);
		int totalPage = (int)Math.ceil((double)total/3);
		
		if(rdao.delete(rnum)) {
			if(nPage!=1 && nPage==totalPage && total%3==1)
				nPage -= 1;
			model.addAttribute("bbsno", bbsno);
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("nPage", nPage);
			
			return "redirect:/bbs/read";
		}else {
			return "/error/error";
		}
	}
}
