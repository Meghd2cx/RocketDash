package ApplicationData;

public class Weather {

	
	private int weatherID;
	private int wind;
	private String units;
	private int temperature;
	private String temperatureUnits;
	private int humidity;
	
	
	
	
	public Weather(int weatherID, int wind, String units, int temperature, String temperatureUnits, int humidity) {
		
		this.weatherID = weatherID;
		this.wind = wind;
		this.units = units;
		this.temperature = temperature;
		this.temperatureUnits = temperatureUnits;
		this.humidity = humidity;
	}
	
	public int getWeatherID() {
		return weatherID;
	}
	public void setWeatherID(int weatherID) {
		this.weatherID = weatherID;
	}
	public int getWind() {
		return wind;
	}
	public void setWind(int wind) {
		this.wind = wind;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public String getTemperatureUnits() {
		return temperatureUnits;
	}
	public void setTemperatureUnits(String temperatureUnits) {
		this.temperatureUnits = temperatureUnits;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	
	
	
	
}
