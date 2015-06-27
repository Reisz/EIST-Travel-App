package de.tum.in.eist.carsharing.data.directions;

import java.util.ArrayList;
import java.util.List;

public class DirectionsData {
	
	private String status;
	private List<DirectionsRouteData> routes = new ArrayList<DirectionsRouteData>();
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<DirectionsRouteData> getRoutes() {
		return routes;
	}
	public void setRoutes(List<DirectionsRouteData> routes) {
		this.routes = routes;
	}
	
}
