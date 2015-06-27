package de.tum.in.eist.carsharing.data.places;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import de.tum.in.eist.carsharing.data.GoogleLocationData;


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
