package spring.model.bbs;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	
	public boolean create(ReplyDTO dto) {
		boolean flag = false;
		
		int cnt = mybatis.insert("reply.create", dto);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}
	
	public ReplyDTO read(int rnum) {
		return mybatis.selectOne("reply.read", rnum);
	}
	
	public boolean update(ReplyDTO dto) {
		boolean flag = false;
		
		int cnt = mybatis.update("reply.update", dto);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}
	
	public boolean delete(int rnum) {
		boolean flag = false;
		
		int cnt = mybatis.delete("reply.delete", rnum);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}
	
	public boolean bdelete(int bbsno) {
		boolean flag = false;
		
		int cnt = mybatis.delete("reply.bdelete", bbsno);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}
	
	public int total(int bbsno) {
		return mybatis.selectOne("reply.total", bbsno);
	}
	
	public List<ReplyDTO> list(Map map) {
		return mybatis.selectList("reply.list", map);
	}
	
	public int rcount(int bbsno){
	    return mybatis.selectOne("reply.rcount", bbsno);
	}
}
