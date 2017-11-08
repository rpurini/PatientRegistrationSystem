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

public class MakeAptServlet extends HttpServlet {

	private static final String QUERY="insert into apmt_table values(?,?,?,?,?,?,?,?)";
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
	int age=0;
	int insNo=0;
	String dName=null;
	String date=null;
	String time=null;
	String location=null;
	
	int result=0;
	int count=0;
	
	try{
	//General Settings
		pw=res.getWriter();
		res.setContentType("text/html");
		
		//Read form data
		fname=req.getParameter("fname");
		lname=req.getParameter("lname");
		age=Integer.parseInt(req.getParameter("age"));
		insNo=Integer.parseInt(req.getParameter("ins"));
		dName=req.getParameter("dname");
		date=req.getParameter("date");
		time=req.getParameter("time");
		location=req.getParameter("location");
		
		//set these values query place holders
		if(ps!=null){
		ps.setString(1, fname);
		ps.setString(2, lname);
		ps.setInt(3, age);
		ps.setInt(4, insNo);
		ps.setString(5, dName);
		ps.setString(6, date);
		ps.setString(7, time);
		ps.setString(8, location);		
		
		//execute the query
		result=ps.executeUpdate();
		if(result==0){
			pw.println("<h2 align='center' style='color:red'> Sorry, Appointment Registration is failed..Try again</h2> ");
	     	pw.println("<br><br><a href='Appointment.html'> Back</a>");
		}
		else
		{
	       pw.println("<h1 align='center' style='color:Green'>Thank you, Your appointment has been scheduled successfully.<br/>");
	  		pw.println("<br><br><a href='Home.html'> Home</a>");
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