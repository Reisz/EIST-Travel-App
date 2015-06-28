package de.tum.eist.segments;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import de.tum.in.eist.algorithm.RouteSegment;
import de.tum.in.eist.data.directions.DirectionsData;

public class TrainRideSegment implements RouteSegment {

	private DirectionsData data;
	private String type = "train";
	
	@Override
	public int price() {
		return 15 * (data.getRoutes().get(0).getLegs().get(0).getDistance().getValue() / 1000);
	}

	@Override
	public int duration() {
		return data.getRoutes().get(0).getLegs().get(0).getDuration().getValue();
	}

	@Override
	public JsonNode getJSON(ObjectMapper mapper) {
		return mapper.valueToTree(this);
	}

	public DirectionsData getData() {
		return data;
	}

	public void setData(DirectionsData data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
