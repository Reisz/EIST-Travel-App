package de.tum.in.eist.carsharing.data.directions;

import de.tum.in.eist.carsharing.data.GoogleLocationData;
import de.tum.in.eist.carsharing.data.GoogleNamedIntegerData;

public class DirectionsStepData {

	private GoogleNamedIntegerData duration;
	private GoogleNamedIntegerData distance;
	private GoogleLocationData start_location;
	private GoogleLocationData end_location;
	private String html_instructions;
	private DirectionsPolylineData polyline;
	
	public GoogleNamedIntegerData getDuration() {
		return duration;
	}
	public void setDuration(GoogleNamedIntegerData duration) {
		this.duration = duration;
	}
	public GoogleNamedIntegerData getDistance() {
		return distance;
	}
	public void setDistance(GoogleNamedIntegerData distance) {
		this.distance = distance;
	}
	public GoogleLocationData getStart_location() {
		return start_location;
	}
	public void setStart_location(GoogleLocationData start_location) {
		this.start_location = start_location;
	}
	public GoogleLocationData getEnd_location() {
		return end_location;
	}
	public void setEnd_location(GoogleLocationData end_location) {
		this.end_location = end_location;
	}
	public String getHtml_instructions() {
		return html_instructions;
	}
	public void setHtml_instructions(String html_instructions) {
		this.html_instructions = html_instructions;
	}
	public DirectionsPolylineData getPolyline() {
		return polyline;
	}
	public void setPolyline(DirectionsPolylineData polyline) {
		this.polyline = polyline;
	}
	
	
}