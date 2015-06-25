package de.tum.in.eist.distance;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceStopData {

	private String street;
	private String city;
	private String countryCode;
	private String region;
	private String postalCode;
	private double latitude;
	private double longitude;
	private List<DistanceAirportData> airports = new ArrayList<DistanceAirportData>();
	private List<DistanceCityData> nearByCities = new ArrayList<DistanceCityData>();
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public List<DistanceAirportData> getAirports() {
		return airports;
	}
	public void setAirports(List<DistanceAirportData> airports) {
		this.airports = airports;
	}
	public List<DistanceCityData> getNearByCities() {
		return nearByCities;
	}
	public void setNearByCities(List<DistanceCityData> nearByCities) {
		this.nearByCities = nearByCities;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	
}
