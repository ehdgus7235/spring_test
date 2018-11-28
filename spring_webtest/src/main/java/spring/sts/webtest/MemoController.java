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

import spring.model.memo.MemoDAO;
import spring.model.memo.MemoDTO;
import spring.model.memo.MemoService;
import spring.utility.webtest.utility;

@Controller
public class MemoController {

	@Autowired
	private MemoDAO dao;
	
	@Autowired
	private MemoService ms;
	
	@RequestMapping("/memo/list")
	public String list(HttpServletRequest request) {
		String col = utility.checkNull(request.getParameter("col"));
		String word = utility.checkNull(request.getParameter("word"));
		
		if(col.equals("total"))
			word = "";
		
		int nowPage=1;
		int recordPerPage=5;
		
		if(request.getParameter("nowPage")!=null){
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		
		int sno = ((nowPage-1)*recordPerPage)+1;
		int eno = nowPage*recordPerPage;
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		List<MemoDTO> list = dao.list(map);
		
		int totalRecord = dao.total(map);
		
		String paging = utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		
		return "/memo/list";
	}
	
	@RequestMapping(value="/memo/create", method=RequestMethod.GET)
	public String create() {
		
		return "/memo/create";
	}

	@RequestMapping(value="/memo/create", method=RequestMethod.POST)
	public String create(MemoDTO dto) {
		if(dao.create(dto)) {
			return "redirect:/memo/list";
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping(value="/memo/read")
	public String read(int memono, HttpServletRequest request) {
		dao.updateViewcnt(memono);

		MemoDTO dto = dao.read(memono);
		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");		
		
		request.setAttribute("dto", dto);
		request.setAttribute("col", request.getParameter("col"));
		request.setAttribute("word", request.getParameter("word"));
		request.setAttribute("nowPage", request.getParameter("nowPage"));
		
		return "/memo/read";
	}
	
	@RequestMapping(value="/memo/update", method=RequestMethod.GET)
	public String update(int memono, HttpServletRequest request) {
		MemoDTO dto = dao.read(memono);
		
		request.setAttribute("dto", dto);
		
		return "/memo/update";
	}
	
	@RequestMapping(value="/memo/update", method=RequestMethod.POST)
	public String update(MemoDTO dto, HttpServletRequest request, Model model) {
		dto.setMemono(Integer.parseInt(request.getParameter("memono")));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));

		boolean flag = dao.update(dto);
		if(flag) {
			model.addAttribute("flag", flag);
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			return "redirect:/memo/list";
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping(value="/memo/reply", method=RequestMethod.GET)
	public String reply(int memono, HttpServletRequest request) {
		MemoDTO dto = dao.replyRead(memono);
		
		request.setAttribute("dto", dto);
		return "/memo/reply";
	}

	@RequestMapping(value="/memo/reply", method=RequestMethod.POST)
	public String reply(MemoDTO dto, HttpServletRequest request, Model model) {
		dto.setMemono(Integer.parseInt(request.getParameter("memono")));
		dto.setGrpno(Integer.parseInt(request.getParameter("grpno")));
		dto.setIndent(Integer.parseInt(request.getParameter("indent")));
		dto.setAnsnum(Integer.parseInt(request.getParameter("ansnum")));
		
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));

		boolean flag = ms.reply(dto);
		if(flag) {
			model.addAttribute("flag", flag);
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("nowPage", request.getParameter("nowPage"));			
			return "redirect:/memo/list";
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping(value="/memo/delete", method=RequestMethod.GET)
	public String delete(int memono, HttpServletRequest request) {
		boolean flag = dao.checkRefnum(memono);
		
		request.setAttribute("flag", flag);
		return "/memo/delete";
	}

	@RequestMapping(value="/memo/delete", method=RequestMethod.POST)
	public String delete(int memono, HttpServletRequest request, Model model) {
		
		boolean flag = dao.delete(memono);
		if(flag) {
			model.addAttribute("flag", flag);
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("nowPage", request.getParameter("nowPage"));			
			return "redirect:/memo/list";
		}else {
			return "/error/error";
		}
	}
}
