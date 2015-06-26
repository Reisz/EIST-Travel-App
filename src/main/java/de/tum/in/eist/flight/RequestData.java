package de.tum.in.eist.flight;

public class RequestData {

	private RequestPassengersData passengers;
	private RequestSliceData slice;
	private int solutions = 1;
	
	public RequestPassengersData getPassengers() {
		return passengers;
	}
	public void setPassengers(RequestPassengersData passengers) {
		this.passengers = passengers;
	}
	public RequestSliceData getSlice() {
		return slice;
	}
	public void setSlice(RequestSliceData slice) {
		this.slice = slice;
	}
	public int getSolutions() {
		return solutions;
	}
	public void setSolutions(int solutions) {
		this.solutions = solutions;
	}
	
	
}
