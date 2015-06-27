package de.tum.in.eist.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.tum.in.eist.Location;

public class CityNode {
	private Location location;
	private List<CityEdge> edges = new ArrayList<CityEdge>();

	public CityNode(Location location) {
		this.location = location;
	}
	
	public void addEdge(CityEdge edge) {
		edges.add(edge);
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void extendPath(List<RouteSegment> current, List<Route> result) {
		if(edges.isEmpty()) {
			result.add(new Route(current));
		}
		
		for(CityEdge e : edges) {
			List<RouteSegment> next = new ArrayList<RouteSegment>();
			Collections.copy(next, current);
			e.getNext().extendPath(next, result);
		}
	}
}
