package com.Librarian.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Books.dao.BooksDao;
import com.Books.model.Books;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBookServlet() {
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
		System.out.println("hello");

		String callno = request.getParameter("callno");
		String name = request.getParameter("name");
		String auth = request.getParameter("auth");
		String publisher = request.getParameter("publisher");
		String quantity1 = request.getParameter("quantity");
		int quantity = Integer.parseInt(quantity1);
		

		Books s = new Books();
		s.setCallno(callno);
		s.setName(name);
		s.setAuth(auth);
		s.setPublisher(publisher);
		s.setQuantity(quantity);

		int status = BooksDao.AddBook(s);
		if (status > 0) {
			out.print(status + " Added");
		} else {
			out.print("Something went wrong...");

		}

	}

}
