
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MainServlet() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
	  response.setContentType("text/html");
	  PrintWriter out = response.getWriter(); 
	  
	  String title = "Lets get jacked!!";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
	   
	  Connection connection = null;
	  PreparedStatement preparedStatement = null; 
	  
	  ArrayList<String> excersizes = new ArrayList<String>();
	  
	  out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h1 align=\"center\">" + title + "</h1>\n");
      
      try {
          DBConnection.getDBConnection(getServletContext());
          connection = DBConnection.connection;
          
          String selectSQL = "SELECT * FROM excersizes";
          preparedStatement = connection.prepareStatement(selectSQL);
          
          ResultSet rs = preparedStatement.executeQuery();
          
          out.print(docType + "<form action='Calculate' method='POST'>");
          
          while (rs.next()) {
             String Excersize = rs.getString("Excersize").trim();
             excersizes.add(Excersize);
             
             int Reps = rs.getInt("Reps");
             int Sets = rs.getInt("Sets");
             int rpe = rs.getInt("rpe");

             out.print("Excersize: " + Excersize + "   ");
             out.print("Rpe:" + rpe);
             out.print("   ");
             out.println("Reps: <input type='number' name='reps" + "_Excersize'" +  "value=" + Reps + ">" + //
            			"   Sets: <input type='number' name='sets" + "_Excersize'" + "value=" + Sets + ">"
            		  );
             out.print("<br>");
             out.print("<br>");
          }
          out.print("<input type='submit' ");
          out.print("</form>");
          out.println("</body></html>");
          rs.close();
          preparedStatement.close();
          connection.close();
       } catch (SQLException se) {
          se.printStackTrace();
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          try {
             if (preparedStatement != null)
                preparedStatement.close();
          } catch (SQLException se2) {
          }
          try {
             if (connection != null)
                connection.close();
          } catch (SQLException se) {
             se.printStackTrace();
          }
       }
	   
      response.setContentType("text/html");
	  
	  
	  out.println("<a href=/WEB-INF/simpleFormInsert.html>Search Data</a> <br>");
	  out.println("</body></html>");
      
//      if (request.getParameter("button1") != null) {
//    	  Submit(userName, email, phone, response);
//      }
      
   }
   
   void Calculate(String userName, String email, String phone, HttpServletResponse response) throws IOException{
	   	
	      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
