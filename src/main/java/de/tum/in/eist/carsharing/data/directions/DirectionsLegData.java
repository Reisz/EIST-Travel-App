package de.tum.in.eist.carsharing.data.directions;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import de.tum.in.eist.carsharing.data.GoogleNamedIntegerData;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionsLegData {
	
	private GoogleNamedIntegerData distance;
	private GoogleNamedIntegerData duration;
	
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
	
	

}
