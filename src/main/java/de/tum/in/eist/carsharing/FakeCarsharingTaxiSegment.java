package de.tum.in.eist.carsharing;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import de.tum.in.eist.algorithm.RouteSegment;
import de.tum.in.eist.carsharing.data.directions.DirectionsData;

public class FakeCarsharingTaxiSegment implements RouteSegment {

	private DirectionsData data;
	private String type = "cs-taxi";
	
	@Override
	public int price() {
		int distance = data.getRoutes().get(0).getLegs().get(0).getDistance().getValue() / 1000;
		return 350 //boooking
			+ distance * 150 //fare
			+ distance * 15; // 10% tip
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
