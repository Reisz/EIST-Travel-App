package de.tum.in.eist.rentalcar;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;

import de.tum.in.eist.ApiKey;
import de.tum.in.eist.Location;
import de.tum.in.eist.RequestOptions;
import de.tum.in.eist.URLFetchServiceHelper;
import de.tum.in.eist.algorithm.IRentalCarAPI;
import de.tum.in.eist.algorithm.RouteSegment;

public class GoogleRentalCarAPI implements IRentalCarAPI {
	
	private final URLFetchService service;
	
	public GoogleRentalCarAPI(URLFetchService service){
		this.service = service;
	}

	@Override
	public List<RouteSegment> getSegments(Location origin, Location destination, RequestOptions requestOptions) throws IOException  {
		URL url =
				new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location="+ origin.getLatitude() +
						"," + origin.getLongitude() + "&types=car_rental&key=" + ApiKey.GOOGLE);
		
		HTTPResponse response = service.fetch(url);
		ObjectMapper mapper = new ObjectMapper();
		
		PlacesData placesData = mapper.readValue(URLFetchServiceHelper.toString(response), PlacesData.class);
		
		url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=" + placesData.getResults().get(0).getGeometry().getLocation().getLat()
				+ "," + placesData.getResults().get(0).getGeometry().getLocation().getLng() + "&destination=" +
				destination.getLatitude() + "," + destination.getLongitude());
		
		response = service.fetch(url);
		DirectionsData directionsData = mapper.readValue(URLFetchServiceHelper.toString(response), DirectionsData.class);
		ArrayList<RouteSegment> result = new ArrayList<RouteSegment>();
		result.add(new GoogleRentalCarSegment(directionsData, placesData));
		return result;
	}

	
}
