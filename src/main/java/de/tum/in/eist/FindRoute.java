package de.tum.in.eist;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/findRoute")
public class FindRoute {
	@GET
	@Path("/{origin}/{destination}/{balance}")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response findRoute(
		@PathParam("origin") String origin,
		@PathParam("destination") String destination,
		@PathParam("balance") int balance) {
		
		//TODO logic
		
		return Response.ok("{}").build();
	}
}
