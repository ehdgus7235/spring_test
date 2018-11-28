package spring.model.board;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public class BreplyDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	
	public int total(int num) {
		
		return mybatis.selectOne("breply.total", num);
	}
	
	public List<BreplyDTO> list(Map map) {
		
		return mybatis.selectList("breply.list", map);
	}
	
	public BreplyDTO read(int num) {
		
		return mybatis.selectOne("breply.read", num);
	} 
	
	@RequestMapping("/board/delete")
	public boolean delete(int rnum) {
		boolean flag = false;
		int cnt = mybatis.delete("breply.delete", rnum);
		if(cnt>0) flag = true;
		return flag;
	}
	
	@RequestMapping("/board/create")
	public boolean create(BreplyDTO dto) {
		boolean flag = false;
		
		int cnt = mybatis.insert("breply.create", dto);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}
	
	@RequestMapping("/board/update")
	public boolean update(BreplyDTO dto) {
		boolean flag = false;
		
		int cnt = mybatis.update("breply.update",dto);
		
		if (cnt>0) flag = true;
		
		return flag;
	}

}
