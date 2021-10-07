
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
import java.util.Enumeration;
import java.util.Collection;

@WebServlet("/Calculate")
public class Calculate extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public Calculate() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	
	   Enumeration<String> tags = request.getHeaderNames();
	   
	   calculate(tags, request, response);
      
   }
   
   void calculate(Enumeration<String> reps_sets, HttpServletRequest request, HttpServletResponse response) throws IOException{
	   
	   response.setContentType("text/html");
	   PrintWriter out = response.getWriter();
	   String title = "Database Result";
	   String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
	            "transitional//en\">\n"; //
	   out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h1 align=\"center\">" + title + "</h1>\n");
	   
	   int sum = 0;
	   
	   while (reps_sets.hasMoreElements()) {
		   String key = reps_sets.nextElement();
		   System.out.println(key);
		   String param = request.getParameter(key);
		   int reps = 0;
		   if (param == null) {
			   reps = 0;
		   }else {
			   reps = Integer.valueOf(param);
		   }
		   
		   param = request.getParameter(key);
		   int sets = 0;
		   if (param == null) {
			   sets = 0;
		   }else {
			   sets = Integer.valueOf(param);
		   }
		   
		   sum += reps * sets;
		   
		   
	   }
	   
	   out.println("<h1>" + "You lifted:" + sum + " lbs, thats incredible!" + "</h1>");
	   out.println("</body></html>");
	   
	   
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
