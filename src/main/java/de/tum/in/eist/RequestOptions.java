package de.tum.in.eist;

public class RequestOptions {
	
	private CarType carType;
	private TrainType trainType;
	private FlightType flightType;
	private int personCount;

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
}
