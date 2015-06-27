package de.tum.in.eist.carsharing.data.directions;

import de.tum.in.eist.carsharing.data.GoogleLocationData;

public class DirectionsBoundsData {

	private GoogleLocationData northeast;
	private GoogleLocationData southwest;
	
	public GoogleLocationData getNortheast() {
		return northeast;
	}
	public void setNortheast(GoogleLocationData northeast) {
		this.northeast = northeast;
	}
	public GoogleLocationData getSouthwest() {
		return southwest;
	}
	public void setSouthwest(GoogleLocationData southwest) {
		this.southwest = southwest;
	}
	
	
}
