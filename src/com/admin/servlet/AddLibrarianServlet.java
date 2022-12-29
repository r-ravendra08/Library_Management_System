package com.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.dao.LibrarianDao;
import com.admin.model.Librarian;

/**
 * Servlet implementation class AddLibrarianServlet
 */
@WebServlet("/AddLibrarianServlet")
public class AddLibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLibrarianServlet() {
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
		
		HttpSession session=request.getSession(false);  
        if(session!=null){  
        }  
        else{  
            out.print("Please login first");  
            //request.getRequestDispatcher("Admin.html").include(request, response);  
        }  
		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String contact1=request.getParameter("contact");
		long contact=Long.parseLong(contact1);
		
		Librarian s=new Librarian();
		s.setName(name);
		s.setPassword(password);
		s.setEmail(email);
		s.setAddress(address);
		s.setCity(city);
		s.setContact(contact);
		
		int status=LibrarianDao.AddLibrarian(s);
		if(status>0) {
			out.print(status+" Added...");
			//request.getRequestDispatcher("AddLibrarian.html").include(request, response);
		}else {
			out.print("Something went wrong...");
		}
	}

}
