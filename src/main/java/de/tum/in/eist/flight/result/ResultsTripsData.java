package de.tum.in.eist.flight.result;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsTripsData {

	private ResultsTripsDataData data;
	private List<ResultsTripsTripOptionData> tripOption;
	
	public ResultsTripsDataData getData() {
		return data;
	}
	public void setData(ResultsTripsDataData data) {
		this.data = data;
	}
	public List<ResultsTripsTripOptionData> getTripOption() {
		return tripOption;
	}
	public void setTripOption(List<ResultsTripsTripOptionData> tripOption) {
		this.tripOption = tripOption;
	}
	
	
	
}
