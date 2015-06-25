package de.tum.in.eist.distance;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties({"distances","travel"})
public class DistanceData {
	
	private double distance;
	private List<DistanceStopData> stops = new ArrayList<DistanceStopData>();
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public List<DistanceStopData> getStops() {
		return stops;
	}
	public void setStops(List<DistanceStopData> stops) {
		this.stops = stops;
	}
	
	
	
}
