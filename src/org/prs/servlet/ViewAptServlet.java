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

public class ViewAptServlet extends HttpServlet
{  
	private static final String QUERY="SELECT *  FROM apmt_table where insnumber=?";
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
		try{
		//General Settings
			pw=res.getWriter();
			res.setContentType("text/html");
			//Read form data
			no=Integer.parseInt(req.getParameter("eno"));
			//Set Param value to Query
			ps.setInt(1, no);
			//Execute the sql query
			if(ps!=null){
				rs=ps.executeQuery();
				}
			if(rs!=null){
				if(rs.next()){
					pw.println("<html>");
					pw.println("<h1 style='color:white ; font-style:italic;background-color:blue; font-size:70px;'><b>Manubolu Group of Healthcare Organizations</b></h1>:");
					pw.println("<body align='center' style='background-color:lightblue' >");
					pw.println("<h1 style='color:blue'><b><u>Your Appointment Details</u></b></h1>:");
				  pw.println("<br> Patient First Name:"+rs.getString(1));
				  pw.println("<br> Patient Last Name:"+rs.getString(2));
				  pw.println("<br> Age:"+rs.getInt(3));
				  pw.println("<br> Insurance Number:"+rs.getInt(4));
				  pw.println("<br> Doctor Name:"+rs.getString(5));				 
				 pw.println("<br>Appointment Date:"+rs.getString(6));
				 pw.println("<br>Appointment Time:"+rs.getString(7));
				 pw.println("<br>Location:"+rs.getString(8));
				 pw.println("</body>");
				 pw.println("</html>");
				}
			}
			else{
				pw.println("<br> Records are not found, Try again");
			}
			pw.println("<br><br><a href='ViewAppointment.html'> Back</a>");
			pw.println("<br><br><a href='Home.html'> Home</a>");
			pw.println("<br><br><a href='RescheduleAppointment.html'> Re-Schedule Appointment</a> ");
			pw.println("<br><br><a href='cancel_appointment.html'> Cancel Appointment</a>");
		
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