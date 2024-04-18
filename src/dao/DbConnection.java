package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	static String bd="biblioteca";
	static String parametros="?useSSL=false&serverTimezone=UTC";
	//static String parametros= "useSSL=false";
	static String longin="root";
	static String password="trebujena";
	static String url="jdbc:mysql://localhost:3306/"+bd+parametros;
	Connection conn=null;
	

	public DbConnection() throws SQLException {
		// TODO Auto-generated constructor stub
			
			

			conn=DriverManager.getConnection(url,longin,password);
		
			
		
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public void disconnect() {
		if(conn!=null) {
			try {
				System.out.println("Closing database: [" +conn+ "] OK");
				conn.close();
				System.err.println("DB disconnect.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
			}
		}
	}

}
