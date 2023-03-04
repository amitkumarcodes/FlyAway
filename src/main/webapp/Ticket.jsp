<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Thank you for booking with us!</h1>
<h2>Flight Details</h2>
<p>Flight ID: ${flightId}</p>
<p>From: ${departureAirport}</p>
<p>To: ${arrivalAirport}</p>
<p>Departure Time: ${departureTime}</p>
<p>Arrival Time: ${arrivalTime}</p>
<p>Total Price: ${price*numPassengers}</p>
<h2>Passenger Details</h2>
<p><b>${passengers.toString()}</b></p>


</body>
</html>