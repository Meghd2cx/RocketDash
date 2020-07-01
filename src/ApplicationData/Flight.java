package ApplicationData;

import java.util.Date;

public class Flight {

	
	private int flightID = 0;
	private int rocketID;
	private String location;
	private int altitude;
	private double time;
	private String stability;
	private String notes;
	private Date flightDate;
	private int windSpeed;
	private String windDirection;
	private int temperature;
	private int humidity;
	
	
	public Flight() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Flight(int flightID, int rocketID, String location, int altitude, double time, String stability,
			String notes, Date flightDate, int windSpeed, String windDirection, int temperature, int humidity) {
		this.flightID = flightID;
		this.rocketID = rocketID;
		this.location = location;
		this.altitude = altitude;
		this.time = time;
		this.stability = stability;
		this.notes = notes;
		this.flightDate = flightDate;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.temperature = temperature;
		this.humidity = humidity;
	}


	public int getFlightID() {
		return flightID;
	}
	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}
	public int getRocketID() {
		return rocketID;
	}
	public void setRocketID(int rocketID) {
		this.rocketID = rocketID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getAltitude() {
		return altitude;
	}
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	
	public String getStability() {
		return stability;
	}
	public void setStability(String stability) {
		this.stability = stability;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}
	public int getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(int windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	
	
	
	
	
	
	
}
