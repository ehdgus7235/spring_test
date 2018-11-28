package spring.model.memo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MemoTest {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("daoTest.xml");

		BeanFactory factory = new XmlBeanFactory(resource);

		MemoDAO dao = (MemoDAO) factory.getBean("Memodao");

		list(dao);
		//read(dao);
		//total(dao);
		//create(dao);
		//update(dao);
		//reply(dao);
		//checkRefnum(dao);
		//delete(dao);
	}

	private static void delete(MemoDAO dao) {
		if(dao.delete(502))
			p("삭제");
		else
			p("실패");
	}

	private static void checkRefnum(MemoDAO dao) {
		if(dao.checkRefnum(501)) {
			p("답변 있어 삭제 불가");
		}else {
			p("삭제 가능");
		}
	}

	private static void reply(MemoDAO dao) {
		MemoDTO dto = dao.replyRead(501);
		dto.setTitle("갓갓갓2");
		dto.setContent("갓갓갓2");
		
		Map map = new HashMap();
		map.put("grpno", dto.getGrpno());
		map.put("ansnum",  dto.getAnsnum());
		
		dao.upAnsnum(map);
		if(dao.replyCreate(dto))
			p("성공");
		else
			p("실패");
	}

	private static void update(MemoDAO dao) {
		MemoDTO dto = dao.read(401);
		dto.setTitle("갓갓갓");
		if(dao.update(dto))
			p("성공");
		else
			p("실패");
	}

	private static void create(MemoDAO dao) {
		MemoDTO dto = new MemoDTO();
		
		dto.setTitle("갓갓");
		dto.setContent("갓갓");
		
		if(dao.create(dto)) {
			p("성공");
		}else {
			p("실패");
		}
	}

	private static void total(MemoDAO dao) {
		Map map = new HashMap();
		map.put("col", "title");
		map.put("word",  "제");
		
		int total = dao.total(map);
		
		p("전체레코드 수:"+total);
	}
	
	private static void read(MemoDAO dao) {
		MemoDTO dto = dao.read(1);
		p(dto);
	}

	private static void list(MemoDAO dao) {
		Map map = new HashMap();
		String col = "title";
		String word = "";
		int sno = 1;
		int eno = 5;
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		

		List<MemoDTO> list = dao.list(map);
		Iterator<MemoDTO> iter = list.iterator();
		while(iter.hasNext()) {
			MemoDTO dto = iter.next();
			p(dto);
		}
	}

	private static void p(MemoDTO dto) {
		System.out.println("--------------------------");
		System.out.println("번호: " + dto.getMemono());
		System.out.println("제목: " + dto.getTitle());
		System.out.println("내용: " + dto.getContent());
		System.out.println("조회수: " + dto.getViewcnt());
		System.out.println("등록 날짜: " + dto.getWdate());
		System.out.println("--------------------------");
	}

	private static void p(String string) {
		System.out.println(string);
	}
}
