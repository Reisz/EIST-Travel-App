package de.tum.in.eist.data.places;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacesResultData {
	private PlacesGeometryData geometry;
	private String name;
	private int price_level;
	private double rating;
	
	public PlacesGeometryData getGeometry() {
		return geometry;
	}
	public void setGeometry(PlacesGeometryData geometry) {
		this.geometry = geometry;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice_level() {
		return price_level;
	}
	public void setPrice_level(int price_level) {
		this.price_level = price_level;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
}
