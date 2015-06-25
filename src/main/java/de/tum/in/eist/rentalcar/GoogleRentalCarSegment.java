package de.tum.in.eist.rentalcar;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import de.tum.in.eist.algorithm.RouteSegment;

public class GoogleRentalCarSegment implements RouteSegment{
	
	private DirectionsData directionsData;
	private PlacesData placesData;

	public GoogleRentalCarSegment(DirectionsData directionData,
			PlacesData placesData) {
		super();
		this.directionsData = directionData;
		this.placesData = placesData;
	}

	public DirectionsData getDirectionData() {
		return directionsData;
	}

	public void setDirectionData(DirectionsData directionData) {
		this.directionsData = directionData;
	}

	public PlacesData getPlacesData() {
		return placesData;
	}

	public void setPlacesData(PlacesData placesData) {
		this.placesData = placesData;
	}

	@Override
	public double price() {
		return 200 * placesData.getResults().get(0).getPrice_level();
	}

	@Override
	public double duration() {
		double duration = 0;
		for(DirectionsLegData leg : directionsData.getRoutes().get(0).getLegs()){
			duration += leg.getDuration().getValue();
		}
		return duration;
	}

	@Override
	public String getJSON() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
}
