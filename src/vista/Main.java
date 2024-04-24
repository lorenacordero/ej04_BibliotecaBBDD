package vista;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import controller.LibroController;
import dao.DbConnection;
import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import modelo.Libro;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DbConnection db=null;
		Connection cn=null;
		
//		try {
//			db=new DbConnection();
//			cn=db.getConnection();
//			System.err.println("DB connect...");
//			db.disconnect();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.err.println("Error en la conexión....");
//		}
		
		try {
			db=new DbConnection();
			cn=db.getConnection();
			LibroController librocontroller= new LibroController(cn);
			List<Libro> libros=librocontroller.getLibros();
			for(Libro l: libros)
				System.out.println(l);
		} catch (SQLException | IsbnException | CamposVaciosException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
	
		
	}

}
