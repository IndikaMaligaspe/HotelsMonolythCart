package com.indify.hotels.models;

public class Hotel {
	private int idHotel = 0;
	private String name = null;
	private String address = null;
	private String shortDescrition = null;
	private String thumbNail = null;

	public Hotel(int idHotel,String name,String address,String shortDescrition,String thumbNail){
		this.idHotel = idHotel;
		this.name = name;
		this.address = address;
		this.shortDescrition = shortDescrition;
		this.thumbNail = thumbNail;
	}
	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		address = address;
	}

	public String getShortDescrition() {
		return shortDescrition;
	}

	public void setShortDescrition(String shortDescrition) {
		shortDescrition = shortDescrition;
	}

	public String getThumbNail() {
		return thumbNail;
	}

	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}

}
