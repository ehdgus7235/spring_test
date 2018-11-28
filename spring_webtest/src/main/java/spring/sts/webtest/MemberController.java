package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import spring.model.member.MemberDAO;
import spring.model.member.MemberDTO;
import spring.utility.webtest.utility;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDAO dao;
	
	@RequestMapping(value="/member/agree")
	public String agree() {
		
		return "/member/agree";
	}

	@RequestMapping(value="/member/create")
	public String create() {
		
		return "/member/create";
	}

	@RequestMapping(value="/member/createProc")
	public String create(MemberDTO dto, HttpServletRequest request, Model model) {
		
		String str = null;
		String url = "/member/pcreate";
		
		if(dao.duplicateId(dto.getId())){
			str="중복된 ID입니다.";
			model.addAttribute("str", str);
		}else if(dao.duplicateEmail(dto.getEmail())){
			str="중복된 email입니다.";
			model.addAttribute("str", str);
		}else {
			String upDir = request.getRealPath("/member/storage");
			int size = (int)dto.getFnameMF().getSize();
			String fname = null;
			if(size>0){
				fname = utility.saveFileSpring(dto.getFnameMF(), upDir);
			}else{
				fname = "member.jpg";
			}
			
			dto.setFname(fname);
			
			boolean flag = dao.create(dto);
			
			model.addAttribute("flag", flag);
			
			url = "/member/createProc";
		}
		
		return url;
	}
	
	@ResponseBody
	@RequestMapping(value="/member/idCheck", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public String idCheck(String id) {
		boolean flag = dao.duplicateId(id);
		String str = "";
		
		if(flag) {
			str = "중복";
		}else {
			str = "중복 아님";
		}
		
		return str;
	}
	
	@ResponseBody
	@RequestMapping(value="/member/emailCheck", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public String emailCheck(String email) {
		boolean flag = dao.duplicateEmail(email);
		String str = "";
		
		if(flag) {
			str = "중복";
		}else {
			str = "중복 아님";
		}
		
		return str;
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public String login(HttpServletRequest request) {
		/*----쿠키설정 내용시작----------------------------*/
		String c_id = ""; // ID 저장 여부를 저장하는 변수, Y
		String c_id_val = ""; // ID 값

		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];

				if (cookie.getName().equals("c_id")) {
					c_id = cookie.getValue(); // Y 
				} else if (cookie.getName().equals("c_id_val")) {
					c_id_val = cookie.getValue(); // user1... 
				}
			}
		}

		/*----쿠키설정 내용 끝----------------------------*/
		
		request.setAttribute("c_id", c_id);
		request.setAttribute("c_id_val", c_id_val);
		
		return "/member/login";
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(@RequestParam Map<String, String> map, String c_id, HttpSession session, Model model, HttpServletResponse response, HttpServletRequest request) {
		
		String id = (String)map.get("id");
		boolean flag = dao.loginCheck(map);
		
		String url = "/error/passwdError";
		
		if(flag){
			String grade = dao.getGrade(id);
			session.setAttribute("id", id);
			session.setAttribute("grade", grade);
			 // ---------------------------------------------- 
		    // Cookie 저장, Checkbox는 선택하지 않으면 null 임 
		    // ---------------------------------------------- 
		    Cookie cookie = null; 
		       
		    if (c_id != null){  // 처음에는 값이 없음으로 null 체크로 처리
		      cookie = new Cookie("c_id", "Y");    // 아이디 저장 여부 쿠키 
		      cookie.setMaxAge(120);               // 2 분 유지 
		      response.addCookie(cookie);          // 쿠키 기록 
		   
		      cookie = new Cookie("c_id_val", id); // 아이디 값 저장 쿠키  
		      cookie.setMaxAge(120);               // 2 분 유지 
		      response.addCookie(cookie);          // 쿠키 기록  
		         
		    }else{ 
		      cookie = new Cookie("c_id", "");     // 쿠키 삭제 
		      cookie.setMaxAge(0); 
		      response.addCookie(cookie); 
		         
		      cookie = new Cookie("c_id_val", ""); // 쿠키 삭제 
		      cookie.setMaxAge(0); 
		      response.addCookie(cookie); 
		    } 
		    // --------------------------------------------- 
		    url = "redirect:/";
		    /**댓글쓰기 페이지로 돌아가기위한 데이터**/
		    String rflag = request.getParameter("flag");
		    String nPage = request.getParameter("nPage");
		    String bbsno = request.getParameter("bbsno");
		    String num = request.getParameter("num");
		    String col = request.getParameter("col");
		    String word = request.getParameter("word");
		    String nowPage = request.getParameter("nowPage");
		    
		    /**댓글쓰기 페이지로 이동**/
		    if(rflag != null && !rflag.equals("")) {
		    	url = "redirect:"+rflag;
		    	model.addAttribute("bbsno", bbsno);
		    	model.addAttribute("num", num);
		    	model.addAttribute("nPage", nPage);
		    	model.addAttribute("col", col);
		    	model.addAttribute("word", word);
		    	model.addAttribute("nowPage", nowPage);
		    }
		}
		
		model.addAttribute("flag", flag);
		
		
		return url;
	}
	
	@RequestMapping("/member/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/member/read")
	public String read(String id, HttpSession session, Model model) {
		
		if(id==null) {
			id = (String)session.getAttribute("id");
		}
		MemberDTO dto = dao.read(id);
		
		model.addAttribute("dto", dto);
		
		return "/member/read";
	}
	
	@RequestMapping(value="/member/updatePasswd", method=RequestMethod.GET)
	public String updatePasswd(String id, HttpServletRequest request) {
		MemberDTO dto = dao.read(id);
		
		request.setAttribute("dto", dto);
		
		return "/member/updatePasswd";
	}
	
	@RequestMapping(value="/member/updatePasswd", method=RequestMethod.POST)
	public String updatePasswd(@RequestParam Map<String, String> map, Model model) {

		String currPasswd = (String)map.get("passwd");
		String newPasswd = (String)map.get("newPasswd");
		
		boolean mflag = dao.passwdCheck(map);
		boolean mflag2 = false;
		if(currPasswd.equals(newPasswd)) {
			mflag2 = true;//기존 비밀번호와 같은지 비교, true이면 변경불가 처리
		}
		boolean flag = false;
		
		if(mflag&&!mflag2) {
			flag = dao.updatePasswd(map);
		}
		
		model.addAttribute("flag", flag);
		model.addAttribute("mflag", mflag);
		model.addAttribute("mflag2", mflag2);
		model.addAttribute("id", (String)map.get("id"));
		
		return "/member/updatePasswdProc";
	}
	
	@RequestMapping("/member/idpwFind")
	public String idpwFind() {
		
		return "/member/idpwFind";
	}
	
	@ResponseBody
	@RequestMapping(value="/member/idFind", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public String idFind(@RequestParam Map<String, String> map) {
		
		String id = dao.getIdFind(map);
		String str = null;
		if(id!=null){
			str = "찾으시는 id는 "+id+"입니다.";
		}else{
			str = "잘못된 정보를 입력하셨습니다.";
		}
		
		return str;
	}

	@ResponseBody
	@RequestMapping(value="/member/pwFind", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public String pwFind(@RequestParam Map<String, String> map) {
		
		String pw = dao.getPwFind(map);
		String str = null;
		if(pw!=null){
			str = "찾으시는 비밀번호는 "+pw+"입니다.";
		}else{
			str = "잘못된 정보를 입력하셨습니다.";
		}
		
		return str;
	}
	
	@RequestMapping(value="/member/update", method=RequestMethod.GET)
	public String update(String id, Model model) {
		
		MemberDTO dto = dao.read(id);
		
		model.addAttribute("dto", dto);
		
		return "/member/update";
	}
	
	@RequestMapping(value="/member/update", method=RequestMethod.POST)
	public String update(MemberDTO dto, Model model, HttpSession session, HttpServletRequest request) {
		
		boolean flag = dao.update(dto);
		
		if(flag) {
			if(session.getAttribute("id") != null && session.getAttribute("grade").equals("A")) {
				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));
				model.addAttribute("nowPage", request.getParameter("nowPage"));
				return "redirect:/admin/list";
			}else {
				return "redirect:/";
			}
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping("/member/emailProc")
	public String emailProc(String email) {
		
		return "/member/email_form";
	}
	
	@RequestMapping(value="/member/updateFile", method=RequestMethod.GET)
	public String updateFile() {
		
		return "/member/updateFile";
	}

	@RequestMapping(value="/member/updateFile", method=RequestMethod.POST)
	public String updateFile(String id, String oldfile, MultipartFile fnameMF, HttpServletRequest request, Model model) {
		
		String upDir = request.getRealPath("/member/storage");
		String fname = null;
		if(fnameMF.getSize()>0){
			if(oldfile!=null && !oldfile.equals("member.jpg"))
				utility.deleteFile(upDir, oldfile);
			fname = utility.saveFileSpring(fnameMF, upDir);
		}
		Map map = new HashMap();
		map.put("id", id);
		map.put("fname", fname);
		
		boolean flag = dao.updateFile(map);
		
		if(flag) {
			model.addAttribute("id", id);
			model.addAttribute("flag", flag);
			
			return "redirect:/member/read";
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping(value="/member/delete", method=RequestMethod.GET)
	public String delete(String id, HttpSession session, Model model) {
		
		if(id==null) id = (String)session.getAttribute("id");
		
		model.addAttribute("id", id);
		
		return "/member/delete";
	}

	@RequestMapping(value="/member/delete", method=RequestMethod.POST)
	public String delete(String id, HttpSession session, HttpServletRequest request, Model model) {
		
		String upDir = request.getRealPath("/member/storage");
		
		String sid = (String)session.getAttribute("id");
		String grade = (String)session.getAttribute("grade");
		
		String oldfile = dao.getFname(id);
		String passwd = request.getParameter("passwd");
		Map map = new HashMap();
		map.put("id", id);
		map.put("passwd", passwd);

		boolean pflag = dao.passwdCheck(map);
		boolean flag = false;
		if (pflag) {
			flag = dao.delete(id);
			if (flag) {
				if (oldfile != null && !oldfile.equals("member.jpg"))
					utility.deleteFile(upDir, oldfile);
				if(sid!=null && !grade.equals("A")) {
					request.getSession().invalidate();
					return "redirect:/";
				}else {
					model.addAttribute("col", request.getParameter("col"));
					model.addAttribute("word", request.getParameter("word"));
					model.addAttribute("nowPage", request.getParameter("nowPage"));
					return "redirect:/admin/list";
				}
			}
			else {
				return "/error/error";
			}
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping("/admin/list")
	public String list(HttpServletRequest request) {
		
		//검색관련
		String col = utility.checkNull(request.getParameter("col"));
		String word = utility.checkNull(request.getParameter("word"));
		if(col.equals("total"))
			word = "";
		
		//페이징관련
		int nowPage = 1;
		int recordPerPage = 5;
		if(request.getParameter("nowPage")!=null)
			nowPage=Integer.parseInt(request.getParameter("nowPage"));
		
		//DB에 가져올 순번
		int sno = ((nowPage-1)*recordPerPage)+1;
		int eno = nowPage*recordPerPage;
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
		int total = dao.total(map);
		List<MemberDTO> list = dao.list(map);
		
		String paging = utility.paging3(total, nowPage, recordPerPage, col, word);
		
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		
		return "/admin/list";
	}
}
