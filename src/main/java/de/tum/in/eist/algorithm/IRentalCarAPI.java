package de.tum.in.eist.algorithm;

import java.util.List;
import de.tum.in.eist.Location;

public interface IRentalCarAPI {

	public List<RouteSegment> getSegments(Location origin, Location destination);

}
