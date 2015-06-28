package de.tum.in.eist.apis;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.inject.Inject;

import de.tum.eist.segments.CarsharingPickupSegment;
import de.tum.eist.segments.DrivingSegment;
import de.tum.in.eist.ApiKey;
import de.tum.in.eist.Location;
import de.tum.in.eist.RequestOptions;
import de.tum.in.eist.URLFetchServiceHelper;
import de.tum.in.eist.algorithm.ICarAPI;
import de.tum.in.eist.algorithm.RouteSegment;
import de.tum.in.eist.data.directions.DirectionsData;

public class CarAPI implements ICarAPI{

	private final URLFetchService service;
	
	@Inject
	public CarAPI(URLFetchService service){
		this.service = service;
	}

	@Override
	public List<RouteSegment> getSegments(Location origin,
			Location destination, RequestOptions requestOptions)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		HTTPResponse response;
		URL url;
		
		//use the car
			url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin="
					+ origin.commaSeperated() 
					+ "&destination=" + destination.commaSeperated() + "&key=" + ApiKey.GOOGLE);
			response = service.fetch(url);
			DirectionsData drive = mapper.readValue(URLFetchServiceHelper.toString(response), DirectionsData.class);
			if(drive.getRoutes().isEmpty() || drive.getRoutes().get(0).getLegs().isEmpty())
				return null;
				
			return buildResponse(drive);			
	}	

	private List<RouteSegment> buildResponse(DirectionsData drive) {
		ArrayList<RouteSegment> result = new ArrayList<RouteSegment>();
	
		CarsharingPickupSegment pSegment = new CarsharingPickupSegment();
		result.add(pSegment);
	
		DrivingSegment dSegment = new DrivingSegment();
		dSegment.setData(drive);
		result.add(dSegment);
	
		return result;
}
	
	
}
