package de.tum.in.eist.algorithm;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public interface RouteSegment {

	public int price(); //in USD cents
	public int duration(); //in seconds
	public JsonNode getJSON(ObjectMapper mapper);
	
}
