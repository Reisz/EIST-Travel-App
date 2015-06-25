package de.tum.in.eist.algorithm;

import java.io.IOException;
import java.util.List;

import de.tum.in.eist.RequestOptions;

public interface IFlightAPI {

	public List<RouteSegment> getSegments(String originIATA, String destinationIATA, RequestOptions requestOptions) throws IOException;
	
}
