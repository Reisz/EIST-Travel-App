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
		rating = ((totalPrice - minPrice) / priceDelta) + ((totalDuration - minDuration) / durationDelta);
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
}
