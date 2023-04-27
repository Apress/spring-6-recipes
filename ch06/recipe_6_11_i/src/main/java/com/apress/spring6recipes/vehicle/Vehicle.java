package com.apress.spring6recipes.vehicle;

import java.util.Objects;

public class Vehicle {

	private String vehicleNo;
	private String color;
	private int wheel;
	private int seat;

	public Vehicle() {}

	public Vehicle(String vehicleNo, String color, int wheel, int seat) {
		this.vehicleNo = vehicleNo;
		this.color = color;
		this.wheel = wheel;
		this.seat = seat;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public int getWheel() {
		return wheel;
	}

	public void setWheel(int wheel) {
		this.wheel = wheel;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (vehicleNo != null && o instanceof Vehicle vehicle) {
			return Objects.equals(this.vehicleNo, vehicle.vehicleNo);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(vehicleNo);
	}

	@Override
	public String toString() {
		var fmt = "Vehicle [vehicleNo='%s', color='%s', wheel=%d, seat=%d]";
		return String.format(fmt, vehicleNo, color, wheel, seat);
	}

}
