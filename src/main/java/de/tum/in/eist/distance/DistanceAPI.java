package de.tum.in.eist.distance;

import java.io.IOException;
import java.net.URL;

import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;

import de.tum.in.eist.URLFetchServiceHelper;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;

public class DistanceAPI {
	
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
}
