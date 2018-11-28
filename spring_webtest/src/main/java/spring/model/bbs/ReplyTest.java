package spring.model.bbs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ReplyTest {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("daoTest.xml");

		BeanFactory factory = new XmlBeanFactory(resource);

		ReplyDAO dao = (ReplyDAO) factory.getBean("Replydao");

		list(dao);
		//read(dao);
		//total(dao);
		//create(dao);
		//update(dao);
		//delete(dao);
		//bdelete(dao);
	}

	private static void bdelete(ReplyDAO dao) {
		if(dao.bdelete(91))
			p("삭제");
		else
			p("실패");
	}

	private static void delete(ReplyDAO dao) {
		if(dao.delete(3))
			p("삭제");
		else
			p("실패");
	}

	private static void update(ReplyDAO dao) {
		ReplyDTO dto = dao.read(2);
		dto.setContent("갓갓갓");
		if(dao.update(dto))
			p("성공");
		else
			p("실패");
	}

	private static void create(ReplyDAO dao) {
		ReplyDTO dto = new ReplyDTO();
		
		dto.setId("user1");
		dto.setContent("의견3");
		dto.setBbsno(91);
		
		if(dao.create(dto)) {
			p("성공");
		}else {
			p("실패");
		}
	}

	private static void total(ReplyDAO dao) {
		int total = dao.total(91);
		
		p("전체레코드 수:"+total);
	}

	private static void read(ReplyDAO dao) {
		ReplyDTO dto = dao.read(1);
		p(dto);
	}

	private static void list(ReplyDAO dao) {
		Map map = new HashMap();
		int sno = 1;
		int eno = 5;
		int bbsno = 91;
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("bbsno", bbsno);
		

		List<ReplyDTO> list = dao.list(map);
		Iterator<ReplyDTO> iter = list.iterator();
		while(iter.hasNext()) {
			ReplyDTO dto = iter.next();
			p(dto);
		}
	}

	private static void p(ReplyDTO dto) {
		System.out.println("--------------------------");
		System.out.println("번호: " + dto.getRnum());
		System.out.println("ID: " + dto.getId());
		System.out.println("내용: " + dto.getContent());
		System.out.println("등록 날짜: " + dto.getRegdate());
		System.out.println("부모글 번호: " + dto.getBbsno());
		System.out.println("--------------------------");
	}

	private static void p(String string) {
		System.out.println(string);
	}
}
