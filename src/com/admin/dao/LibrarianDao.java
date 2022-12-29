package com.admin.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.admin.model.Librarian;

public class LibrarianDao {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LibraryManagement", "root", "");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
	public static int AddLibrarian(Librarian s) {
		int status = 0;
		try {
			Connection con = LibrarianDao.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into librarian(name,password,email,address,city,contact)values(?,?,?,?,?,?)");
			ps.setString(1, s.getName());
			ps.setString(2, s.getPassword());
			ps.setString(3, s.getEmail());
			ps.setString(4, s.getAddress());
			ps.setString(5, s.getCity());
			ps.setLong(6, s.getContact());

			status = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static List<Librarian> ViewLibrarian() {
		List<Librarian> list = new ArrayList<Librarian>();
		try {
			Connection con = LibrarianDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from librarian");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Librarian s = new Librarian();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setPassword(rs.getString(3));
				s.setEmail(rs.getString(4));
				s.setAddress(rs.getString(5));
				s.setCity(rs.getString(6));
				s.setContact(rs.getLong(7));
				list.add(s);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;

	}

	public static int DeleteLibrarian(int id) {
		int status = 0;
		try {
			Connection con = LibrarianDao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from librarian where id=?");
			ps.setInt(1, id);

			status = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static class LoginDao {
		public static boolean validate(String email, String password) {
			boolean status = false;
			try {
				Connection con = LibrarianDao.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from librarian where email=? and password=?");
				ps.setString(1, email);
				ps.setString(2, password);

				ResultSet rs = ps.executeQuery();
				status = rs.next();

			} catch (Exception e) {
				System.out.println(e);
			}
			return status;
		}
	}

}
