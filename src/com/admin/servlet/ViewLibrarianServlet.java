package com.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.dao.LibrarianDao;
import com.admin.model.Librarian;

/**
 * Servlet implementation class ViewLibrarianServlet
 */
@WebServlet("/ViewLibrarianServlet")
public class ViewLibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewLibrarianServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(false);  
        if(session!=null){  
        String name=(String)session.getAttribute("name");  
        }  
        else{  
            out.print("Please login first");  
            request.getRequestDispatcher("Admin.html").include(request, response);  
        }  

		List<Librarian> list = LibrarianDao.ViewLibrarian();

		out.print("<table width='100%' border='1'>");
		out.print("<tr><td style='color:red;font-size:30px;'>Id</td><td style='color:red;font-size:30px;'>Name</td><td style='color:red;font-size:30px;'>Password</td><td style='color:red;font-size:30px;'>Email</td><td style='color:red;font-size:30px;'>Address</td><td style='color:red;font-size:30px;'>City</td><td style='color:red;font-size:30px;'>Contact</td></tr>");
		for (Librarian s : list) {
			out.print("<tr><td>" + s.getId() + "</td><td style='text-transform:capitalize';>" + s.getName() + "</td><td>" + s.getPassword() + "</td><td style='text-transform:lowercase';>"
					+ s.getEmail() + "</td><td>" + s.getAddress() + "</td><td>" + s.getCity() + "</td><td>"
					+ s.getContact() + "</td></tr>");
		}
		out.print("</table>");
	}

}
