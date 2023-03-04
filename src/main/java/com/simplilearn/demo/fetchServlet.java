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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fetch")
public class fetchServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		Connection conn = DBConfig.getConnection(props);
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		if(conn!=null) {
			try {
				out.print("<center> <span style='color:blue'><h3>Connection Established!<h3></span></center>");
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery("SELECT * FROM flightList");
				
				while(result.next()) {
					out.print("<br>" + result.getInt(1) + ".   " + result.getString(2) + " >>>>> " + result.getString(3) + ", " + result.getString(4) + ", " 
												+ result.getString(5) + ", Rs. " + result.getInt(6) + "<br>");
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
