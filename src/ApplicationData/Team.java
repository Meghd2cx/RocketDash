package ApplicationData;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class Team {

	private int TeamID;
	private String TeamName = "";
	private String TARCID = "";
	private Date LastSync;
	private ArrayList<Rocket> rockets;
	private String SpeedUnits = "mph";
	private String TemperatureUnits = "F";
	private String HumidityUnits = "%";
	private String TimeUnits = "secs";
	private String AltitudeUnits = "ft";
	
	//private String conn = "";
	
	public Team() {
		
	}

	public int getTeamID() {
		return TeamID;
	}

	public void setTeamID(int teamID) {
		TeamID = teamID;
	}

	public String getTeamName() {
		return TeamName;
	}

	public void setTeamName(String teamName) {
		TeamName = teamName;
	}

	public String getTARCID() {
		return TARCID;
	}

	public void setTARCID(String tARCID) {
		TARCID = tARCID;
	}

	public Date getLastSync() {
		return LastSync;
	}

	public void setLastSync(Date lastSync) {
		LastSync = lastSync;
	}
	
	public ArrayList<Rocket> getRockets(){
		return rockets;
	}
	
	public ArrayList<Rocket> addRocket(Rocket newRocket) {
		rockets.add(newRocket);
		return rockets;
	}
	
	public void setRocketList (ArrayList<Rocket> newRocketList) {
		this.rockets = newRocketList;
	}
	
	
	public String getSpeedUnits() {
		return SpeedUnits;
	}

	public void setSpeedUnits(String speedUnits) {
		SpeedUnits = speedUnits;
	}

	public String getTemperatureUnits() {
		return TemperatureUnits;
	}

	public void setTemperatureUnits(String temperatureUnits) {
		TemperatureUnits = temperatureUnits;
	}

	public String getHumidityUnits() {
		return HumidityUnits;
	}

	public void setHumidityUnits(String humidityUnits) {
		HumidityUnits = humidityUnits;
	}

	public String getTimeUnits() {
		return TimeUnits;
	}

	public void setTimeUnits(String timeUnits) {
		TimeUnits = timeUnits;
	}

	public String getAltitudeUnits() {
		return AltitudeUnits;
	}

	public void setAltitudeUnits(String altitudeUnits) {
		AltitudeUnits = altitudeUnits;
	}

	public static Rocket findRocketByName (Team currentTeam, String rocketName) {
		
		ArrayList<Rocket> rocketArray = currentTeam.getRockets();
		
		Rocket ret = null;
		boolean foundRocket = false;
		int i = 0;
		while(!foundRocket) {
			if(rocketArray.get(i).getRocketName().equals(rocketName)) {
				ret = rocketArray.get(i);
				foundRocket = true;
			}
			i++;
		}
		
		return ret;
	}

	
	
	/*
	 * public String getConn() { return conn; }
	 * 
	 * public void setConn(String conn) { this.conn = conn; }
	 */
	
	
	
}
