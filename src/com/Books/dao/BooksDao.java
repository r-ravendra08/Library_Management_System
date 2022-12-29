package com.Books.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Books.model.Books;

public class BooksDao {
	public static Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagement","root","");
		}catch(Exception e) {
			System.out.println(e);
		}return con;
	}
	public static int AddBook(Books s) {
		int status=0;
		try {
			Connection con=BooksDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into books(callno,name,auth,publisher,quantity,date)values(?,?,?,?,?,now())");
			ps.setString(1, s.getCallno());
			ps.setString(2, s.getName());
			ps.setString(3, s.getAuth());
			ps.setString(4, s.getPublisher());
			ps.setInt(5, s.getQuantity());
			
			
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}return status;
	}
	public static List<Books> ViewBook(){
		List<Books> list=new ArrayList<Books>();
		try {
			Connection con=BooksDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select* from books");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Books s=new Books();
				s.setId(rs.getInt(1));
				s.setCallno(rs.getString(2));
				s.setName(rs.getString(3));
				s.setAuth(rs.getString(4));
				s.setPublisher(rs.getString(5));
				s.setQuantity(rs.getInt(6));
				s.setIssued(rs.getInt(7));
				s.setDate(rs.getString(8));
				list.add(s);		
			}
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}return list;
	}

}
