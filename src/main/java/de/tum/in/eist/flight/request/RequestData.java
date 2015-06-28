package de.tum.in.eist.flight.request;

import java.util.ArrayList;
import java.util.List;

public class RequestData {

	private RequestPassengersData passengers = new RequestPassengersData();
	private List<RequestSliceData> slice = new ArrayList<RequestSliceData>();
	private int solutions = 1;
	
	public RequestPassengersData getPassengers() {
		return passengers;
	}
	public void setPassengers(RequestPassengersData passengers) {
		this.passengers = passengers;
	}
	public List<RequestSliceData> getSlice() {
		return slice;
	}
	public void setSlice(List<RequestSliceData> slice) {
		this.slice = slice;
	}
	public int getSolutions() {
		return solutions;
	}
	public void setSolutions(int solutions) {
		this.solutions = solutions;
	}
	
	
}
