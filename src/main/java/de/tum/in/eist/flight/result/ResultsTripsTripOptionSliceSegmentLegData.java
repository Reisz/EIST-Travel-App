package de.tum.in.eist.flight.result;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsTripsTripOptionSliceSegmentLegData {

	private String arrivalTime;
	private String departureTime;
	private String origin;
	private String destination;
	private int duration;
	private int mileage;
	private int connectionDuration;
	private boolean changePlane;
	
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public int getConnectionDuration() {
		return connectionDuration;
	}
	public void setConnectionDuration(int connectionDuration) {
		this.connectionDuration = connectionDuration;
	}
	public boolean isChangePlane() {
		return changePlane;
	}
	public void setChangePlane(boolean changePlane) {
		this.changePlane = changePlane;
	}
	
	
}
