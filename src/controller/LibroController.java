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
	
	public int borrar(String sql) throws SQLException {
		int rows=0;
		LibroDao libro= new LibroDao(cn);
		rows=libro.borrar(sql);
		return rows;
	}
	
	public int prestar(String sql, int id) throws SQLException, IsbnException, CamposVaciosException{
		int row=0;
		LibroDao libro= new LibroDao(cn);
		List<Libro> lib=libro.getConsultaLibros(sql);
		if(lib.size()!=0 && !lib.get(0).isPrestado()) {
			row=libro.prestar(id);
		}
		return row;
	}

}
