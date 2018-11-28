package spring.model.team;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class TeamTest {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("daoTest.xml");

		BeanFactory factory = new XmlBeanFactory(resource);

		TeamDAO dao = (TeamDAO) factory.getBean("Teamdao");

		list(dao);
		//read(dao);
		//total(dao);
		//create(dao);
		//update(dao);
		//reply(dao);
		//checkRefnum(dao);
		//delete(dao);
	}

	private static void delete(TeamDAO dao) {
		if(dao.delete(18))
			p("삭제");
		else
			p("실패");
	}

	private static void checkRefnum(TeamDAO dao) {
		if(dao.checkRefnum(1)) {
			p("답변 있어 삭제 불가");
		}else {
			p("삭제 가능");
		}
	}

	private static void reply(TeamDAO dao) {
		TeamDTO dto = dao.replyRead(16);
		dto.setName("갓갓갓2");
		dto.setGender("여자");
		dto.setPhone("1234");
		dto.setHobby("독서");
		dto.setSkills("Java");
		
		Map map = new HashMap();
		map.put("grpno", dto.getGrpno());
		map.put("ansnum",  dto.getAnsnum());
		
		dao.increaseAnsnum(map);
		if(dao.replyCreate(dto))
			p("성공");
		else
			p("실패");
	}

	private static void update(TeamDAO dao) {
		TeamDTO dto = dao.read(3);
		dto.setHobby("기술서적읽기");
		if(dao.update(dto))
			p("성공");
		else
			p("실패");
	}

	private static void create(TeamDAO dao) {
		TeamDTO dto = new TeamDTO();
		
		dto.setName("갓갓갓");
		dto.setGender("남자");
		dto.setPhone("1234");
		dto.setHobby("독서");
		dto.setSkills("Java");
		dto.setZipcode("1234");
		dto.setAddress("서울시 강서구");
		dto.setAddress2("화곡동");
		
		if(dao.create(dto)) {
			p("성공");
		}else {
			p("실패");
		}
	}

	private static void total(TeamDAO dao) {
		Map map = new HashMap();
		map.put("col", "title");
		map.put("word",  "제");
		
		int total = dao.total(map);
		
		p("전체레코드 수:"+total);
	}
	
	private static void read(TeamDAO dao) {
		TeamDTO dto = dao.read(1);
		p(dto);
	}

	private static void list(TeamDAO dao) {
		Map map = new HashMap();
		String col = "name";
		String word = "";
		int sno = 1;
		int eno = 5;
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		

		List<TeamDTO> list = dao.list(map);
		Iterator<TeamDTO> iter = list.iterator();
		while(iter.hasNext()) {
			TeamDTO dto = iter.next();
			p(dto);
		}
	}

	private static void p(TeamDTO dto) {
		System.out.println("--------------------------");
		System.out.println("번호: " + dto.getNo());
		System.out.println("제목: " + dto.getName());
		System.out.println("성별: " + dto.getGender());
		System.out.println("기술: " + dto.getSkills());
		System.out.println("전화번호: " + dto.getPhone());
		System.out.println("--------------------------");
	}

	private static void p(String string) {
		System.out.println(string);
	}
}
