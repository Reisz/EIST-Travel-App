package de.tum.in.eist.apis;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.inject.Inject;

import de.tum.eist.segments.DrivingSegment;
import de.tum.eist.segments.CarsharingPickupSegment;
import de.tum.eist.segments.TaxiSegment;
import de.tum.eist.segments.WalkingSegment;
import de.tum.in.eist.ApiKey;
import de.tum.in.eist.Location;
import de.tum.in.eist.RequestOptions;
import de.tum.in.eist.URLFetchServiceHelper;
import de.tum.in.eist.algorithm.ICarAPI;
import de.tum.in.eist.algorithm.RouteSegment;
import de.tum.in.eist.data.directions.DirectionsData;
import de.tum.in.eist.data.places.PlacesData;

public class FakeCarsharingAPI implements ICarAPI {
	
	private final URLFetchService service;
	
	@Inject
	public FakeCarsharingAPI(URLFetchService service){
		this.service = service;
	}

	@Override
	public List<RouteSegment> getSegments(Location origin, Location destination, RequestOptions requestOptions) throws IOException  {
		ObjectMapper mapper = new ObjectMapper();
		
		HTTPResponse response;
		URL url;
		
		//find nearby train station
		url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location="+ origin.getLatitude() +
						"," + origin.getLongitude() + "&types=train_station&radius=50000&key=" + ApiKey.GOOGLE);
		response = service.fetch(url);
		PlacesData trainStation = mapper.readValue(URLFetchServiceHelper.toString(response), PlacesData.class);
		if(trainStation.getResults().isEmpty())
			return null;
		
		//get walking directions
		url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=" + origin.commaSeperated()
				+ "&destination=" + trainStation.getResults().get(0).getGeometry().getLocation().getLat()
				+ "," + trainStation.getResults().get(0).getGeometry().getLocation().getLng() + "&mode=walking" + "&key=" + ApiKey.GOOGLE);
		response = service.fetch(url);
		DirectionsData walk = mapper.readValue(URLFetchServiceHelper.toString(response), DirectionsData.class);
		
		//get a taxi if walk is too long or not found
		DirectionsData taxi = null;
		if(walk.getRoutes().isEmpty() || walk.getRoutes().get(0).getLegs().isEmpty() ||
				walk.getRoutes().get(0).getLegs().get(0).getDistance().getValue() > requestOptions.getMaxWalkingDistance()) {
			url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=" + origin.commaSeperated()
					+ "&destination=" + trainStation.getResults().get(0).getGeometry().getLocation().getLat()
					+ "," + trainStation.getResults().get(0).getGeometry().getLocation().getLng() + "&key=" + ApiKey.GOOGLE);
			response = service.fetch(url);
			taxi = mapper.readValue(URLFetchServiceHelper.toString(response), DirectionsData.class);
			if(taxi.getRoutes().isEmpty() || taxi.getRoutes().get(0).getLegs().isEmpty())
				return null;
		}
		
		//use the car
		url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin="
				+ trainStation.getResults().get(0).getGeometry().getLocation().getLat()
				+ "," + trainStation.getResults().get(0).getGeometry().getLocation().getLng()
				+ "&destination=" + destination.commaSeperated() + "&key=" + ApiKey.GOOGLE);
		response = service.fetch(url);
		DirectionsData drive = mapper.readValue(URLFetchServiceHelper.toString(response), DirectionsData.class);
		if(drive.getRoutes().isEmpty() || drive.getRoutes().get(0).getLegs().isEmpty())
			return null;
		
		return buildResponse(walk, taxi, drive);
	}

	private List<RouteSegment> buildResponse(DirectionsData walk, DirectionsData taxi, DirectionsData drive) {
		ArrayList<RouteSegment> result = new ArrayList<RouteSegment>();
		
		if(taxi == null) {
			WalkingSegment wSegment = new WalkingSegment();
			wSegment.setData(walk);
			result.add(wSegment);
		} else {
			TaxiSegment tSegment = new TaxiSegment();
			tSegment.setData(taxi);
			result.add(tSegment);
		}
		
		CarsharingPickupSegment pSegment = new CarsharingPickupSegment();
		result.add(pSegment);
		
		DrivingSegment dSegment = new DrivingSegment();
		dSegment.setData(drive);
		result.add(dSegment);
		
		return result;
	}
}
