package de.tum.in.eist.algorithm;

import de.tum.in.eist.Location;

public class AirportNode extends CityNode{

	private String iata;
	
	public AirportNode(Location location, String iata) {
		super(location);
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}
	
}
