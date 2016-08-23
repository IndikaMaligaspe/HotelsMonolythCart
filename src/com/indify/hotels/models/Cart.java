package com.indify.hotels.models;

public class Cart {
	int id = 0;
	String cart = null;
	String type = null;
	String transactionID = null;
	String hotelName = null;
	String roomName = null;
	String ratePlan = null;
	
	
	public Cart(String cart, String type,String transactionID){
		this.cart = cart;
		this.type = type;
		this.transactionID = transactionID;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCart() {
		return cart;
	}
	public void setCart(String cart) {
		this.cart = cart;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRatePlan() {
		return ratePlan;
	}

	public void setRatePlan(String ratePlan) {
		this.ratePlan = ratePlan;
	}
	

	
	
}
