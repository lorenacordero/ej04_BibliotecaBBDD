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
			

			LibroController librocontroller= new LibroController(cn);
			//leemos la base de datos
			List<Libro> libros=librocontroller.getLibros();
			mostrarLibros(libros);
			
			
			librocontroller=null;
			db.disconnect();db=null;
			//********************************************************************
			
			System.out.println();

			
			//********************************************************************
//			System.out.println("AGREGAMOS UN LIBRO");
			//agregamos un libro y comprobamos que se ha agregado correctamente
//			db=new DbConnection();
//			cn=db.getConnection();
//			librocontroller=new LibroController(cn);
//			
//			
//			String titulo="Nada", autor="Carmen Laforet", editorial="Santillana",isbn="978-0-306-40615-7";
//			boolean agregado=librocontroller.agregarLibro(titulo, autor, editorial, isbn);
//			if(agregado) {
//				libros=librocontroller.getLibros();
//				mostrarLibros(libros);
//			}
//			
//			
//			librocontroller=null;
//			db.disconnect();db=null;
			//********************************************************************
//			System.out.println();
			
			//********************************************************************
			//borramos un libro
			db=new DbConnection();
			cn=db.getConnection();
			librocontroller=new LibroController(cn);
			
			
			String campo="idlibros", cadenaBusqueda="47";
			String sql="delete from libros where "+campo+" = '"+cadenaBusqueda+"'";
			int rows=librocontroller.borrar(sql);
			if(rows>0) {
				System.out.println("Se han borrado "+rows+" libros");
				libros=librocontroller.getLibros();
				mostrarLibros(libros);
			}
			else
				System.out.println("No se ha eliminado ningún libro");
			
			
			librocontroller=null;
			db.disconnect();db=null;
			//********************************************************************
			System.out.println();
			//********************************************************************
			db=new DbConnection();
			cn=db.getConnection();
			librocontroller=new LibroController(cn);
			
			
			int id=1;
			sql="select * from libros where "+campo+" = '"+id+"'";
			
			int prestados=librocontroller.prestar(sql, id);
			System.out.println("Se han prestado "+prestados+" libros");
			libros=librocontroller.getLibros();
			mostrarLibros(libros);
			
			librocontroller=null;
			db.disconnect();db=null;
			
			
					
			
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
