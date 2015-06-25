package de.tum.in.eist.rentalcar;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacesGeometryData {
	private GoogleLocationData location;

	public GoogleLocationData getLocation() {
		return location;
	}

	public void setLocation(GoogleLocationData location) {
		this.location = location;
	}
}
