package de.tum.eist.segments;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import de.tum.in.eist.algorithm.RouteSegment;
import de.tum.in.eist.flight.result.ResultData;
import de.tum.in.eist.flight.result.ResultsTripsTripOptionSliceData;

public class QPXFlightSegment implements RouteSegment{

	private ResultData data;
	private String type = "plane";
	
	@Override
	public int price() {
		String price = data.getTrips().getTripOption().get(0).getPricing().get(0).getSaleTotal();
		Pattern pattern = Pattern.compile("\\d");
		Matcher matcher = pattern.matcher(price);
		matcher.find();
		double price_final = Double.parseDouble(price.substring(matcher.start())); // USD156.85
		price_final *= 100; //in cents
		return (int) price_final;
	}

	@Override
	public int duration() {
		ArrayList<ResultsTripsTripOptionSliceData> slice = (ArrayList) data.getTrips().getTripOption().get(0).getSlice();
		int duration = 0;
		for(ResultsTripsTripOptionSliceData res : slice){
			duration += res.getDuration();
		}
		return duration;
	}

	@Override
	public JsonNode getJSON(ObjectMapper mapper) {
		return mapper.valueToTree(this);
	}

	public ResultData getData() {
		return data;
	}

	public void setData(ResultData data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
