package org.prs.servlet;

import java.sql.*;

public class Validate
 {
     public static boolean checkUser(String email,String pass) 
     {
      boolean st =false;
      try{

    	  Class.forName("com.mysql.jdbc.Driver");
			
		Connection	 con=DriverManager.getConnection("jdbc:mysql://localhost:5985/test", "root", "root");
         PreparedStatement ps =con.prepareStatement
                             ("select * from prs1 where email=? and pass=?");
         ps.setString(1, email);
         ps.setString(2, pass);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
        
      }catch(Exception e)
      {
          e.printStackTrace();
      }
         return st;                 
  }   
}