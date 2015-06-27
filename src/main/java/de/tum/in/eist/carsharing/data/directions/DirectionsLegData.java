package de.tum.in.eist.carsharing.data.directions;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import de.tum.in.eist.carsharing.data.GoogleLocationData;
import de.tum.in.eist.carsharing.data.GoogleNamedIntegerData;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionsLegData {
	private GoogleNamedIntegerData distance;
	private GoogleNamedIntegerData duration;
	private String start_address;
	private GoogleLocationData start_location;
	private String end_address;
	private GoogleLocationData end_location;
	private List<DirectionsStepData> steps;
	
	public GoogleNamedIntegerData getDistance() {
		return distance;
	}
	public void setDistance(GoogleNamedIntegerData distance) {
		this.distance = distance;
	}
	public GoogleNamedIntegerData getDuration() {
		return duration;
	}
	public void setDuration(GoogleNamedIntegerData duration) {
		this.duration = duration;
	}
	public String getStart_address() {
		return start_address;
	}
	public void setStart_address(String start_address) {
		this.start_address = start_address;
	}
	public GoogleLocationData getStart_location() {
		return start_location;
	}
	public void setStart_location(GoogleLocationData start_location) {
		this.start_location = start_location;
	}
	public String getEnd_address() {
		return end_address;
	}
	public void setEnd_address(String end_address) {
		this.end_address = end_address;
	}
	public GoogleLocationData getEnd_location() {
		return end_location;
	}
	public void setEnd_location(GoogleLocationData end_location) {
		this.end_location = end_location;
	}
	public List<DirectionsStepData> getSteps() {
		return steps;
	}
	public void setSteps(List<DirectionsStepData> steps) {
		this.steps = steps;
	}
}
