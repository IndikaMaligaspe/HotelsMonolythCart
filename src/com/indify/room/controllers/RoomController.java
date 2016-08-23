package com.indify.room.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.indify.hotels.models.Hotel;
import com.indify.hotels.models.Room;

/**
 * Servlet implementation class RoomController
 */
@WebServlet("/Rooms/*")
public class RoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private ArrayList<Room> roomList = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost/hotelcartsystem?"
							+ "user=indika&password=123456");

			stmt = conn.createStatement();
			rs = stmt.executeQuery("select idRoomRateMap,HotelId,RoomTypeId,RatePlanId,rt.Name as RoomType,rt.Description as RoomTypeDesciption, "+
									" rp.Name as RatePlan from `hotelcartsystem`.`RoomRateMap` as  rrm "+
									" left join `hotelcartsystem`.`RoomType` as rt on rrm.RoomTypeId = rt.idRoom "+
									" left join `hotelcartsystem`.`RatePlan` as rp on rrm.RatePlanId = rp.idRatePlan "+
									" where rrm.RoomTypeId = rt.idRoom "+
									" and "+
									" rrm.RatePlanId = rp.idRatePlan "+
									" and hotelId = '"+request.getParameter("hotelId")+"'");
			roomList = new ArrayList<Room>();
			while (rs.next()) {
				roomList.add(new Room(rs.getInt("idRoomRateMap"), rs.getInt("HotelId"), rs.getInt("RoomTypeId") ,  rs.getInt("RatePlanId") ,
						rs.getString("RoomType") , rs.getString("RoomTypeDesciption") , rs.getString("RatePlan")));
			}
		    request.setAttribute("roomList", roomList);
			RequestDispatcher rd = request.getRequestDispatcher("/view/roomList.jsp");
		    rd.forward(request, response);
		} catch (Exception err) {
			err.printStackTrace();
		}
		finally{
			try{rs.close();}catch(SQLException sqle){sqle.printStackTrace();}
			try{stmt.close();}catch(SQLException sqle){sqle.printStackTrace();}
			try{conn.close();}catch(SQLException sqle){sqle.printStackTrace();}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
