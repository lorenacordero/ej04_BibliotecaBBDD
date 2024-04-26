package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import modelo.Libro;

public class LibroDao {
	
	private Connection cn;

	public LibroDao(Connection cn) {
		// TODO Auto-generated constructor stub
		this.cn=cn;
	}
	
	public List<Libro> getConsultaLibros(String sql) throws SQLException, IsbnException, CamposVaciosException{
		List<Libro> libros=new ArrayList<Libro>();
		PreparedStatement pst= cn.prepareStatement(sql);
		ResultSet rs= pst.executeQuery();
		Libro libro;
		while(rs.next()) {
			int id=rs.getInt("idlibros");
			String titulo=rs.getString("titulo");
			String autor=rs.getString("autor");
			String editorial=rs.getString("editorial");
			boolean prestado=rs.getBoolean("prestado");
			LocalDate fechaPrestamo=null;
			if(rs.getDate("fechaPrestamo")!=null)
				fechaPrestamo=rs.getDate("fechaPrestamo").toLocalDate();
			LocalDate fechaDevolucion=null;
			if(rs.getDate("fechaDevolucion")!=null)
				fechaDevolucion=rs.getDate("fechaDevolucion").toLocalDate();
			String isbn=rs.getString("isbn");
			LocalDateTime fechaAlta=rs.getTimestamp("fechaAlta").toLocalDateTime();
			libro=new Libro(id,titulo,autor,editorial,isbn,prestado,fechaPrestamo,fechaDevolucion,fechaAlta);
			libros.add(libro);
			libro=null;
		}
		return libros;
	}
	
	
	public boolean agregarLibro(Libro libro) throws SQLException {
		boolean agregado=false;
		String titulo=libro.getTitulo();
		String autor=libro.getAutor();
		String editorial=libro.getEditorial();
		String isbn=libro.getIsbn();
		boolean prestado=libro.isPrestado();
		LocalDateTime fechaAlta=libro.getFechaAlta();
		
		String sql="insert into libros values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst=cn.prepareStatement(sql);
		
		pst.setInt(1, 0);
		pst.setString(2, titulo);
		pst.setString(3, autor);
		pst.setString(4, editorial);
		pst.setBoolean(5, prestado);
		pst.setDate(6, null);
		pst.setDate(7, null);
		pst.setString(8, isbn);
		Timestamp ts=Timestamp.valueOf(fechaAlta);
		pst.setTimestamp(9, ts);
		
		pst.executeUpdate();
		pst=null;
		
		agregado=true;
		return agregado;
	}
	
	
	public int borrar(String sql) throws SQLException {
		int rows=0;
		PreparedStatement pst=cn.prepareStatement(sql);
		rows=pst.executeUpdate();
		return rows;
	}
	
	public int prestar(int id) throws SQLException {
		int row=0;
		String sql="Update libros Set prestado=? ,fechaPrestamo=?,fechaDevolucion=? Where idlibros=?";
		PreparedStatement pst=cn.prepareStatement(sql);
		LocalDate fechaPrest=LocalDate.now();
		LocalDate fechaDev=fechaPrest.plusDays(10);
		Date fechaPrestamo=Date.valueOf(fechaPrest);
		Date fechaDevolucion=Date.valueOf(fechaDev);
		pst.setBoolean(1, true);
		pst.setDate(2, fechaPrestamo);
		pst.setDate(3, fechaDevolucion);
		pst.setInt(4, id);
		row=pst.executeUpdate();
		return row;
	}

}
