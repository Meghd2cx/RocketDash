package ApplicationData;

import java.util.ArrayList;

public class Rocket {

	private int rocketID;
	private String rocketName;
	private double totalCost;
	private int teamID;
	private ArrayList<Flight> flights;
	private double simMass;
	private String simMassUnits;
	private int simApogee;
	private String simApogeeUnits;
	
	
	
	public Rocket(int rocketID, String rocketName, double totalCost, int teamID,
			double simMass, String simMassUnits, int simApogee, String simApogeeUnits) {
		
		this.rocketID = rocketID;
		this.rocketName = rocketName;
		this.totalCost = totalCost;
		this.teamID = teamID;
		this.simMass = simMass;
		this.simMassUnits = simMassUnits;
		this.simApogee = simApogee;
		this.simApogeeUnits = simApogeeUnits;
	}

	public Rocket (int RocketID, String RocketName, double TotalCost, int TeamID) {
		this.rocketID = RocketID;
		this.rocketName = RocketName;
		this.totalCost = TotalCost;
		this.teamID = TeamID;
	}
	
	public Rocket (int RocketID, String RocketName, int TeamID) {
		this.rocketID = RocketID;
		this.rocketName = RocketName;
		this.teamID = TeamID;
	}
	

	
	public int getRocketID() {
		return rocketID;
	}

	public void setRocketID(int rocketID) {
		this.rocketID = rocketID;
	}

	public String getRocketName() {
		return rocketName;
	}

	public void setRocketName(String rocketName) {
		this.rocketName = rocketName;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public double getSimMass() {
		return simMass;
	}

	public void setSimMass(double simMass) {
		this.simMass = simMass;
	}

	public String getSimMassUnits() {
		return simMassUnits;
	}

	public void setSimMassUnits(String simMassUnits) {
		this.simMassUnits = simMassUnits;
	}

	public int getSimApogee() {
		return simApogee;
	}

	public void setSimApogee(int simApogee) {
		this.simApogee = simApogee;
	}

	public String getSimApogeeUnits() {
		return simApogeeUnits;
	}

	public void setSimApogeeUnits(String simApogeeUnits) {
		this.simApogeeUnits = simApogeeUnits;
	}

	public ArrayList<Flight> getFlights (){
		return flights;
	}
	
	public ArrayList<Flight> addFlight (Flight newFlight) {
		flights.add(newFlight);
		return flights;
	}
	
	public void setFlightsList (ArrayList<Flight> newFlightList ) {
		this.flights = newFlightList;
	}
	
	
	
}
