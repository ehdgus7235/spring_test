package spring.model.memo;

import java.sql.*;
import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.db.webtest.DBClose;
import spring.db.webtest.DBOpen;

@Repository
public class MemoDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}

	public boolean create(MemoDTO dto) {
		boolean flag = false;
		
		int cnt = mybatis.insert("memo.create", dto);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}

	public MemoDTO read(int memono) {
		
		return mybatis.selectOne("memo.read", memono);
	}

	public boolean update(MemoDTO dto) {
		boolean flag = false;
		
		int cnt = mybatis.update("memo.update", dto);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}

	public boolean delete(int memono) {
		boolean flag = false;
		
		int cnt = mybatis.delete("memo.delete", memono);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}

	public List<MemoDTO> list(Map map) {

		return mybatis.selectList("memo.list", map);
	}

	public void updateViewcnt(int memono) {
		mybatis.update("memo.updateViewcnt", memono);
	}

	public MemoDTO replyRead(int memono) {
		
		return mybatis.selectOne("memo.replyRead", memono);
	}

	public void upAnsnum(Map map){
		mybatis.update("memo.upAnsnum", map);
	}

	public boolean replyCreate(MemoDTO dto){
		boolean flag = false;
		
		int cnt = mybatis.insert("memo.replyCreate", dto);
		if(cnt > 0)
			flag = true;

		return flag;
	}
	
	public int total(Map map) {
		return mybatis.selectOne("memo.total", map);
	}

	public boolean checkRefnum(int memono) {
		boolean flag = false;

		int cnt = mybatis.selectOne("memo.checkRefnum", memono);
		if(cnt > 0)
			flag = true;
		
		return flag;
	}
}
