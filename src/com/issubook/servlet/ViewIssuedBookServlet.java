package com.issubook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.Librarian;
import com.student.dao.StudentDao;
import com.student.model.Student;

/**
 * Servlet implementation class ViewIssuedBookServlet
 */
@WebServlet("/ViewIssuedBookServlet")
public class ViewIssuedBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewIssuedBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		List<Student> list=StudentDao.ViewIssuedBook();
		out.print("<table width='100%' border='1'>");
		out.print("<tr><td style='color:red;font-size:30px;'>Id</td><td style='color:red;font-size:30px;'>Book Call No.</td><td style='color:red;font-size:30px;'>Student Id</td><td style='color:red;font-size:30px;'>Student Name</td><td style='color:red;font-size:30px;'>Contact</td><td style='color:red;font-size:30px;'>Date</td></tr>");
		for (Student s : list) {
			out.print("<tr><td>" + s.getId() + "</td><td style='text-transform:capitalize';>" + s.getCallno() + "</td><td>" + s.getStudentid() + "</td><td style='text-transform:lowercase';>"
					+ s.getName() + "</td><td>" + s.getContact() + "</td><td>" + s.getDate() +"</td></tr>");
		}
		out.print("</table>");

	
	}

}
