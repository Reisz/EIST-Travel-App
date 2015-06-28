package de.tum.eist.segments;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import de.tum.in.eist.algorithm.RouteSegment;

public class CarsharingPickupSegment implements RouteSegment {
	
	private String type = "cs-pickup";
	
	@Override
	public int price() {
		return 0;
	}

	@Override
	public int duration() {
		return 0;
	}

	@Override
	public JsonNode getJSON(ObjectMapper mapper) {
		return mapper.valueToTree(this);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
