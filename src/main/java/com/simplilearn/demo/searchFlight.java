package com.simplilearn.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/flightSearch")
public class searchFlight extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		Connection conn = DBConfig.getConnection(props);
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		int passenger = Integer.parseInt(req.getParameter("numPassengers"));
		String from = req.getParameter("originCity");
		String to = req.getParameter("destCity");
		String date = req.getParameter("date");
		
		if(conn!=null) {
			try {
				out.print("Connection Established!");
				String sql = "SELECT * FROM flightList WHERE origin = ? AND destination = ?";
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, from);
				stmt.setString(2, to);
				
				ResultSet result = stmt.executeQuery();
				
				if(result.next() && passenger>0) {
					out.println("<center> <span style='color:green'><br> Following flight(s) are availabe on: " + date + "</span></center>");
					do {
						out.print("<br>" + "|> " + result.getString(2) + " >>>>> " + result.getString(3) + ", Departure: " + result.getString(4) + ", Arrival: " 
													+ result.getString(5) + ", Rs. " + result.getInt(6) + "  " + "<a href=\"passengerInfo.jsp?numPassengers=" + passenger +"&flightId=" + result.getInt(1) + "\">Click to book</a>" + "<br>" );
					}while(result.next());
					
				}else {
					out.print("No flight found!");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}			
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		
	}
}
