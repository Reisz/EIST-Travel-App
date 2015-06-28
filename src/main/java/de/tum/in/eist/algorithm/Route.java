package de.tum.in.eist.algorithm;

import java.util.List;

public class Route {
	private List<RouteSegment> segments;
	
	private int totalPrice = 0;
	private int totalDuration = 0;
	private int rating = 0;
	
	public Route(List<RouteSegment> segments) {
		this.segments = segments;
		for(RouteSegment s : segments) {
			totalPrice += s.price();
			totalDuration += s.duration();
		}
	}
	
	public void updateRating(int minPrice, int priceDelta, int minDuration, int durationDelta) {
		rating = 0;
		if(priceDelta != 0)
			rating += ((totalPrice - minPrice) / priceDelta);
		if(durationDelta != 0)
			rating += ((totalDuration - minDuration) / durationDelta);
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public int getTotalDuration() {
		return totalDuration;
	}
	
	public int getRating() {
		return rating;
	}

	public List<RouteSegment> getSegments() {
		return segments;
	}
	
	public String priceString() {
		int p = getTotalPrice();
		return (new StringBuilder()).append("$").append(p / 100).append(".").append(p % 100).toString();
	}
	
	public String durationString() {
		int d = getTotalDuration();
		return String.format("%d:%02d:%02d", d/3600, (d%3600)/60, (d%60));
	}
}
