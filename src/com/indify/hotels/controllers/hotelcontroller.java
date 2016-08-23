package com.indify.hotels.controllers;

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

/**
 * Servlet implementation class hotelcontroller
 */
@WebServlet("/hotels/*")
public class hotelcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private ArrayList<Hotel> hotelList = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public hotelcontroller() {
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
//		response.getWriter().append("Served at: ")
//				.append(request.getContextPath());
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost/hotelcartsystem?"
							+ "user=indika&password=123456");

			stmt = conn.createStatement();
			rs = stmt.executeQuery("select idHotel,name,address,shortDescription,ThumbnailURL as thumbNail from `hotelcartsystem`.`Hotel`;");
			hotelList = new ArrayList<Hotel>();
			while (rs.next()) {
				hotelList.add(new Hotel(rs.getInt("idHotel"), rs
						.getString("name"), rs.getString("address"), rs
						.getString("shortDescription"), rs
						.getString("thumbNail")));
			}
		    request.setAttribute("hotelList", hotelList);
			RequestDispatcher rd = request.getRequestDispatcher("/view/hotelList.jsp");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

}
