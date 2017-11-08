package org.prs.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        
        String email = request.getParameter("user");
        String pass = request.getParameter("pwd");
        
        if(Validate.checkUser(email, pass))
        {
            RequestDispatcher rs = request.getRequestDispatcher("Home.html");
            rs.forward(request, response);
        }
        else
        {
        	pw.println("<script type=\"text/javascript\">");
 	       pw.println("alert('Invalid Username/Password, Please try again');");
 	       pw.println("</script>");
           RequestDispatcher rs = request.getRequestDispatcher("login.html");
           rs.include(request, response);
        }
    }  
}