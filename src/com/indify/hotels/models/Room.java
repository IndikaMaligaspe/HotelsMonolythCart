package com.indify.hotels.models;

public class Room {
	int idRoomRateMap = 0;
	int HotelId = 0;
	int RoomTypeId = 0;
	int RatePlanId = 0;
	String RoomTypeName = null;
	String RoomTypeDescription = null;
	String RatePlan = null;

	public Room(int idRoomRateMap, int HotelId, int RoomTypeId, int RatePlanId,
			String RoomTypeName, String RoomTypeDescription, String RatePlan) {
          this.idRoomRateMap = idRoomRateMap;
          this.HotelId = HotelId;
          this.RoomTypeId = RoomTypeId;
          this.RatePlanId = RatePlanId;
          this.RoomTypeName = RoomTypeName;
          this.RoomTypeDescription = RoomTypeDescription;
          this.RatePlan = RatePlan;
	}

	public int getIdRoomRateMap() {
		return idRoomRateMap;
	}

	public void setIdRoomRateMap(int idRoomRateMap) {
		this.idRoomRateMap = idRoomRateMap;
	}

	public int getHotelId() {
		return HotelId;
	}

	public void setHotelId(int hotelId) {
		HotelId = hotelId;
	}

	public int getRoomTypeId() {
		return RoomTypeId;
	}

	public void setRoomTypeId(int roomTypeId) {
		RoomTypeId = roomTypeId;
	}

	public int getRatePlanId() {
		return RatePlanId;
	}

	public void setRatePlanId(int ratePlanId) {
		RatePlanId = ratePlanId;
	}

	public String getRoomTypeDescription() {
		return RoomTypeDescription;
	}

	public void setRoomTypeDescription(String roomTypeDescription) {
		RoomTypeDescription = roomTypeDescription;
	}

	public String getRatePlan() {
		return RatePlan;
	}

	public void setRatePlan(String ratePlan) {
		RatePlan = ratePlan;
	}
	
	public String RoomTypeName() {
		return this.RoomTypeName;
	}
	

}
