package de.tum.in.eist.flight;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsTripsTripOptionSliceSegmentData {

	private int duration;
	private String cabin;
	private List<ResultsTripsTripOptionSliceSegmentLegData> leg;
	private int connectionDuration;
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public List<ResultsTripsTripOptionSliceSegmentLegData> getLeg() {
		return leg;
	}
	public void setLeg(List<ResultsTripsTripOptionSliceSegmentLegData> leg) {
		this.leg = leg;
	}
	public int getConnectionDuration() {
		return connectionDuration;
	}
	public void setConnectionDuration(int connectionDuration) {
		this.connectionDuration = connectionDuration;
	}
	
	
}
