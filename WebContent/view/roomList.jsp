<%@page import="java.util.ArrayList"%>
<%@page import="com.indify.hotels.models.Room"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	$("a.room").click(function(event) {
		event.preventDefault();
		var url = $(this).attr("href");
		var id = $(this).attr("id");
		console.debug(url);
		console.debug("cart_" + id);
	    $form = $(document.getElementById("cart_" + id));
	    $form.attr('action',url);
	    console.debug("action - "+$form.attr('action'));
	    $form.submit();
	    
	});
</script>
<%
	ArrayList<Room> roomList = (ArrayList) request
			.getAttribute("roomList");
	for (int i = 0; i < roomList.size(); i++) {
		Room room = roomList.get(i);
%>
<table border=1 width=100% cellpadding=0 cellspacing=0>
	<form method="POST" action="test"
		id="cart_<%=room.getHotelId()%>_<%=room.getIdRoomRateMap()%>">
		<table border=0 cellpadding=0 cellspacing=0 width=100%>

			<tr>
				<td width=100px><%=room.RoomTypeName()%></td>
				<td><%=room.getRoomTypeDescription()%></td>
				<td width=100px><div class="room_" <%=room.getIdRoomRateMap()%>>
						<a class="room"
							id="<%=room.getHotelId()%>_<%=room.getIdRoomRateMap()%>"
							href="Cart?action=add&hotelId=<%=room.getHotelId()%>&roomId=<%=room.getIdRoomRateMap()%>&rateId=<%=room.getRatePlanId()%>">Book</a>
					</div></td>
			</tr>

		</table>
	</form>
</table>
<%
	}
%>