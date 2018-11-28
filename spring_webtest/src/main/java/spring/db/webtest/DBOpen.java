package spring.db.webtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBOpen {

	public static Connection open() {
		// TODO Auto-generated method stub
		
		Connection con = null;
		
		try {
			Class.forName(Constance.DRIVER);
			con = DriverManager.getConnection(Constance.URL, Constance.USER, Constance.PASSWD);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return con;
	}

}
