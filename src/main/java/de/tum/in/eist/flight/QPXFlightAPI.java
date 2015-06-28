package de.tum.in.eist.flight;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.inject.Inject;

import de.tum.eist.segments.QPXFlightSegment;
import de.tum.in.eist.ApiKey;
import de.tum.in.eist.RequestOptions;
import de.tum.in.eist.URLFetchServiceHelper;
import de.tum.in.eist.algorithm.IFlightAPI;
import de.tum.in.eist.algorithm.RouteSegment;
import de.tum.in.eist.flight.request.RequestData;
import de.tum.in.eist.flight.request.RequestSliceData;
import de.tum.in.eist.flight.result.ResultData;

public class QPXFlightAPI implements IFlightAPI {

	private final URLFetchService service;
	
	@Inject
	public QPXFlightAPI(URLFetchService service) {
		super();
		this.service = service;
	}

	@Override
	public List<RouteSegment> getSegments(String originIATA, String destinationIATA, RequestOptions requestOptions)
			throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		URL url = new URL(
				"https://www.googleapis.com/qpxExpress/v1/trips/search?key="+ ApiKey.GOOGLE);
		
		HTTPRequest r = new HTTPRequest(url, HTTPMethod.POST);
		r.addHeader(new HTTPHeader("Content-Type", "application/json"));
		
		String flightType = "COACH";
		switch(requestOptions.getFlightType()) {
		case FirstClass:
			flightType = "FIRST";
		case Business:
			flightType = "BUSINESS";
		case Economy:
			flightType = "COACH";
		}
		
		RequestData requestData = new RequestData();
		requestData.getPassengers().setAdultCount(requestOptions.getPersonCount());
		RequestSliceData slice = new RequestSliceData();
		slice.setOrigin(originIATA);
		slice.setDestination(destinationIATA);
		slice.setDate(requestOptions.getDate());
		slice.setPreferredCabin(flightType);
		requestData.getSlice().add(slice);
		r.setPayload(mapper.writeValueAsString(requestData).getBytes("UTF-8"));

		HTTPResponse response = service.fetch(r);
		ResultData response_result = mapper.readValue(URLFetchServiceHelper.toString(response), ResultData.class);
		List<RouteSegment> result = new ArrayList<RouteSegment>();
		
		//iterate over result and get sum of distance and duration of the segments
		//add them to the list of route segments and return the list
		
		if(response_result.getTrips() == null)
			return null;
		
		QPXFlightSegment flSegment = new QPXFlightSegment();
		flSegment.setData(response_result);
		result.add(flSegment);		
		
		return result;
		
	}

}
