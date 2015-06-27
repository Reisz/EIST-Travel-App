package de.tum.in.eist.flight.result;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import de.tum.in.eist.flight.QPXNamedCode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsTripsDataData {

	private List<QPXNamedCode> airport;
	private List<QPXNamedCode> city;
	
	public List<QPXNamedCode> getAirport() {
		return airport;
	}
	public void setAirport(List<QPXNamedCode> airport) {
		this.airport = airport;
	}
	public List<QPXNamedCode> getCity() {
		return city;
	}
	public void setCity(List<QPXNamedCode> city) {
		this.city = city;
	}

	
}
