package de.tum.in.eist.rentalcar;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

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
