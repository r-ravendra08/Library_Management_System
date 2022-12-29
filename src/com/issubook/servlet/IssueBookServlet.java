package com.issubook.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.dao.StudentDao;
import com.student.model.Student;

/**
 * Servlet implementation class IssueBookServlet
 */
@WebServlet("/IssueBookServlet")
public class IssueBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String callno=request.getParameter("callno");
		String studentid1=request.getParameter("studentid");
		int studentid=Integer.parseInt(studentid1);
		String name=request.getParameter("name");
		String contact1=request.getParameter("contact");
		long contact=Long.parseLong(contact1);
		
		Student s=new Student();
		
		s.setStudentid(studentid);
		s.setName(name);
		s.setContact(contact);
		int status=StudentDao.IssueBook(callno, s);
		if(status>0) {
			out.print(status+" Book Issued...");
		}else {
			out.print("something went wrong");
		}
			
		
	}

}
