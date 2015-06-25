package de.tum.in.eist;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.JsonObject;

import de.tum.in.eist.distance.DistanceAPI;
import de.tum.in.eist.distance.DistanceData;

@Path("/findRoute")
public class FindRoute {
	
	private final DistanceAPI distanceApi;
	
	@Inject
	public FindRoute(DistanceAPI distanceApi){
		this.distanceApi = distanceApi;
	}
	
	/**
	 * 
	 * @param origin
	 * @param destination
	 * @param balance 0 = cheapest, 1 = balanced, 2 = fastest
	 * @return
	 */
	@GET
	@Path("/{origin}/{destination}/{balance}")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response findRoute(
		@PathParam("origin") String origin,
		@PathParam("destination") String destination,
		@PathParam("balance") int balance) {
			
		try {
			DistanceData distanceData = distanceApi.getDistanceData(origin, destination);
			ObjectMapper mapper = new ObjectMapper();
			
			return Response.ok(mapper.writeValueAsString(distanceData)).build();
		} catch (IOException e) {
			return Response.ok(generateExceptionJson(e)).build();
		}
	}
	
	public String generateExceptionJson(Exception e){
		StringWriter w = new StringWriter();
		e.printStackTrace(new PrintWriter(w));
		JsonObject response = new JsonObject();
		response.addProperty("status", "exception");
		response.addProperty("message", w.toString());
		return response.toString();
		
	}
}
