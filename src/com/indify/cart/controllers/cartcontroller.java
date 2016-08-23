package com.indify.cart.controllers;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.org.mozilla.javascript.internal.json.JsonParser;

import com.indify.hotels.models.Cart;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart/*")
public class cartcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private ArrayList<Object> cartDetails = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public cartcontroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost/hotelcartsystem?"
							+ "user=indika&password=123456");

			String action = request.getParameter("action");
			ArrayList<Cart> cartDetails = null;
			if (null == request.getAttribute("cartDetails")) {
				cartDetails = new ArrayList<Cart>();
			} else {
				cartDetails = (ArrayList<Cart>) request
						.getAttribute("cartDetails");
			}
			String message = "";

			Cart cart = null;
			if ((null != action) && (action.equals("add"))) {
				cart = new Cart(generateCart(request), "JSON",
						generateTransactionId(request));
				stmt = conn.createStatement();
				stmt.execute("insert into  `hotelcartsystem`.`cart` (`cart`,`type`,`transactionID`) "
						+ " values "
						+ " ('"
						+ cart.getCart()
						+ "','JSON','"
						+ cart.getTransactionID() + "');");
				
				rs = stmt.executeQuery("select name  from  `hotelcartsystem`.`Hotel` where idHotel = '"+request.getParameter("hotelId")+"'");
				if(rs.next()){
					cart.setHotelName(rs.getString(1));
				}
				
				rs = stmt.executeQuery("select name from `hotelcartsystem`.`RoomType` where idRoom = '"+request.getParameter("roomId")+"'");
				
				if(rs.next()){
					cart.setRoomName (rs.getString(1));
				}
				
				rs = stmt.executeQuery("select name from `hotelcartsystem`.`RatePlan` where idRatePlan = '"+request.getParameter("rateId")+"'");
				
				if(rs.next()){
					cart.setRatePlan(rs.getString(1));
				}
				
				cartDetails.add(cart);
				message = "Added to Cart Successfully..";

			}
			if ((null != action) && (action.equals("remove"))) {

			}
			if ((null != action) && (action.equals("view"))) {
				stmt = conn.createStatement();
				// rs =
				// stmt.executeQuery("select idRoomRateMap,HotelId,RoomTypeId,RatePlanId,rt.Name as RoomType,rt.Description as RoomTypeDesciption, "+
				// " rp.Name as RatePlan from `hotelcartsystem`.`RoomRateMap` as  rrm "+
				// " left join `hotelcartsystem`.`RoomType` as rt on rrm.RoomTypeId = rt.idRoom "+
				// " left join `hotelcartsystem`.`RatePlan` as rp on rrm.RatePlanId = rp.idRatePlan "+
				// " where rrm.RoomTypeId = rt.idRoom "+
				// " and "+
				// " rrm.RatePlanId = rp.idRatePlan "+
				// " and hotelId = '"+request.getParameter("hotelId")+"'");
				// roomList = new ArrayList<Room>();
				// while (rs.next()) {
				// roomList.add(new Room(rs.getInt("idRoomRateMap"),
				// rs.getInt("HotelId"), rs.getInt("RoomTypeId") ,
				// rs.getInt("RatePlanId") ,
				// rs.getString("RoomType") , rs.getString("RoomTypeDesciption")
				// , rs.getString("RatePlan")));
				// }

			}
			request.setAttribute("message", message);
			request.setAttribute("cartDetails", cartDetails);
			RequestDispatcher rd = request
					.getRequestDispatcher("/view/Cart.jsp");
			rd.forward(request, response);
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException sqle) {
				 sqle.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}

		}
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private String generateCart(HttpServletRequest request) {
		String hotelId = request.getParameter("hotelId");
		String roomId = request.getParameter("roomId");

//		try {
//			URL url = new URL(request.getContextPath() + "/hotels/hotelId=" + hotelId);
//			HttpURLConnection ucon = (HttpURLConnection) url.openConnection();
//			ucon.setDoOutput(true);
//			DataInputStream dis = new DataInputStream(ucon.getInputStream());
//			String line = "";
//			StringBuffer sbr = new StringBuffer();
//			while(null!=(line = dis.readLine())){
//				sbr.append(line);
//			}
//			dis.close();
//
//		} catch (Exception err) {
//			err.printStackTrace();
//		}
		return "{\"cart\": [{\"hotelID\":\"" + hotelId + "\"},{\"roomId\": \""
				+ roomId + "\"}]}";
	}

	private String generateTransactionId(HttpServletRequest request) {
		String hotelId = request.getParameter("hotelId");
		String roomId = request.getParameter("roomId");

		return new Date().getTime() + ":" + hotelId + ":" + roomId;

	}

}
