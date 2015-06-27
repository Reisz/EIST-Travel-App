package de.tum.in.eist.carsharing.data.places;

import java.util.List;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"html_attributions", "next_page_token", })
public class PlacesData {
	private String status;
	private List<PlacesResultData> results = new ArrayList<PlacesResultData>();
	private String error_message;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<PlacesResultData> getResults() {
		return results;
	}
	public void setResults(List<PlacesResultData> results) {
		this.results = results;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
}
