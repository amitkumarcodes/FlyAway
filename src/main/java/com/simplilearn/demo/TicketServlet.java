package com.simplilearn.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/PassengerServlet")
public class TicketServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numPassengers = Integer.parseInt(request.getParameter("numPassengers"));
		int flightId = Integer.parseInt(request.getParameter("flightId"));
		
		
		PrintWriter out = response.getWriter();
		List<Passenger> passengers = new ArrayList<>();
		for(int i =1; i <= numPassengers; i++) {
			String name = request.getParameter("name" + i);
			int age = Integer.parseInt(request.getParameter("age" + i));
			Passenger passenger = new Passenger(name, age);
			passengers.add(passenger);
		}
			
			//Connection to the database
			Properties props = new Properties();
			InputStream in = getServletContext().getResourceAsStream("/WEB-INF/application.properties");
			props.load(in);
			Connection conn = DBConfig.getConnection(props);
			response.setContentType("text/html");
			
			PreparedStatement stmt = null;
			ResultSet rs = null;
			float price = 0.0f;
			float totalPrice = 0.0f;
			String departureAirport = null;
			String arrivalAirport = null;
			String departureTime = null;
			String arrivalTime = null;
			String date = null;
			
			try {
					
				// Prepare SQL query to retrieve the price of the flight
				stmt = conn.prepareStatement("SELECT price, origin, destination, departure_time, arrival_time FROM flightList WHERE id = ?");
				stmt.setInt(1, flightId);
				
				// Execute the query and retrieve the result set
				rs = stmt.executeQuery();
				
				// Extract the price of the flight from the result set
				if (rs.next()) {
					price = rs.getFloat("price");
					departureAirport = rs.getString("origin");
					arrivalAirport = rs.getString("destination");
					departureTime = rs.getString("departure_time");
					arrivalTime = rs.getString("arrival_time");
				}
				
				// Calculate the total price for the booking
				totalPrice = numPassengers * price;
				//out.print(totalPrice);
			
				
				// Set the numPassengers and totalPrice as attributes of the request object
				request.setAttribute("numPassengers", numPassengers);
				request.setAttribute("totalPrice", totalPrice);
				request.setAttribute("price", price);
				request.setAttribute("flightId", flightId);
				request.setAttribute("passengers", passengers);
				request.setAttribute("departureAirport", departureAirport);
				request.setAttribute("arrivalAirport", arrivalAirport);
				request.setAttribute("departureTime", departureTime);
				request.setAttribute("arrivalTime", arrivalTime);
				
				// Forward the request to the payment JSP page
				//request.getRequestDispatcher("payment.jsp").forward(request, response);
				request.getRequestDispatcher("Ticket.jsp").forward(request, response);	
	
				}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// Close the result set, statement and connection
					if (rs != null) rs.close();
					if (stmt != null) stmt.close();
					if (conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
	}	
}
