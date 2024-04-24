package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.LibroDao;
import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import modelo.Libro;

public class LibroController {
	
	private List<Libro> libros;
	Connection cn;

	public LibroController(Connection cn) {
		// TODO Auto-generated constructor stub
		this.cn=cn;
	}
	
	public List<Libro> getLibros() throws SQLException, IsbnException, CamposVaciosException{
		LibroDao biblioteca=new LibroDao(cn);
		return biblioteca.getConsultaLibros("select * from libros");
	}
	
	public boolean agregarLibro(String titulo, String autor, String editorial, String isbn) throws IsbnException, CamposVaciosException, SQLException {
		boolean agregado=false;
		Libro libro=new Libro(titulo, autor, editorial, isbn);
		LibroDao ld= new LibroDao(cn);
		ld.agregarLibro(libro);
		agregado=true;
		return agregado;
	}

}
