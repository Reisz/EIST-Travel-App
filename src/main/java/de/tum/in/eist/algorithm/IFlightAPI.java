package de.tum.in.eist.algorithm;

import java.util.List;

public interface IFlightAPI {

	public List<RouteSegment> getSegments(String originIATA, String destinationIATA);
	
}
