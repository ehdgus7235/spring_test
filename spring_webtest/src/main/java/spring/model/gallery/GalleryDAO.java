package spring.model.gallery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GalleryDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	
	public boolean delete(int gno) {
		boolean flag = false;
		
		int cnt = mybatis.delete("gallery.delete", gno);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}
	
	public GalleryDTO read(int gno) {

		return mybatis.selectOne("gallery.read", gno);
	}

	public void upViewCnt(int gno) {
		mybatis.update("gallery.upViewCnt", gno);
	}

	public List readList(int gno) {
		List list = new ArrayList();
		
		Map rs = mybatis.selectOne("gallery.readList", gno);
		
		int[] lGno = { ((BigDecimal)rs.get("PRE_GNO2")).intValue(), ((BigDecimal)rs.get("PRE_GNO1")).intValue(), ((BigDecimal)rs.get("GNO")).intValue(), ((BigDecimal)rs.get("NEX_GNO1")).intValue(), ((BigDecimal)rs.get("NEX_GNO2")).intValue() };
		String[] lFname = { (String)rs.get("PRE_FNAME2"), (String)rs.get("PRE_FNAME1"), (String)rs.get("FNAME"), (String)rs.get("NEX_FNAME1"), (String)rs.get("NEX_FNAME2"), };
		list.add(lGno);
		list.add(lFname);

		return list;
	}

	public boolean passwdCheck(Map<String, String> map) {
		boolean flag = false;

		int cnt = mybatis.selectOne("gallery.passwdCheck", map);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}


	public boolean updatePasswd(Map<String, String> map) {
		boolean flag = false;

		int cnt = mybatis.update("gallery.updatePasswd", map);
		if(cnt > 0)
			flag = true;

		return flag;
	}


	public boolean updateInfo(Map<String, String> map) {
		boolean flag = false;

		int cnt = mybatis.update("gallery.updateInfo", map);
		if(cnt > 0)
			flag = true;

		return flag;
	}
	
	public boolean create(GalleryDTO dto) {
		boolean flag = false;
		
		int cnt = mybatis.insert("gallery.create", dto);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}
		
	public List<GalleryDTO> list(Map map) {
		
		return mybatis.selectList("gallery.list", map);
	}
	
	public int total(Map map) {
		
		return mybatis.selectOne("gallery.total", map);
	}
}
	
