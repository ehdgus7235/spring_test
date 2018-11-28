package spring.model.bbs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class BbsTest {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("daoTest.xml");

		BeanFactory factory = new XmlBeanFactory(resource);

		BbsDAO dao = (BbsDAO) factory.getBean("Bbsdao");

		list(dao);
		//read(dao);
		//total(dao);
		//create(dao);
		//passCheck(dao);
		//update(dao);
		//replyRead(dao);
		//reply(dao);
		//checkRefnum(dao);
		//delete(dao);
	}

	private static void passCheck(BbsDAO dao) {
		Map map = new HashMap();
		map.put("bbsno", 1);
		map.put("passwd",  123411);
		if(dao.passCheck(map))
			p("성공");
		else
			p("실패");
	}

	private static void delete(BbsDAO dao) {
		if(dao.delete(88))
			p("삭제");
		else
			p("실패");
	}

	private static void checkRefnum(BbsDAO dao) {
		if(dao.checkRefnum(88)) {
			p("답변 있어 삭제 불가");
		}else {
			p("삭제 가능");
		}
	}

	private static void reply(BbsDAO dao) {
		BbsDTO dto = dao.replyRead(3);
		dto.setWname("갓갓갓2");
		dto.setTitle("갓갓갓2");
		dto.setContent("갓갓갓2");
		dto.setPasswd("1234");
		dto.setFilename("test2.txt");
		dto.setFilesize(100);
		
		Map map = new HashMap();
		map.put("grpno", dto.getGrpno());
		map.put("ansnum",  dto.getAnsnum());
		
		dao.upAnsnum(map);
		if(dao.replyCreate(dto))
			p("성공");
		else
			p("실패");
	}

	private static void replyRead(BbsDAO dao) {
		BbsDTO dto = dao.replyRead(5);
		p("번호:"+dto.getBbsno());
		p("부모글번호:"+dto.getGrpno());
		p("indent:"+dto.getIndent());
		p("Ansnum:"+dto.getAnsnum());
		p("제목:"+dto.getTitle());
	}

	private static void update(BbsDAO dao) {
		BbsDTO dto = dao.read(6);
		dto.setWname("갓갓갓");
		if(dao.update(dto))
			p("성공");
		else
			p("실패");
	}

	private static void create(BbsDAO dao) {
		BbsDTO dto = new BbsDTO();
		
		dto.setWname("갓갓");
		dto.setTitle("갓갓");
		dto.setContent("갓갓");
		dto.setPasswd("1234");
		//dto.setFilename("test.txt");
		//dto.setFilesize(100);
		
		if(dao.create(dto)) {
			p("성공");
		}else {
			p("실패");
		}
	}

	private static void total(BbsDAO dao) {
		Map map = new HashMap();
		map.put("col", "title");
		map.put("word",  "제");
		
		int total = dao.total(map);
		
		p("전체레코드 수:"+total);
	}

	private static void read(BbsDAO dao) {
		BbsDTO dto = dao.read(1);
		p(dto);
	}

	private static void list(BbsDAO dao) {
		Map map = new HashMap();
		String col = "name";
		String word = "동";
		int sno = 1;
		int eno = 5;
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		

		List<BbsDTO> list = dao.list(map);
		Iterator<BbsDTO> iter = list.iterator();
		while(iter.hasNext()) {
			BbsDTO dto = iter.next();
			p(dto);
		}
	}

	private static void p(BbsDTO dto) {
		System.out.println("--------------------------");
		System.out.println("번호: " + dto.getBbsno());
		System.out.println("이름: " + dto.getWname());
		System.out.println("제목: " + dto.getTitle());
		System.out.println("내용: " + dto.getContent());
		System.out.println("조회수: " + dto.getViewcnt());
		System.out.println("등록 날짜: " + dto.getWdate());
		System.out.println("파일 이름: " + dto.getFilename());
		System.out.println("파일 크기: " + dto.getFilesize());
		System.out.println("--------------------------");
	}

	private static void p(String string) {
		System.out.println(string);
	}
}
