package com.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLoginServlet() {
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

		if (user.equals("roopal") && password.equals("1234")) {
			out.print("<script>alert('login success..."+user+"')</script>");
			//out.print("Welcome, " + user);
			HttpSession session = request.getSession();
			session.setAttribute("name", user);
			request.getRequestDispatcher("AdminHome.html").include(request, response);
		} else {
			out.print("<script>alert('Sorry, username or password error!');</script>");
			request.getRequestDispatcher("Admin.html").include(request, response);

		}
		out.close();
	}

}
