<%@page import="java.util.ArrayList"%>
<%@page import="com.indify.hotels.models.Cart"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HotelMonolythCart - Cart</title>
</head>
<body>
	<%
		String message = request.getAttribute("message").toString();
		ArrayList cartList = (ArrayList) request
				.getAttribute("cartDetails");
	%>
	<table width=50%>
		<tr>
			<td><%=message%></td>
		</tr>
		<tr>
			<td>Cart Details ...</td>
		</tr>
		<%
			for (int i = 0; i < cartList.size(); i++) {
				Cart cart = (Cart) cartList.get(i);
				String _cart = cart.getCart();
		%>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td width=100px>Hotel -</td>
						<td><%=cart.getHotelName()%></td>
					</tr>
					<tr>
						<td width=100px>Room -</td>
						<td><%=cart.getRoomName()%> - <%=cart.getRatePlan()%></td>
					</tr>
					<tr>
						<td width=100px colspan=2></td>
						<td><a href=#>Remove</a></td>
					</tr>
				</table>

			</td>
		</tr>
		<%
			}
		%>
		<tr>
			<td>
				<table>
			<td><a href=#>Add another room</a></td>
				<td><a href=#>Confirm</a></td>
			</tr>
		
			
	</table>
		</td>
		</tr>
	</table>
</body>
</html>