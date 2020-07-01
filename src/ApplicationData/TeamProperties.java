package ApplicationData;

import java.sql.Timestamp;

public class TeamProperties {

	private int teamID;
	private Timestamp lastSync;
	private String tarcID;
	private String speedUnits;
	private String temperatureUnits;
	private String humidityUnits;
	private String timeUnits;
	private String altitudeUnits;
	
	
	
	
	public TeamProperties(int teamID, Timestamp lastSync, String tarcID, String speedUnits, String temperatureUnits,
			String humidityUnits, String timeUnits, String altitudeUnits) {
		this.teamID = teamID;
		this.lastSync = lastSync;
		this.tarcID = tarcID;
		this.speedUnits = speedUnits;
		this.temperatureUnits = temperatureUnits;
		this.humidityUnits = humidityUnits;
		this.timeUnits = timeUnits;
		this.altitudeUnits = altitudeUnits;
	}

	public TeamProperties (int teamID, Timestamp lastSync, String tarcID) {
		
		this.teamID = teamID;
		this.lastSync = lastSync;
		this.tarcID = tarcID;
		
		
	}
	
	public TeamProperties() {
		// TODO Auto-generated constructor stub
	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public Timestamp getLastSync() {
		return lastSync;
	}

	public void setLastSync(Timestamp lastSync) {
		this.lastSync = lastSync;
	}

	public String getTarcID() {
		return tarcID;
	}

	public void setTarcID(String tarcID) {
		this.tarcID = tarcID;
	}

	public String getSpeedUnits() {
		return speedUnits;
	}

	public void setSpeedUnits(String speedUnits) {
		this.speedUnits = speedUnits;
	}

	public String getTemperatureUnits() {
		return temperatureUnits;
	}

	public void setTemperatureUnits(String temperatureUnits) {
		this.temperatureUnits = temperatureUnits;
	}

	public String getHumidityUnits() {
		return humidityUnits;
	}

	public void setHumidityUnits(String humidityUnits) {
		this.humidityUnits = humidityUnits;
	}

	public String getTimeUnits() {
		return timeUnits;
	}

	public void setTimeUnits(String timeUnits) {
		this.timeUnits = timeUnits;
	}

	public String getAltitudeUnits() {
		return altitudeUnits;
	}

	public void setAltitudeUnits(String altitudeUnits) {
		this.altitudeUnits = altitudeUnits;
	}
	
	

	
	
	
}
