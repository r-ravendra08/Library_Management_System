package com.Librarian.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Books.dao.BooksDao;
import com.Books.model.Books;



/**
 * Servlet implementation class ViewBookServlet
 */
@WebServlet("/ViewBookServlet")
public class ViewBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBookServlet() {
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
		List<Books> list=BooksDao.ViewBook();
		out.print("<table width='100%' border='1'>");
		out.print("<tr><td style='color:red;font-size:30px;'>Id</td><td style='color:red;font-size:30px;'>Call No.</td><td style='color:red;font-size:30px;'>Name</td><td style='color:red;font-size:30px;'>Auth</td><td style='color:red;font-size:30px;'>Publiaher</td><td style='color:red;font-size:30px;'>Quantity</td><td style='color:red;font-size:30px;'>Issued</td><td style='color:red;font-size:30px;'>Date</td></tr>");
		for(Books s:list) {
			out.print("<tr><td>"+s.getId()+"</td><td>"+s.getCallno()+"</td><td>"+s.getName()+"</td><td>"+s.getAuth()+"</td><td>"+s.getPublisher()+"</td><td>"+s.getQuantity()+"</td><td>"+s.getIssued()+"</td><td>"+s.getDate()+"</td></tr>");
		}
		out.print("</table>");
		
	}

}
