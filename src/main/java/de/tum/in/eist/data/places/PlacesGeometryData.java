package de.tum.in.eist.data.places;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import de.tum.in.eist.data.google.GoogleLocationData;


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
