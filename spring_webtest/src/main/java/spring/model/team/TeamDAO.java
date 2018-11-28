package spring.model.team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.db.webtest.DBClose;
import spring.db.webtest.DBOpen;
@Repository
public class TeamDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}

	public int total(Map map) {
		
		return mybatis.selectOne("team.total", map);		
	}
	
	public boolean checkRefnum(int no) {
		boolean flag = false;
		
		int cnt = mybatis.selectOne("team.checkRefnum", no);
		if(cnt > 0) {
			flag = true;
		}
		
		return flag;
	}
	
	public boolean replyCreate(TeamDTO dto){
		boolean flag=false;

		int cnt = mybatis.insert("team.replyCreate", dto);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}
	
	public void increaseAnsnum(Map map){
		mybatis.update("team.increaseAnsnum", map);
	}
	
	public TeamDTO replyRead(int no) {
		
		return mybatis.selectOne("team.replyRead", no);
	}
	
	public List<TeamDTO> list(Map map){
		
		return mybatis.selectList("team.list", map);
	}
	
	public boolean create(TeamDTO dto){
		boolean flag = false;
		
		int cnt = mybatis.insert("team.create", dto);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}
	
	public TeamDTO read(int no){
		
		return mybatis.selectOne("team.read", no);
	}
	
	public boolean update(TeamDTO dto){
		boolean flag = false;
		
		int cnt = mybatis.update("team.update", dto);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}

	public boolean delete(int no){
		boolean flag = false;
		
		int cnt = mybatis.delete("team.delete", no);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}


	
}
