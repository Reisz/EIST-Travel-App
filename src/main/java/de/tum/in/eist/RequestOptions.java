package de.tum.in.eist;

public class RequestOptions {
	
	private CarType carType;
	private TrainType trainType;
	private FlightType flightType;
	private int personCount;
	
	private String date;
	
	private int maxWalkingDistance = 5000;

	public enum CarType{
		Small, Medium, Large
	}
	public enum TrainType{
		FirstClass, SecondClass
	}
	public enum FlightType{
		FirstClass, Business, Economy
	}
	public CarType getCarType() {
		return carType;
	}
	public void setCarType(CarType carType) {
		this.carType = carType;
	}
	public TrainType getTrainType() {
		return trainType;
	}
	public void setTrainType(TrainType trainType) {
		this.trainType = trainType;
	}
	public FlightType getFlightType() {
		return flightType;
	}
	public void setFlightType(FlightType flightType) {
		this.flightType = flightType;
	}
	public int getPersonCount() {
		return personCount;
	}
	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}
	public int getMaxWalkingDistance() {
		return maxWalkingDistance;
	}
	public void setMaxWalkingDistance(int maxWalkingDistance) {
		this.maxWalkingDistance = maxWalkingDistance;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
