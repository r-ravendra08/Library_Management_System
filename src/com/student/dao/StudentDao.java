package com.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.student.model.Student;

public class StudentDao {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagement", "root", "");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static boolean checkBook(String callno) {
		boolean status = false;
		try {
			Connection con = StudentDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from books where callno=?");
			ps.setString(1, callno);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int IssueBook(String callno, Student s) {
		int status = 0;
		try {
			Connection con = StudentDao.getConnection();

			status = updatebook(callno);// updating quantity and issue

			if (status > 0) {
				PreparedStatement ps = con.prepareStatement(
						"insert into issuedbook(callno,studentid,name,contact,date)values(?,?,?,?,now())");
				ps.setString(1, callno);
				ps.setInt(2, s.getStudentid());
				ps.setString(3, s.getName());
				ps.setLong(4, s.getContact());
				status = ps.executeUpdate();

			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

	public static int updatebook(String callno) {
		int status = 0;
		int quantity = 0, issued = 0;
		try {
			Connection con = StudentDao.getConnection();

			PreparedStatement ps = con.prepareStatement("select quantity,issued from books where callno=?");
			ps.setString(1, callno);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				quantity = rs.getInt("quantity");
				issued = rs.getInt("issued");
			}

			if (quantity > 0) {
				PreparedStatement ps2 = con.prepareStatement("update books set quantity=?,issued=? where callno=?");
				ps2.setInt(1, quantity - 1);
				ps2.setInt(2, issued + 1);
				ps2.setString(3, callno);

				status = ps2.executeUpdate();
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static List<Student> ViewIssuedBook() {
		List<Student> list = new ArrayList<Student>();
		try {
			Connection con = StudentDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from issuedbook");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt(1));
				s.setCallno(rs.getString(2));
				s.setStudentid(rs.getInt(3));
				s.setName(rs.getString(4));
				s.setContact(rs.getLong(5));
				s.setDate(rs.getString(6));
				list.add(s);
				
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public static int ReturnBookDao(String callno, int studentid) {
		int status = 0;
		try {
			Connection con = StudentDao.getConnection();

			status = updateAfterReturnbook(callno);// updating quantity and issue

			if (status > 0) {
				PreparedStatement ps = con.prepareStatement("delete from issuedbook where callno=? and studentid=?");
				ps.setString(1, callno);
				ps.setInt(2, studentid);
				status = ps.executeUpdate();
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int updateAfterReturnbook(String callno) {
		int status = 0;
		int quantity = 0, issued = 0;
		try {
			Connection con = StudentDao.getConnection();

			PreparedStatement ps = con.prepareStatement("select quantity,issued from books where callno=?");
			ps.setString(1, callno);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				quantity = rs.getInt("quantity");
				issued = rs.getInt("issued");
			}

			if (issued > 0) {
				PreparedStatement ps2 = con.prepareStatement("update books set quantity=?,issued=? where callno=?");
				ps2.setInt(1, quantity + 1);
				ps2.setInt(2, issued - 1);
				ps2.setString(3, callno);

				status = ps2.executeUpdate();
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

}
