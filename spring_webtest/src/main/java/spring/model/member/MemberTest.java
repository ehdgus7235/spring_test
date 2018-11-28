package spring.model.member;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MemberTest {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("daoTest.xml");

		BeanFactory factory = new XmlBeanFactory(resource);

		MemberDAO dao = (MemberDAO) factory.getBean("Memberdao");

		//list(dao);
		//read(dao);
		//total(dao);
		//create(dao);
		//update(dao);
		delete(dao);
	}

	private static void delete(MemberDAO dao) {
		if(dao.delete("god"))
			p("삭제");
		else
			p("실패");
	}

	private static void update(MemberDAO dao) {
		MemberDTO dto = dao.read("god");
		dto.setTel("12345678");
		if(dao.update(dto))
			p("성공");
		else
			p("실패");
	}

	private static void create(MemberDAO dao) {
		MemberDTO dto = new MemberDTO();
		
		dto.setId("god");
		dto.setMname("갓갓갓");
		dto.setPasswd("1234");
		dto.setEmail("got@email.com");
		dto.setJob("A10");
		dto.setFname("test.txt");
		dto.setGrade("H");
		
		if(dao.create(dto)) {
			p("성공");
		}else {
			p("실패");
		}
	}

	private static void total(MemberDAO dao) {
		Map map = new HashMap();
		map.put("col", "id");
		map.put("word",  "user");
		
		int total = dao.total(map);
		
		p("전체레코드 수:"+total);
	}
	
	private static void read(MemberDAO dao) {
		MemberDTO dto = dao.read("god");
		p(dto);
	}

	private static void list(MemberDAO dao) {
		Map map = new HashMap();
		String col = "id";
		String word = "";
		int sno = 1;
		int eno = 5;
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		

		List<MemberDTO> list = dao.list(map);
		Iterator<MemberDTO> iter = list.iterator();
		while(iter.hasNext()) {
			MemberDTO dto = iter.next();
			p(dto);
		}
	}

	private static void p(MemberDTO dto) {
		System.out.println("--------------------------");
		System.out.println("아이디: " + dto.getId());
		System.out.println("이름: " + dto.getMname());
		System.out.println("전화번호: " + dto.getTel());
		System.out.println("이메일: " + dto.getEmail());
		System.out.println("우편번호: " + dto.getZipcode());
		System.out.println("주소: " + dto.getAddress1() + " " + dto.getAddress2());
		System.out.println("사진: " + dto.getFname());
		System.out.println("--------------------------");
	}

	private static void p(String string) {
		System.out.println(string);
	}
}
