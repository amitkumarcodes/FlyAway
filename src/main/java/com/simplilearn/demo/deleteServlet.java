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

@WebServlet("/delete")
public class deleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		Connection conn = DBConfig.getConnection(props);
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		int id = Integer.parseInt(req.getParameter("id"));
		
		if(conn!=null) {
			try {
				out.print("<center> <span style='color:blue'><h3>Connection Established!<h3></span></center>");
				String sql = "DELETE FROM flightList where id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1,  id);
				int rowsInserted = stmt.executeUpdate();
				if(rowsInserted > 0) {
					System.out.println("FlightList has been Updated.");
					out.println("<center> <span style='color:red'><br> <h3>Flight removed from list! <h3></span></center>");
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
