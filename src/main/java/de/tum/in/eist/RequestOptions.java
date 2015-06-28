package de.tum.in.eist;


public class RequestOptions {
	public enum AlgoType {
		Fastest, Balanced, Cheapest
	}
	public enum TrainType{
		FirstClass, SecondClass
	}
	public enum FlightType{
		FirstClass, Business, Economy
	}
	
	private AlgoType algoType;
	private TrainType trainType;
	private FlightType flightType;
	private int personCount;
	
	private boolean carEnabled;
	private boolean trainEnabled;
	private boolean flightEnabled;
	
	private String date;
	
	private int maxWalkingDistance = 5000;
	
	public RequestOptions(int balance, boolean carEnabled, boolean trainEnabled, int trainClass, boolean planeEnabled, int planeClass) {
		switch (balance) {
		case 0:
			this.algoType = AlgoType.Cheapest;
			break;
		case 2:
			this.algoType = AlgoType.Fastest;
			break;
		default:
			this.algoType = AlgoType.Balanced;
		}
		
		this.carEnabled = carEnabled;
		
		this.trainEnabled = trainEnabled;
		switch(trainClass) {
		case 0: 
			this.trainType = TrainType.FirstClass;
			break;
		default:
			this.trainType = TrainType.SecondClass;
		}
		
		this.flightEnabled = planeEnabled;
		switch (planeClass) {
		case 0:
			this.flightType = FlightType.FirstClass;
			break;
		case 2:
			this.flightType = FlightType.Economy;
			break;
		default:
			this.flightType = FlightType.Business;
		}
	}
	
	public AlgoType getAlgoType() {
		return algoType;
	}
	public void setAlgoType(AlgoType algoType) {
		this.algoType = algoType;
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
