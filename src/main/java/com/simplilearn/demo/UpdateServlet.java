package com.simplilearn.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		Connection conn = DBConfig.getConnection(props);
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		int id = Integer.parseInt(req.getParameter("id"));
		String from = req.getParameter("From");
		String to = req.getParameter("To");
		String depTimeStr = req.getParameter("Departure_Time");
		String arrTimeStr = req.getParameter("Arrival_Time");
		int price = Integer.parseInt(req.getParameter("Price"));	
		
		if(conn!=null) {
			try {
				out.print("Connection Established!");
				String sql = "UPDATE flightList SET origin = ?, destination = ?, departure_time = ?, arrival_time = ?, price = ? WHERE id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, from);
				stmt.setString(2, to);
				stmt.setString(3, depTimeStr);
				stmt.setString(4, arrTimeStr);
				stmt.setInt(5, price);
				stmt.setInt(6,  id);
				int rowsInserted = stmt.executeUpdate();
				if(rowsInserted > 0) {
					System.out.println("FlightList has been Updated.");
					out.println("<center> <span style='color:red'><br> FlightList has been Updated! </span></center>");
				}else {
					out.print("Data not added");
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
