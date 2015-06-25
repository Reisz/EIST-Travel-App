package de.tum.in.eist;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/findRoute")
public class FindRoute {
	
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
		
		//TODO logic
		
		return Response.ok("{\"status\":\"ok\", \"data\":[0,9,8,7,6,5,4,3,2,1]}").build();
	}
}
