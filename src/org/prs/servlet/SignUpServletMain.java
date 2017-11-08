package org.prs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServletMain extends HttpServlet {

	private static final String QUERY="insert into prs1 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private Connection con;
	private PreparedStatement ps;
	private PrintWriter pw;
	private ResultSet rs;
	private Statement st=null;
	
   public void init(){
	   System.out.println("init()method executed");
		
		try{
						
			Class.forName("com.mysql.jdbc.Driver");
			
			 con=DriverManager.getConnection("jdbc:mysql://localhost:5985/test", "root", "root");
			 //Create jDBC Prepared Statement obj
		      if(con!=null){
		    	  ps=con.prepareStatement(QUERY);
		      }
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
   
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{	 
	System.out.println("from doGet(-,-)");
	String fname=null;
	String lname=null;
	String gender=null;
	String dob=null;
	int pno=0;
	String email=null;
	String pwd=null;
	String cpwd=null;
	String insNo=null;
	String apt=null;
	String strt=null;
	String city=null;
	String state=null;
	String country=null;
	int zip=0;
	
	int result=0;
	int count=0;
	
	try{
	//General Settings
		pw=res.getWriter();
		res.setContentType("text/html");
		
		//Read form data
		fname=req.getParameter("fname");
		lname=req.getParameter("lname");
		gender=req.getParameter("gender");
		dob=req.getParameter("dob");
		pno=Integer.parseInt(req.getParameter("phone"));
		email=req.getParameter("email");
		pwd=req.getParameter("pass");
		cpwd=req.getParameter("confirm");
	    insNo=req.getParameter("ins");
		apt=req.getParameter("apt");
		strt=req.getParameter("street");
		city=req.getParameter("city");
		state=req.getParameter("state");
		country=req.getParameter("country");
		zip=Integer.parseInt(req.getParameter("zip"));
		
		//set these values query place holders
		if(ps!=null){
		ps.setString(1, fname);
		ps.setString(2, lname);
		ps.setString(3, gender);
		ps.setString(4, dob);
		ps.setLong(5, pno);
		ps.setString(6, email);
		ps.setString(7, pwd);
		ps.setString(8, cpwd);
		ps.setString(9, insNo);
		ps.setString(10, apt);
		ps.setString(11, strt);
		ps.setString(12, city);
		ps.setString(13, state);
		ps.setString(14, country);
		ps.setInt(15, zip);
		
		//execute the query
		result=ps.executeUpdate();
		if(result==0){
			pw.println("<h2> Sorry, Registration is failed..Try again</h2> ");
	     	pw.println("<br><br><a href='sign_up.html'> home</a>");
		}
		else
		{
			pw.println("<script type=\"text/javascript\">");
	       pw.println("alert('Thank you, You are registered succesfully');");
	       pw.println("</script>");
		
		pw.println("<br><br><a href='login.html'> Click for Login</a>");
		}
		}

	}
	catch(Exception e){
		e.printStackTrace();
	}
	
}
public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	System.out.println("from doPost(-,-)");
	doGet(req, res);
}
public void destroy() {
	System.out.println("from destroy()");
	
	try{
	if(rs!=null){
		rs.close();
	  }
	}
	catch(SQLException se){
		se.printStackTrace();
	}
	try{
		if(ps!=null){
			ps.close();
		  }
		}
		catch(SQLException ssse){
			ssse.printStackTrace();
		}
	try{
		if(con!=null){
			con.close();
		  }
		}//try
		catch(SQLException sse){
			sse.printStackTrace();
		}
	catch(Exception e){
		e.printStackTrace();
	}
  
   }//destroy()
}//class