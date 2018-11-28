package spring.model.board;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import spring.model.bbs.ReplyDAO;

public class BreplyTest {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("daoTest.xml");

		BeanFactory factory = new XmlBeanFactory(resource);

		BreplyDAO dao = (BreplyDAO) factory.getBean("Breplydao");
		
		create(dao);
	}

	private static void create(BreplyDAO dao) {
		BreplyDTO dto = new BreplyDTO();
		
		dto.setContent("인터넷은 우리가 함께 만들어가는 소중한 공간입니다. 댓글 작성 시 타인에 대한 배려와 책임을 담아주세요.");
		dto.setId("user1");
		dto.setNum(21);
		
		if(dao.create(dto)) {
			p("성공");
		}else {
			p("실패");
		}
	}

	private static void p(String string) {
		System.out.println(string);
	}

}
