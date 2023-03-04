<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>Enter Passenger Information</h1>
			<%
				int flightId = Integer.parseInt(request.getParameter("flightId"));
				int numPassengers = Integer.parseInt(request.getParameter("numPassengers")); %>
				<form method="post" action="PassengerServlet?numPassengers=<%=numPassengers%>&flightId=<%=flightId%>">
				<%
				for(int i = 1; i <= numPassengers; i++){
			%>
				<fieldset>
					<legend>Passenger <%= i %> Information</legend>
					Name: <input type="text" name="name<%=i %>" required><br>
					Age: <input type="number" name="age<%=i %>" required><br>				
				</fieldset>
			<%
				}
			%>
			
			<input type="submit" value="Submit">
		</form>
 </body>
</html>