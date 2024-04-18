package vista;

import java.sql.Connection;
import java.sql.SQLException;

import dao.DbConnection;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DbConnection db=null;
		Connection cn=null;
		
		try {
			db=new DbConnection();
			cn=db.getConnection();
			System.err.println("DB connect...");
			db.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error en la conexión....");
		}
	
		
	}

}
