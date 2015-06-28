package de.tum.in.eist.distance;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;

import de.tum.in.eist.Location;
import de.tum.in.eist.URLFetchServiceHelper;

import javax.inject.Inject;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class DistanceAPI {
	
	final static Comparator<DistanceCityData> CLOSEST = new Comparator<DistanceCityData>() {
		@Override
		public int compare(DistanceCityData o1, DistanceCityData o2) {
			return o1.getDistance() - o2.getDistance();
		}
	};
	
	private final URLFetchService service;
	
	@Inject
	public DistanceAPI(URLFetchService service){
		this.service = service;
	}
	
	public DistanceData getDistanceData(String origin, String destination) throws IOException{	
		URL url =
		        new URL("http://www.distance24.org/route.json?stops=" + origin + "|" + destination);
		HTTPRequest request = new HTTPRequest(url, HTTPMethod.GET,FetchOptions.Builder.withDeadline(60));
		HTTPResponse response = service.fetch(request);
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.readValue(URLFetchServiceHelper.toString(response), DistanceData.class);
	}
	
	public List<Location> getNearbyLocations(DistanceStopData city) throws JsonParseException, JsonMappingException, IOException {
		if(city.getNearByCities().isEmpty())
			return new ArrayList<Location>();
		
		Collections.sort(city.getNearByCities(), CLOSEST);
		
		StringBuilder requestString = new StringBuilder();
		requestString.append(city.getNearByCities().get(0).getCity());
		for(int i = 1; i < 2 && i < city.getNearByCities().size(); i++) {
			requestString.append("|");
			requestString.append(city.getNearByCities().get(0).getCity());
		}
		
		URL url =
		        new URL("http://www.distance24.org/route.json?stops=" + requestString.toString());
		HTTPRequest request = new HTTPRequest(url, HTTPMethod.GET,FetchOptions.Builder.withDeadline(60));
		HTTPResponse response = service.fetch(request);
		ObjectMapper mapper = new ObjectMapper();
		
		DistanceData dd = mapper.readValue(URLFetchServiceHelper.toString(response), DistanceData.class);
		ArrayList<Location> result = new ArrayList<Location>();
		
		for(DistanceStopData dsd : dd.getStops())
			result.add(new Location(dsd.getLatitude(), dsd.getLongitude()));
		
		return result;
	}
	
	public Location getAirportLocation(String iata) throws JsonParseException, JsonMappingException, IOException {
		URL url =
		        new URL("http://www.distance24.org/route.json?stops=" + iata);
		HTTPRequest request = new HTTPRequest(url, HTTPMethod.GET,FetchOptions.Builder.withDeadline(60));
		HTTPResponse response = service.fetch(request);
		ObjectMapper mapper = new ObjectMapper();
		
		DistanceData dd = mapper.readValue(URLFetchServiceHelper.toString(response), DistanceData.class);
		
		return new Location(dd.getStops().get(0).getLatitude(), dd.getStops().get(0).getLongitude());
	}
}
