package de.tum.in.eist.flight.result;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultData {

	private ResultsTripsData trips;

	public ResultsTripsData getTrips() {
		return trips;
	}

	public void setTrips(ResultsTripsData trips) {
		this.trips = trips;
	}
	
	
}
