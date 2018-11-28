package spring.model.bbs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ReplyDAOTest {
	
	private static ReplyDAO dao;
	private static BeanFactory beans;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Resource resource = new ClassPathResource("daoTest.xml");
		beans = new XmlBeanFactory(resource);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dao = (ReplyDAO)beans.getBean("Replydao");
	}


	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Ignore
	public void testCreate() {
		ReplyDTO dto = new ReplyDTO();
		
		dto.setId("user1");
		dto.setContent("의견3");
		dto.setBbsno(91);
		
		assertTrue(dao.create(dto));
	}

	@Test
	@Ignore
	public void testRead() {
		ReplyDTO dto = dao.read(15);
		assertNotNull(dto);
	}

	@Test
	@Ignore
	public void testUpdate() {
		int rnum = 15;
		ReplyDTO dto = dao.read(rnum);
		dto.setContent("변경");
		
		assertTrue(dao.update(dto));
	}

	@Test
	public void testDelete() {
		int rnum = 16;
		assertTrue(dao.delete(rnum));
	}

	@Test
	public void testBdelete() {
		int bbsno = 88;
		assertTrue(dao.bdelete(bbsno));
	}

	@Test
	@Ignore
	public void testTotal() {
		int bbsno = 91;
		int total = dao.total(bbsno);
		assertEquals(total, 13);
	}

	@Test
	@Ignore
	public void testList() {
		Map map = new HashMap();
		int sno = 1;
		int eno = 5;
		int bbsno = 91;
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("bbsno", bbsno);
		List<ReplyDTO> list = dao.list(map);
		
		assertEquals(list.size(), 5);
	}

	@Test
	@Ignore
	public void testRcount() {
		int bbsno = 91;
		int cnt = dao.rcount(bbsno);
		assertEquals(cnt, 13);
	}

}
