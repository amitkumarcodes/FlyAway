<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Payment Page</title>
</head>
<body>
	<h1>Payment Details</h1>
	<form method="post" action="Ticket.jsp">
	<label for="price">Passengers:</label>
		<input type="text" id="numPassengers" name="numPassengers" readonly="readonly" value="${numPassengers}" /><br />
		
		<label for="price">Price:</label>
		<input type="text" id="price" name="price" readonly="readonly" value="${price}" /><br />
		
		<label for="price">Total Price:</label>
		<input type="text" id="totalPrice" name="totalPrice" readonly="readonly" value="${totalPrice}" /><br />
		
		<label for="name">Name on Card:</label>
		<input type="text" id="name" name="name" required /><br />
		
		<label for="cardNumber">Card Number:</label>
		<input type="text" id="cardNumber" name="cardNumber"  /><br />
		
		<label for="expiryMonth">Expiration Month:</label>
		<input type="text" id="expiryMonth" name="expiryMonth"  /><br />
		
		<label for="expiryYear">Expiration Year:</label>
		<input type="text" id="expiryYear" name="expiryYear"  /><br />
		
		<label for="cvv">CVV:</label>
		<input type="text" id="cvv" name="cvv"  /><br />
		
		<label for="address">Billing Address:</label>
		<textarea id="address" name="address" ></textarea><br />
		
		<input type="submit" value="Pay Now" />
	</form>
</body>
</html>
