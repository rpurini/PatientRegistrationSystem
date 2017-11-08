//DBServlet.java
package org.prs.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelAptServlet extends HttpServlet
{  
	private static final String QUERY=" DELETE FROM apmt_table where insnumber=?";
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
		int no=0;
		int count=0;
		try{
		//General Settings
			pw=res.getWriter();
			res.setContentType("text/html");
			//Read form data
			no=Integer.parseInt(req.getParameter("sno"));
			//Set Param value to Query
			ps.setInt(1, no);
			//Execute the sql query
			if(ps!=null){
				count=ps.executeUpdate();
				}
			if(count==1){
					pw.println("<h1><b><u>Your Appointment has been Cancelled..</u></b></h1>:");
					pw.println("<br><br><a href='Home.html'>Click for Home</a> </br>");
						  
				}
			else{
				pw.println("<br> Insurance Number is not correct, Try again");
			}
			pw.println("<br><br><a href='cancel_appointment.html'> Back</a> </br>");
			pw.println("<br><br><a href='Home.html'>Click for Home</a> </br>");
			
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("from doPost(-,-)");
		doGet(req, res);
	}
	@Override
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
//class