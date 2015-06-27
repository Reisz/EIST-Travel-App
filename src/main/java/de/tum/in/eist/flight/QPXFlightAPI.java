package de.tum.in.eist.flight;

import java.io.IOException;
import java.net.URL;
import java.util.List;




import org.codehaus.jackson.map.ObjectMapper;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.inject.Inject;

import de.tum.in.eist.ApiKey;
import de.tum.in.eist.RequestOptions;
import de.tum.in.eist.algorithm.IFlightAPI;
import de.tum.in.eist.algorithm.RouteSegment;

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
		requestData.getSlice().setOrigin(originIATA);
		requestData.getSlice().setDestination(destinationIATA);
		requestData.getSlice().setDate(requestOptions.getDate());
		requestData.getSlice().setPreferredCabin(flightType);
		r.setPayload(mapper.writeValueAsString(requestData).getBytes("UTF-8"));

		HTTPResponse response = service.fetch(r);
		
		return null;
	}

}
