package com.Librarian.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.dao.LibrarianDao.LoginDao;
import com.admin.servlet.ViewLibrarianServlet;

/**
 * Servlet implementation class LibrarianLoginServlet
 */
@WebServlet("/LibrarianLoginServlet")
public class LibrarianLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibrarianLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String user = request.getParameter("user");
		String password = request.getParameter("password");
		if (LoginDao.validate(user, password)) {
			out.print("<script>alert('Success Login')</script>");
			//RequestDispatcher rd = request.getRequestDispatcher("");
			//rd.forward(request, response);
			response.sendRedirect("LibrarianHome.html");
		} else {
			out.print("<script>alert('Sorry username or password error')</script>");
			RequestDispatcher rd = request.getRequestDispatcher("LibrarianLogin.html");
			rd.include(request, response);
		}
	}

}
