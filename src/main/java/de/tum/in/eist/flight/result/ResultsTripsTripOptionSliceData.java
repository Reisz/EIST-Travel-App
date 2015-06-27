package de.tum.in.eist.flight.result;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsTripsTripOptionSliceData {

	private int duration; //in minutes
	private List<ResultsTripsTripOptionSliceSegmentData> segment;
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public List<ResultsTripsTripOptionSliceSegmentData> getSegment() {
		return segment;
	}
	public void setSegment(List<ResultsTripsTripOptionSliceSegmentData> segment) {
		this.segment = segment;
	}
	
	
	
}
