package spring.model.team;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeamService{
	
	@Autowired
	private TeamDAO dao;
	
	public boolean reply(TeamDTO dto) {
		boolean flag = false;
		
		
		try {
			Map map = new HashMap();
			map.put("grpno", dto.getGrpno());
			map.put("ansnum", dto.getAnsnum());
			
			dao.increaseAnsnum(map);
			flag = dao.replyCreate(dto);
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		
		return flag;
	}
}
