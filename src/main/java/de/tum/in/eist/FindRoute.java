package de.tum.in.eist;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import com.google.gson.JsonObject;

import de.tum.in.eist.algorithm.RouteSegment;
import de.tum.in.eist.apis.FakeCarsharingAPI;
import de.tum.in.eist.apis.TrainAPI;
import de.tum.in.eist.distance.DistanceAPI;
import de.tum.in.eist.distance.DistanceData;

@Path("/findRoute")
public class FindRoute {
	
	private final DistanceAPI distanceApi;
	private final FakeCarsharingAPI carsharingApi;
	private final TrainAPI trainApi;
	
	@Inject
	public FindRoute(DistanceAPI distanceApi, FakeCarsharingAPI gRentalCarAPI, TrainAPI trainApi){
		this.distanceApi = distanceApi;
		this.carsharingApi = gRentalCarAPI;
		this.trainApi = trainApi;
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
			List<RouteSegment> segments = carsharingApi.getSegments(
					new Location(distanceData.getStops().get(0).getLatitude(), distanceData.getStops().get(0).getLongitude()), 
					new Location(distanceData.getStops().get(1).getLatitude(), distanceData.getStops().get(1).getLongitude()),
					new RequestOptions());
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			ObjectNode response = mapper.createObjectNode();
			ArrayNode route = mapper.createArrayNode();
			
			response.put("status", "ok");
			response.put("data", route);
			
			for(RouteSegment s : segments) {
				route.add(s.getJSON(mapper));
			}
			
			return Response.ok(getJson(response, mapper)).build();
		} catch (Exception e) {
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
	
	public String getJson(JsonNode node, ObjectMapper mapper) throws JsonProcessingException, IOException {
		JsonFactory jFac = new JsonFactory();
		StringWriter w = new StringWriter();
		mapper.writeValue(jFac.createJsonGenerator(new PrintWriter(w)), node);
		return w.toString();
	}
}
