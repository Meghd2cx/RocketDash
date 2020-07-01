package ApplicationData;

import java.text.ParseException;
import java.text.SimpleDateFormat;

//This class is just a way to bypass the TableView's parameterized object form

public class FlightProperty {

	
	private String flightID = "";
	//TODO Change to flight ID here and on the table
	private String location = "";
	private String altitude = "";
	private String time = "";
	private String stability = "";
	private String notes = "";
	private String flightDate = "";
	private String wind = "";
	private String temperature = "";
	private String humidity = "";
	
	public FlightProperty(String flightID, String location, String altitude, String time, String stability,
			String notes, String flightDate, String wind, String temperature, String humidity) {
		this.flightID = flightID;
		this.location = location;
		this.altitude = altitude;
		this.time = time;
		this.stability = stability;
		this.notes = notes;
		this.flightDate = flightDate;
		this.wind = wind;
		this.temperature = temperature;
		this.humidity = humidity;
	}

	

	public FlightProperty(String flightID, String location, String altitude, String time, String stability,
			String notes, String wind, String temperature, String humidity) {
		this.flightID = flightID;
		this.location = location;
		this.altitude = altitude;
		this.time = time;
		this.stability = stability;
		this.notes = notes;
		this.wind = wind;
		this.temperature = temperature;
		this.humidity = humidity;
	}



	public FlightProperty() {
		// TODO Auto-generated constructor stub
	}



	public String getFlightID() {
		return flightID;
	}

	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
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

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	
	//TODO Finish get wind speed function
	//Pulls wind speed from wind string
	public static Flight convertFlightProperty (FlightProperty flightProperty) throws ParseException {
		Flight flight = new Flight();
		
		flight.setRocketID(extractIntegers(flightProperty.getFlightID()));
		flight.setLocation(flightProperty.getLocation());
		flight.setAltitude(extractIntegers(flightProperty.getAltitude()));
		flight.setTime(extractIntegers(flightProperty.getFlightID()));
		flight.setStability(flightProperty.getStability());
		flight.setNotes(flightProperty.getNotes());
		flight.setFlightDate(new SimpleDateFormat("MM/dd/yyyy").parse(flightProperty.getFlightDate()));
		flight.setWindSpeed(extractIntegers(flightProperty.getWindSpeed()));
		if(flightProperty.getWind().toLowerCase().contains("west")) {
			flight.setWindDirection("West");

		}
		else if(flightProperty.getWind().toLowerCase().contains("east")) {
			flight.setWindDirection("East");
		}
		else if(flightProperty.getWind().toLowerCase().contains("north")) {
			flight.setWindDirection("North");
		}
		else if(flightProperty.getWind().toLowerCase().contains("south")) {
			flight.setWindDirection("South");
		}
		flight.setTemperature(extractIntegers(flightProperty.getTemperature()));
		flight.setHumidity(extractIntegers(flightProperty.getHumidity()));
		
		return flight;
	}
	
	private String getWindSpeed() {
		// TODO Auto-generated method stub
		return null;
	}

	public static int extractIntegers(String str) 
    { 
        // Replacing every non-digit number 
        // with a space(" ") 
        str = str.replaceAll("[^\\d]", " "); 
  
        // Remove extra spaces from the beginning 
        // and the ending of the string 
        str = str.trim(); 
  
        // Replace all the consecutive white 
        // spaces with a single space 
        str = str.replaceAll(" +", " "); 
  
        if (str.equals("")) 
            return -1; 
  
        return Integer.parseInt(str); 
    } 
	
	public static double extractDoubles(String str) {
		return Double.parseDouble(str.replaceAll("[^0-9.]", ""));
	}
  
		
}
