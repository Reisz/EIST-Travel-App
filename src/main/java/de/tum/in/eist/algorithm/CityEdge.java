package de.tum.in.eist.algorithm;

import java.util.List;

public class CityEdge {
	private CityNode next;
	private List<RouteSegment> route;
	
	public CityEdge(CityNode next, List<RouteSegment> route) {
		this.next = next;
		this.route = route;
	}

	public CityNode getNext() {
		return next;
	}

	public void setNext(CityNode next) {
		this.next = next;
	}

	public List<RouteSegment> getRoute() {
		return route;
	}

	public void setRoute(List<RouteSegment> route) {
		this.route = route;
	}
}
