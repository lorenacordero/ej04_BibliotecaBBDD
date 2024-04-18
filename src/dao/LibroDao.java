package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

import modelo.Libro;

public class LibroDao {
	
	private Connection cn;

	public LibroDao(Connection cn) {
		// TODO Auto-generated constructor stub
		this.cn=cn;
	}
	
	public List<Libro> getConsultaLibros(String sql) throws SQLException{
		List<Libro> libros=new ArrayList<Libro>();
		PreparedStatement pst= cn.prepareStatement(sql);
		ResultSet rs= pst.executeQuery();
		Libro libro;
		while(rs.next()) {
			int id=rs.getInt("idlibros");
			String titulo=rs.getString("titulo");
			String autor=rs.getString("autor");
			String editorial=rs.getString("editorial");
			String isbn=rs.getString("isbn");
			boolean prestado=rs.getBoolean("prestado");
			Date fechaPrestamo=rs.getDate("fechaPrestamo");
			Date fechaDevolucion=rs.getDate("fechaDevolucion");
			Timestamp fechaAlta=rs.getTimestamp("fechaAlta");
			libro=new Libro(id,titulo,autor,editorial,isbn,prestado,fechaPrestamo,fechaDevolucion,fechaAlta);
			libros.add(libro);
			libro=null;
		}
		return libros;
	}

}
