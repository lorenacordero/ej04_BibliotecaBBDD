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
		
		try {
			db=new DbConnection();
			cn=db.getConnection();
			System.err.println("DB connect...");
			LibroController librocontroller= new LibroController(cn);
			
			//********************************************************************
			//leemos la base de datos
			List<Libro> libros=librocontroller.getLibros();
			mostrarLibros(libros);
			//********************************************************************
			
			System.out.println();
			System.out.println("AGREGAMOS UN LIBRO");
			
			//********************************************************************
			//agregamos un libro y comprobamos que se ha agregado correctamente
			String titulo="Nada", autor="Carmen Laforet", editorial="Santillana",isbn="978-0-306-40615-7";
			boolean agregado=librocontroller.agregarLibro(titulo, autor, editorial, isbn);
			if(agregado) {
				libros=librocontroller.getLibros();
				mostrarLibros(libros);
			}
			//********************************************************************
			
			db.disconnect();
		} catch (SQLException | IsbnException | CamposVaciosException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
	
		
	}

	private static void mostrarLibros(List<Libro> libros) {
		// TODO Auto-generated method stub
		for(Libro l: libros)
			System.out.println(l);
		
	}

}
