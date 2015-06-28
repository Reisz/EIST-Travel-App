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
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import com.google.gson.JsonObject;

import de.tum.in.eist.algorithm.AlgorithmManager;
import de.tum.in.eist.algorithm.Route;
import de.tum.in.eist.algorithm.RouteSegment;
import de.tum.in.eist.apis.CarAPI;
import de.tum.in.eist.apis.FakeCarsharingAPI;
import de.tum.in.eist.apis.TrainAPI;
import de.tum.in.eist.distance.DistanceAPI;
import de.tum.in.eist.distance.DistanceData;
import de.tum.in.eist.flight.QPXFlightAPI;

@Path("/findRoute")
public class FindRoute {
	
	private final DistanceAPI distanceApi;
	private final FakeCarsharingAPI carsharingApi;
	private final CarAPI carApi;
	private final TrainAPI trainApi;
	private final QPXFlightAPI qpxFlightApi;
	
	@Inject
	public FindRoute(DistanceAPI distanceApi, FakeCarsharingAPI gRentalCarAPI, CarAPI carApi, TrainAPI trainApi, QPXFlightAPI qpxFlightApi){
		this.distanceApi = distanceApi;
		this.carsharingApi = gRentalCarAPI;
		this.carApi = carApi;
		this.trainApi = trainApi;
		this.qpxFlightApi = qpxFlightApi;
	}
	
	/**
	 * 
	 * @param origin
	 * @param destination
	 * @param balance 0 = cheapest, 1 = balanced, 2 = fastest
	 * @return
	 */
	@GET
	@Path("/{origin}/{destination}/{balance}/{carEnabled}/{trainEnabled}/{trainClass}/{planeEnabled}/{planeClass}")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response findRoute(
		@PathParam("origin") String origin,
		@PathParam("destination") String destination,
		@PathParam("balance") int balance,
		@PathParam("carEnabled") boolean carEnabled,
		@PathParam("trainEnabled") boolean trainEnabled,
		@PathParam("trainClass") int trainClass,
		@PathParam("planeEnabled") boolean planeEnabled,
		@PathParam("planeClass") int planeClass) {
		try {
			RequestOptions options = new RequestOptions(balance, carEnabled, trainEnabled, trainClass, planeEnabled, planeClass);
			options.setDate("2015-06-29");
			
			ObjectMapper mapper = new ObjectMapper();

			DistanceData distanceData = distanceApi.getDistanceData(origin, destination);
			
			AlgorithmManager manager = new AlgorithmManager(
					new Location(distanceData.getStops().get(0).getLatitude(), distanceData.getStops().get(0).getLongitude()), 
					new Location(distanceData.getStops().get(1).getLatitude(), distanceData.getStops().get(1).getLongitude()));
			
			for(Location l : distanceApi.getNearbyLocations(distanceData.getStops().get(0)))
				manager.addNearOrigin(l);
			for(Location l : distanceApi.getNearbyLocations(distanceData.getStops().get(1)))
				manager.addNearOrigin(l);
			
			if(!distanceData.getStops().get(0).getAirports().isEmpty()) {
				manager.setOriginAirport(distanceApi.getAirportLocation(distanceData.getStops().get(0).getAirports().get(0).getIata()),
						distanceData.getStops().get(0).getAirports().get(0).getIata());
			}
			if(!distanceData.getStops().get(1).getAirports().isEmpty()) {
				manager.setOriginAirport(distanceApi.getAirportLocation(distanceData.getStops().get(1).getAirports().get(0).getIata()),
						distanceData.getStops().get(1).getAirports().get(0).getIata());
			}
			
			manager.addCarApi(carApi);
			manager.addCarApi(carsharingApi);
			manager.addTrainApi(trainApi);
			manager.addFlightApi(qpxFlightApi);
			
			List<Route> routes = manager.getRoutes(options);
						
			ObjectNode response = mapper.createObjectNode();
			ArrayNode routesNode = mapper.createArrayNode();
			
			if(routes.isEmpty()) {
				response.put("status", "no-route");
				return Response.ok(getJson(response, mapper)).build();
			}
			
			response.put("status", "ok");
			response.put("routes", routesNode);
			
			for(int i = 0; i < 4 && i < routes.size(); i++) {
				Route r = routes.get(i);
				
				ObjectNode route = mapper.createObjectNode();
				route.put("duration", r.durationString());
				route.put("price", r.priceString());
				
				ArrayNode array = mapper.createArrayNode();
				for(RouteSegment s :r.getSegments()) {
					array.add(s.getJSON(mapper));
				}
				route.put("route", array);
				
				routesNode.add(route);
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
