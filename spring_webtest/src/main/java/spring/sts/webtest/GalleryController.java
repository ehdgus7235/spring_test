package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spring.model.gallery.GalleryDAO;
import spring.model.gallery.GalleryDTO;
import spring.utility.webtest.utility;

@Controller
public class GalleryController {

	@Autowired
	private GalleryDAO dao;

	@RequestMapping("/gallery/list")
	public String list(HttpServletRequest request) {
		String col = utility.checkNull(request.getParameter("col"));
		String word = utility.checkNull(request.getParameter("word"));

		if (col.equals("total"))
			word = "";

		// paging관련
		int nowPage = 1;
		int recordPerPage = 5;

		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}

		// DB에서 가져올 레코드의 순번
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		int totalRecord = dao.total(map);
		List<GalleryDTO> list = dao.list(map);

		String paging = utility.paging3(totalRecord, nowPage, recordPerPage, col, word);

		request.setAttribute("list", list);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", paging);

		return "/gallery/list";
	}

	@RequestMapping(value = "/gallery/create", method = RequestMethod.GET)
	public String create() {

		return "/gallery/create";
	}

	@RequestMapping(value = "/gallery/create", method = RequestMethod.POST)
	public String create(GalleryDTO dto, MultipartFile fnameMF, HttpServletRequest request) {

		String upDir = request.getRealPath("/gallery/storage");

		int size = (int) fnameMF.getSize();
		String fname = null;
		if (size > 0) {
			fname = utility.saveFileSpring(fnameMF, upDir);
		} else {
			fname = "noimage.jpg";
		}

		dto.setFname(fname);

		boolean flag = dao.create(dto);

		request.setAttribute("flag", flag);

		if (!flag && fname.equals("")) {
			utility.deleteFile(upDir, fname);
		}

		return "/gallery/createProc";
	}

	@RequestMapping("/gallery/read")
	public String read(int gno, HttpServletRequest request) {
		dao.upViewCnt(gno);

		GalleryDTO dto = dao.read(gno);
		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");

		List list = dao.readList(gno);
		int[] lGno = (int[]) list.get(0);
		String[] lFname = (String[]) list.get(1);

		request.setAttribute("dto", dto);
		request.setAttribute("lGno", lGno);
		request.setAttribute("lFname", lFname);
		request.setAttribute("col", request.getParameter("col"));
		request.setAttribute("word", request.getParameter("word"));
		request.setAttribute("nowPage", request.getParameter("nowPage"));

		return "/gallery/read";
	}

	@RequestMapping(value = "/gallery/update", method = RequestMethod.GET)
	public String update(int gno, HttpServletRequest request) {

		GalleryDTO dto = dao.read(gno);

		request.setAttribute("dto", dto);

		return "/gallery/update";
	}

	@RequestMapping(value = "/gallery/update", method = RequestMethod.POST)
	public String update(@RequestParam Map<String, String> map, HttpServletRequest request, MultipartFile fnameMF) {

		String upDir = request.getRealPath("/gallery/storage");

		int gno = Integer.parseInt(map.get("gno"));
		String oldfile = (String) map.get("oldfile");
		String fname = null;
		if (fnameMF.getSize() > 0) {
			if (oldfile != null) {
				utility.deleteFile(upDir, oldfile);
			}
			fname = utility.saveFileSpring(fnameMF, upDir);
		} else {
			fname = oldfile;
		}
		
		map.put("fname", fname);

		boolean gflag = dao.passwdCheck(map);
		boolean flag = dao.updateInfo(map);

		request.setAttribute("gno", gno);
		request.setAttribute("flag", flag);
		request.setAttribute("gflag", gflag);
		request.setAttribute("col", (String) map.get("col"));
		request.setAttribute("word", (String) map.get("word"));
		request.setAttribute("nowPage", (String) map.get("nowPage"));

		return "/gallery/updateProc";
	}

	@RequestMapping(value = "/gallery/updatePasswd", method = RequestMethod.GET)
	public String updatePasswd(int gno, HttpServletRequest request) {

		request.setAttribute("gno", gno);

		return "/gallery/updatePasswd";
	}

	@RequestMapping(value = "/gallery/updatePasswd", method = RequestMethod.POST)
	public String updatePasswd(@RequestParam Map<String, String> map, HttpServletRequest request) {

		int gno = Integer.parseInt(map.get("gno"));
		String pw = (String) map.get("pw");
		String newpw = (String) map.get("newpw");
		
		boolean gflag = dao.passwdCheck(map);
		boolean flag = false;
		if (gflag)
			flag = dao.updatePasswd(map);

		request.setAttribute("gno", gno);
		request.setAttribute("flag", flag);
		request.setAttribute("gflag", gflag);
		request.setAttribute("col", request.getParameter("col"));
		request.setAttribute("word", request.getParameter("word"));
		request.setAttribute("nowPage", request.getParameter("nowPage"));

		return "/gallery/updatePasswdProc";
	}

	@RequestMapping(value = "/gallery/delete", method = RequestMethod.GET)
	public String delete(int gno, HttpServletRequest request) {

		request.setAttribute("gno", gno);
		request.setAttribute("oldfile", request.getParameter("oldfile"));

		return "/gallery/delete";
	}

	@RequestMapping(value = "/gallery/delete", method = RequestMethod.POST)
	public String delete(@RequestParam Map<String, String> map, Model model, HttpServletRequest request) {

		int gno = Integer.parseInt(map.get("gno"));
		String oldfile = (String)map.get("oldfile");

		boolean pflag = dao.passwdCheck(map);

		String upDir = request.getRealPath("/gallery/storage");

		if (pflag) {
			if (dao.delete(gno)) {
				utility.deleteFile(upDir, oldfile);

				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));
				model.addAttribute("nowPage", request.getParameter("nowPage"));
				
				return "redirect:/gallery/list";
			}else {
				return "/error/error";
			}
		}else {
			return "/error/error";
		}
	}
}
