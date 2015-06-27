package de.tum.in.eist.algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.tum.in.eist.Location;
import de.tum.in.eist.RequestOptions;

public class AlgorithmManager {
	
	final static Comparator<Route> CHEAPEST = new Comparator<Route>() {
		@Override
		public int compare(Route o1, Route o2) {
			return o2.getTotalPrice() - o1.getTotalPrice();
		}
	};

	final static Comparator<Route> FASTEST = new Comparator<Route>() {
		@Override
		public int compare(Route o1, Route o2) {
			return o2.getTotalDuration() - o1.getTotalDuration();
		}
	};

	final static Comparator<Route> BALANCED = new Comparator<Route>() {
		@Override
		public int compare(Route o1, Route o2) {
			return o2.getRating() - o1.getRating();
		}
	};
	
	private CityNode origin;
	private List<CityNode> nearOrigin = new ArrayList<CityNode>();
	private AirportNode originAirport;
	
	private CityNode dest;
	private List<CityNode> nearDest = new ArrayList<CityNode>();
	private AirportNode destAirport;
	
	private List<IFlightAPI> flightApis = new ArrayList<IFlightAPI>();
	private List<ICarAPI> carApis = new ArrayList<ICarAPI>();
	private List<ITrainAPI> trainApis = new ArrayList<ITrainAPI>();
	
	public AlgorithmManager(Location origin, Location destination) {
		this.origin = new CityNode(origin);
		this.dest = new CityNode(destination);
	}
	
	public void addNearOrigin(Location l) {
		nearOrigin.add(new CityNode(l));
	}
	
	public void addNearDest(Location l) {
		nearOrigin.add(new CityNode(l));
	}

	public void setOriginAirport(Location l, String iata) {
		this.originAirport = new AirportNode(l, iata);
	}

	public void setDestAirport(Location l, String iata) {
		this.destAirport = new AirportNode(l, iata);
	}
	
	public void addFlightApi(IFlightAPI flightApi) {
		flightApis.add(flightApi);
	}
	
	public void addCarApi(ICarAPI carApi) {
		carApis.add(carApi);
	}
	
	public void addTrainApi(ITrainAPI trainApi) {
		trainApis.add(trainApi);
	}
	
	public List<Route> getRoutes(RequestOptions options) throws IOException {
		establishCityConnections(options);
		establishAirportConnections(options);
		
		List<Route> result = findPaths();
		
		switch(options.getAlgoType()) {
		case Fastest:
			Collections.sort(result, FASTEST);
			break;
		case Cheapest:
			Collections.sort(result, CHEAPEST);
			break;
		case Balanced:
			updateRatings(result);
			Collections.sort(result, BALANCED);
			break;
		}

		return result;
	}
	
	private void establishCityConnections(RequestOptions options) throws IOException {
		//direct connection
		connect(origin, dest, options);
		
		//1 waypoint city near origin
		for(CityNode n : nearOrigin) {
			connect(origin, n, options);
		}
		
		//1 waypoint city near destination
		for(CityNode n : nearDest) {
			connect(origin, n, options);
		}
		
		//2 waypoints one near each city
		for(CityNode n1 : nearOrigin) {
			for(CityNode n2 : nearDest) {
				connect(n1, n2, options);
			}
		}
	}
	
	private void establishAirportConnections(RequestOptions options) throws IOException {
		if(options.isFlightEnabled()) {
			//routes to and from airports
			connect(origin, originAirport, options);
			connect(destAirport, dest, options);
			
			for(IFlightAPI flightApi : flightApis) {
				List<RouteSegment> route = flightApi.getSegments(originAirport.toString(), destAirport.getIata(), options);
				if(route != null) {
					originAirport.addEdge(new CityEdge(destAirport, route));
				}
			}
		}
	}
	
	private void connect(CityNode e1, CityNode e2, RequestOptions options) throws IOException {
		if(options.isCarEnabled()) {
			for(ICarAPI carApi : carApis) {
				List<RouteSegment> route = carApi.getSegments(e1.getLocation(), e2.getLocation(), options);
				if(route != null) {
					e1.addEdge(new CityEdge(e2, route));
				}
			}
		}
		
		if(options.isTrainEnabled()) {
			for(ITrainAPI trainApi : trainApis) {
				List<RouteSegment> route = trainApi.getSegments(e1.getLocation(), e2.getLocation(), options);
				if(route != null) {
					e1.addEdge(new CityEdge(e2, route));
				}
			}
		}
	}
	
	private List<Route> findPaths() {
		List<Route> result = new ArrayList<Route>();
		origin.extendPath(new ArrayList<RouteSegment>(), result);
		return result;
	}
	
	private void updateRatings(List<Route> routes) {
		if(routes.isEmpty())
			return;
		
		int minPrice = routes.get(0).getTotalPrice(), maxPrice = 0;
		int minDuration = routes.get(0).getTotalDuration(), maxDuration = 0;
		for (Route r : routes) {
			minPrice = Math.min(minPrice, r.getTotalPrice());
			maxPrice = Math.max(maxPrice, r.getTotalPrice());
			minDuration = Math.min(minDuration, r.getTotalDuration());
			maxDuration = Math.max(maxDuration, r.getTotalDuration());
		}
		
		int priceDelta = maxPrice - minPrice;
		int  durationDelta = maxDuration - minDuration;
		
		for(Route r : routes) {
			r.updateRating(minPrice, priceDelta, minDuration, durationDelta);
		}
	}
}
