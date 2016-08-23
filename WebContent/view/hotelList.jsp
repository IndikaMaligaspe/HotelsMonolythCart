<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.indify.hotels.models.Hotel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HotelMonolythCart - List Hotels</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script>
	$(document).ready(function() {
		$("a.hotelRoom").click(function(event) {
			event.preventDefault();
			var url = $(this).attr("href");
			var id = $(this).attr("id");						
			$.get(url, function(data) {
				$("div.roomList_"+id).html(data);
			});
		});
	});
</script>
</head>
<body>
	<%
		ArrayList<Hotel> hotelList = (ArrayList) request
				.getAttribute("hotelList");
		for (int i = 0; i < hotelList.size(); i++) {
			Hotel hotel = hotelList.get(i);
	%>

	<table border=0>
		<tr border=0>
			<td rowspan=3><img src="<%=hotel.getThumbNail()%>"></td>
			<td><%=hotel.getName()%></td>
		</tr>
		<tr border=0>
			<td><%=hotel.getAddress()%></td>
		</tr>
		<tr border=0>
			<td><%=hotel.getShortDescrition()%></td>
		</tr>
		<tr>
			<td></td>
			<td align="right" colspan="2"><div
					class="roomList_<%=hotel.getIdHotel()%>">
					<a class='hotelRoom' href='Rooms/?hotelId=<%=hotel.getIdHotel()%>' id='<%=hotel.getIdHotel()%>'>listRooms</a>
				</div></td>
		</tr>
	</table>

	<%
		}
	%>
</body>
</html>