package com.simplilearn.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/save")
public class SaveServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		Connection conn = DBConfig.getConnection(props);
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String from = req.getParameter("From");
		String to = req.getParameter("To");
		String depTimeStr = req.getParameter("Departure_Time");
		String arrTimeStr = req.getParameter("Arrival_Time");
		int price = Integer.parseInt(req.getParameter("Price"));
		
		//convert date strings to Date objects
		/*SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date depTime = null;
		Date arrTime = null;
		try {
			depTime = dateFormat.parse(depTimeStr);
			arrTime = dateFormat.parse(arrTimeStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//Create new FlightList object with parameter values
		/*FlightList flight = new FlightList();
		flight.setFrom(from);
		flight.setTo(to);
		flight.setDepTime(depTime);
		flight.setArrTime(arrTime);
		flight.setPrice(price);*/
		
		if(conn!=null) {
			try {
				out.print("<center> <span style='color:blue'><h3>Connection Established!<h3></center></span>");
				String sql = "INSERT INTO flightList(origin, destination, departure_time, arrival_time, price) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, from);
				stmt.setString(2, to);
				stmt.setString(3, depTimeStr);
				stmt.setString(4, arrTimeStr);
				stmt.setInt(5, price);
				int rowsInserted = stmt.executeUpdate();
				if(rowsInserted > 0) {
					System.out.println("A new flight has been added to the database.");
					out.println("<center> <span style='color:red'><br><h3> A new flight has been added to the database <h3></span></center>");
				}else {
					out.print("<center> <span style='color:red'><br> Error! </span></center>");
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
		//resp.sendRedirect("index.html");
	}
	
}
