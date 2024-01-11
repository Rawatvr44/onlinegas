package org.ogb;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail=request.getParameter("email1");
		String pass=request.getParameter("pass1");
		
		PrintWriter out=response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onli","root","undead123");
		PreparedStatement ps =con.prepareStatement("select * from reg where email=? and password=?");
		ps.setString(1, mail);
		ps.setString(2, pass);
		
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()) {
			response.setContentType("text/html");
				RequestDispatcher rd=request.getRequestDispatcher("/home.jsp");
			}
		else {
			response.setContentType("text/html");
			out.print("<h2 style='color:red;'>Email or password missmatch</h2>");
			RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
			rd.include(request, response);
		}
		
		
		}catch(Exception e){
			response.setContentType("text/html");
			out.print("<h2 style='color:red;'>Email or password missmatch</h2>");
			RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
			rd.include(request, response);
		}
	}

}
