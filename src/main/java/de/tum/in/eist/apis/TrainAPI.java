package de.tum.in.eist.apis;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.inject.Inject;

import de.tum.eist.segments.TaxiSegment;
import de.tum.eist.segments.WalkingSegment;
import de.tum.eist.segments.TrainRideSegment;
import de.tum.in.eist.ApiKey;
import de.tum.in.eist.Location;
import de.tum.in.eist.RequestOptions;
import de.tum.in.eist.URLFetchServiceHelper;
import de.tum.in.eist.algorithm.ITrainAPI;
import de.tum.in.eist.algorithm.RouteSegment;
import de.tum.in.eist.data.directions.DirectionsData;
import de.tum.in.eist.data.places.PlacesData;

public class TrainAPI implements ITrainAPI {

	private final URLFetchService service;

	@Inject
	public TrainAPI(URLFetchService service) {
		this.service = service;
	}

	@Override
	public List<RouteSegment> getSegments(Location origin,
			Location destination, RequestOptions requestOptions)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		HTTPResponse response;
		URL url;

		// find nearby trainstation
		url = new URL(
				"https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location=" + origin.getLatitude() + ","
						+ origin.getLongitude() + "&types=train_station&radius=50000&key=" + ApiKey.GOOGLE);
		response = service.fetch(url);
		PlacesData trainStationOrigin = mapper.readValue(
				URLFetchServiceHelper.toString(response), PlacesData.class);
		if (trainStationOrigin.getResults().isEmpty())
			return null;
		
		// find trainstation nearby destination
		url = new URL(
				"https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location=" + destination.getLatitude() + ","
						+ destination.getLongitude() + "&types=train_station&radius=50000&key=" + ApiKey.GOOGLE);
		response = service.fetch(url);
		PlacesData trainStationDestination = mapper.readValue(
				URLFetchServiceHelper.toString(response), PlacesData.class);
		if (trainStationDestination.getResults().isEmpty())
			return null;
		
		//get walking directions from origin to nearest trainstation
		url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=" + origin.commaSeperated()
						+ "&destination=" + trainStationOrigin.getResults().get(0).getGeometry().getLocation().getLat()
						+ "," + trainStationOrigin.getResults().get(0).getGeometry().getLocation().getLng() + "&mode=walking" + "&key=" + ApiKey.GOOGLE);
		response = service.fetch(url);
		DirectionsData walkOrigin = mapper.readValue(URLFetchServiceHelper.toString(response), DirectionsData.class);
		
		//get a taxi if walk is too long or not found
			DirectionsData taxiOrigin = null;
			if(walkOrigin.getRoutes().isEmpty() || walkOrigin.getRoutes().get(0).getLegs().isEmpty() ||
					walkOrigin.getRoutes().get(0).getLegs().get(0).getDistance().getValue() > requestOptions.getMaxWalkingDistance()) {
				url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=" + origin.commaSeperated()
						+ "&destination=" + trainStationOrigin.getResults().get(0).getGeometry().getLocation().getLat()
						+ "," + trainStationOrigin.getResults().get(0).getGeometry().getLocation().getLng() + "&key=" + ApiKey.GOOGLE);
				response = service.fetch(url);
				taxiOrigin = mapper.readValue(URLFetchServiceHelper.toString(response), DirectionsData.class);
				if(taxiOrigin.getRoutes().isEmpty() || taxiOrigin.getRoutes().get(0).getLegs().isEmpty())
					return null;
				}
		
		//use the train
		url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin="
				+ trainStationOrigin.getResults().get(0).getGeometry().getLocation().getLat()
				+ "," + trainStationOrigin.getResults().get(0).getGeometry().getLocation().getLng()
				+ "&destination=" + trainStationDestination.getResults().get(0).getGeometry().getLocation().getLat()
				+ "," + trainStationDestination.getResults().get(0).getGeometry().getLocation().getLng() 
				+ "&mode=transit&transit_mode=train" + "&key=" + ApiKey.GOOGLE);
			response = service.fetch(url);
			DirectionsData train = mapper.readValue(URLFetchServiceHelper.toString(response), DirectionsData.class);
			if(train.getRoutes().isEmpty() || train.getRoutes().get(0).getLegs().isEmpty())
				return null;	
			
		//get walking directions from train station near destination to destination
		url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=" 
				+ trainStationDestination.getResults().get(0).getGeometry().getLocation().getLat()
				+ "," + trainStationDestination.getResults().get(0).getGeometry().getLocation().getLng()
				+ "&destination=" + destination.commaSeperated() + "&mode=walking" + "&key=" + ApiKey.GOOGLE);
			response = service.fetch(url);
			DirectionsData walkDestination = mapper.readValue(URLFetchServiceHelper.toString(response), DirectionsData.class);
			
		//get a taxi if walk is too long or not found
		DirectionsData taxiDestination = null;
		if(walkDestination.getRoutes().isEmpty() || walkDestination.getRoutes().get(0).getLegs().isEmpty() ||
				walkDestination.getRoutes().get(0).getLegs().get(0).getDistance().getValue() > requestOptions.getMaxWalkingDistance()) {
			url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=" 
					+ trainStationDestination.getResults().get(0).getGeometry().getLocation().getLat()
					+ "," + trainStationDestination.getResults().get(0).getGeometry().getLocation().getLng()
					+ "&destination=" + destination.commaSeperated() + "&key=" + ApiKey.GOOGLE );
			response = service.fetch(url);
			taxiDestination = mapper.readValue(URLFetchServiceHelper.toString(response), DirectionsData.class);
			if(taxiDestination.getRoutes().isEmpty() || taxiDestination.getRoutes().get(0).getLegs().isEmpty())
				return null;
			}	
		
		return buildResponse(walkOrigin, taxiOrigin, train, walkDestination, taxiDestination);

	}
	
	private List<RouteSegment> buildResponse(DirectionsData walkOrigin, DirectionsData taxiOrigin, DirectionsData train, DirectionsData walkDestination, DirectionsData taxiDestination){
		ArrayList<RouteSegment> result = new ArrayList<RouteSegment>();
		
		if(taxiOrigin == null){
			WalkingSegment wOrSegment = new WalkingSegment();
			wOrSegment.setData(walkOrigin);
			result.add(wOrSegment);
		} else {
			TaxiSegment tOrSegment = new TaxiSegment();
			tOrSegment.setData(taxiOrigin);
			result.add(tOrSegment);
		}
		
		TrainRideSegment trSegment = new TrainRideSegment();
		trSegment.setData(train);
		result.add(trSegment);
		
		if(taxiDestination == null){
			WalkingSegment wDeSegment = new WalkingSegment();
			wDeSegment.setData(walkDestination);
			result.add(wDeSegment);
		} else {
			TaxiSegment tDeSegment = new TaxiSegment();
			tDeSegment.setData(taxiDestination);
			result.add(tDeSegment);
		}
		
		return result;
		
		
	}

}
