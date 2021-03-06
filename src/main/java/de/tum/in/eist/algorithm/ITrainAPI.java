package de.tum.in.eist.algorithm;

import java.io.IOException;
import java.util.List;

import de.tum.in.eist.Location;
import de.tum.in.eist.RequestOptions;

public interface ITrainAPI {

	public List<RouteSegment> getSegments(Location origin, Location destination, RequestOptions requestOptions) throws IOException;
}
