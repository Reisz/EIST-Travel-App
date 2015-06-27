package de.tum.in.eist;

public class RequestOptions {
	public enum AlgoType {
		Fastest, Balanced, Cheapest
	}
	public enum CarType{
		Small, Medium, Large
	}
	public enum TrainType{
		FirstClass, SecondClass
	}
	public enum FlightType{
		FirstClass, Business, Economy
	}
	
	private AlgoType algoType;
	private CarType carType;
	private TrainType trainType;
	private FlightType flightType;
	private int personCount;
	
	private boolean carEnabled = true;
	private boolean trainEnabled = true;
	private boolean flightEnabled = true;
	
	private String date;
	
	private int maxWalkingDistance = 5000;
	public AlgoType getAlgoType() {
		return algoType;
	}
	public void setAlgoType(AlgoType algoType) {
		this.algoType = algoType;
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
	public boolean isCarEnabled() {
		return carEnabled;
	}
	public void setCarEnabled(boolean carEnabled) {
		this.carEnabled = carEnabled;
	}
	public boolean isTrainEnabled() {
		return trainEnabled;
	}
	public void setTrainEnabled(boolean trainEnabled) {
		this.trainEnabled = trainEnabled;
	}
	public boolean isFlightEnabled() {
		return flightEnabled;
	}
	public void setFlightEnabled(boolean flightEnabled) {
		this.flightEnabled = flightEnabled;
	}
}
