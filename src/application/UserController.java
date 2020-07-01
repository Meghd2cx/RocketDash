package application;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import ApplicationData.Flight;
import ApplicationData.FlightProperty;
import ApplicationData.Rocket;
import ApplicationData.RocketProperty;
import ApplicationData.Team;
import ApplicationData.TeamProperties;
import SQLManager.SQLDatabaseConnection;


public class UserController implements Initializable {
	//Fields
	
	//Syncing object
	//@FXML
	//private Button syncButton;
	
	//Rocket and User info labels
	@FXML
	private Label aboutlbl;
	@FXML
	private Label teamnamelbl;
	@FXML
	private Label rocketlbl;
	
	//Flight log objects
	@FXML
	private TableView flightLogTable;
	@FXML
	private TableColumn flightIDColumn;
	@FXML
	private TableColumn locationColumn;
	@FXML
	private TableColumn altitudeColumn;
	@FXML
	private TableColumn timeColumn;
	@FXML
	private TableColumn stabilityColumn;
	@FXML
	private TableColumn notesColumn;
	@FXML
	private TableColumn flightDateColumn;
	@FXML
	private TableColumn windColumn;
	@FXML
	private TableColumn temperatureColumn;
	@FXML
	private TableColumn humidityColumn;
	
	//Modify flight log objects
	@FXML
	private Button switchFlightModeButton;
	@FXML
	private Text flightEditMode;
	@FXML
	private Label addFlightlbl;
	@FXML
	private TextField newFlightDate;
	@FXML
	private TextField newLocation;
	@FXML
	private TextField newAltitude;
	@FXML
	private TextField newTime;
	@FXML
	private TextField newTemperature;
	@FXML
	private TextField newWindSpeed;
	@FXML
	private TextField newHumidity;
	@FXML
	private TextField newStability;
	@FXML
	private TextField newNotes;
	@FXML
	private ChoiceBox<String> windDirectionBox;
	@FXML
	private Button submitFlightButton;
	@FXML
	private Button deleteFlightButton;
	
	//Select rocket object
	@FXML
	private ComboBox<String> selectRocketComboBox;
	
	//Rocket Properties objects
	@FXML
	private TableView rocketPropertiesTable;
	@FXML
	private TableColumn propertyColumn;
	@FXML
	private TableColumn valueColumn;
	
	//Class
	private ObservableList<String> rocketList = FXCollections.observableArrayList("");
	
	public Team currentTeam = new Team();
	
	
	//Initialization methods
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		//syncRockets();
		//selectRocketComboBox.setItems(rocketList);
		windDirectionBox.getItems().add("N");
		windDirectionBox.getItems().add("S");
		windDirectionBox.getItems().add("E");
		windDirectionBox.getItems().add("W");
		windDirectionBox.getItems().add("NE");
		windDirectionBox.getItems().add("NW");
		windDirectionBox.getItems().add("SE");
		windDirectionBox.getItems().add("SW");
		

		propertyColumn.setCellValueFactory(new PropertyValueFactory<> ("property"));
		valueColumn.setCellValueFactory(new PropertyValueFactory<> ("value"));
		
		flightIDColumn.setCellValueFactory(new PropertyValueFactory<> ("flightID"));
		locationColumn.setCellValueFactory(new PropertyValueFactory<> ("location"));
		altitudeColumn.setCellValueFactory(new PropertyValueFactory<> ("altitude"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<> ("time"));
		stabilityColumn.setCellValueFactory(new PropertyValueFactory<> ("stability"));
		notesColumn.setCellValueFactory(new PropertyValueFactory<> ("notes"));
		flightDateColumn.setCellValueFactory(new PropertyValueFactory<> ("flightDate"));
		windColumn.setCellValueFactory(new PropertyValueFactory<> ("wind"));
		temperatureColumn.setCellValueFactory(new PropertyValueFactory<> ("temperature"));
		humidityColumn.setCellValueFactory(new PropertyValueFactory<> ("humidity"));
		
		flightLogTable.setOnMouseClicked(e -> {
			editFlight();
		});
		
	}	
	
	public void initTeam (String TeamName, int TeamID) {
		currentTeam.setTeamName(TeamName);
		currentTeam.setTeamID(TeamID);
		teamnamelbl.setText("Welcome: " + TeamName + "-" + TeamID);
		
		sync();
		

		newAltitude.setPromptText("Altitude (" + currentTeam.getAltitudeUnits() +")");
		newTime.setPromptText("Time (" + currentTeam.getTimeUnits() + ")");
		newTemperature.setPromptText("Temperature (" + currentTeam.getTemperatureUnits() + ")");
		newWindSpeed.setPromptText("Wind (" + currentTeam.getSpeedUnits() + ")");
		newHumidity.setPromptText("Humidity (" + currentTeam.getHumidityUnits() + ")");
		
	}
	
	//Basic app func. methods
	public void Logout (ActionEvent event) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root,300,350);
		LoginController.primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		LoginController.primaryStage.setResizable(false);
		LoginController.primaryStage.show();
	}

	public void Fullscreen (ActionEvent event) {
		LoginController.primaryStage.setFullScreenExitHint("Press 'esc' to exit");
		LoginController.primaryStage.setFullScreen(true);
	}

	public void About (ActionEvent event) {
		//TODO add window on top of screen to show about information
		LoginController.primaryStage.getScene();
		if(aboutlbl.getOpacity()==0)
		aboutlbl.setOpacity(1);
		else {
			aboutlbl.setOpacity(0);		}
		
	}
	
	//Syncing methods
	public void sync () {
		long starttime = System.currentTimeMillis();
		
		//syncButton.setText("Syncing...");
		//syncButton.setDisable(true);
		
		syncTeamProperties();
		syncRockets();
		syncFlights();
		
		System.out.println("Sync finished");
		System.out.println("Overall sync: " + (System.currentTimeMillis()-starttime) + " ms");
		
		//syncButton.setDisable(false);
		//syncButton.setText("Sync");
		
		//TODO Take additional sync methods and put in a new sync class
	}
	
	public void syncTeamProperties () {
		
		TeamProperties syncedProperties = SQLDatabaseConnection.syncTeamProperties(currentTeam.getTeamID());
		
		if(syncedProperties == (null)) {
			System.out.println("No team properties exist. Using defaults.");
			return;
		}
		
		currentTeam.setSpeedUnits(syncedProperties.getSpeedUnits());
		currentTeam.setTemperatureUnits(syncedProperties.getTemperatureUnits());
		currentTeam.setHumidityUnits(syncedProperties.getHumidityUnits());
		currentTeam.setTimeUnits(syncedProperties.getTimeUnits());
		currentTeam.setAltitudeUnits(syncedProperties.getAltitudeUnits());
		
	}
	
	public void syncRockets() {
		long starttime = System.currentTimeMillis();
		int teamID = currentTeam.getTeamID();
		ArrayList<Rocket> rocketArray = SQLDatabaseConnection.syncRockets(teamID);
		currentTeam.setRocketList(rocketArray);
		for(int i = 0; i < rocketArray.size();i++) {
			//TODO Fix inefficient add to Array method
			if(!selectRocketComboBox.getItems().contains(rocketArray.get(i).getRocketName()))
			{selectRocketComboBox.getItems().add((rocketArray.get(i).getRocketName()));}
		}
		
		System.out.println("Rocket sync finished");
		System.out.println("Rocket sync: " + (System.currentTimeMillis()-starttime) + " ms");
	}
	
	public void syncFlights() {
		long starttime = System.currentTimeMillis();
		
		for(int i = 0; i < currentTeam.getRockets().size(); i++) {
			ArrayList<Flight> flights = SQLDatabaseConnection.syncFlights(currentTeam.getRockets().get(i).getRocketID());
			currentTeam.getRockets().get(i).setFlightsList(flights);
		}
		
		System.out.println("Flight sync finished");
		System.out.println("Flight sync: " + (System.currentTimeMillis()-starttime) + " ms");
	}

	//Choosing rocket methods
	@SuppressWarnings("unchecked")
	public void chooseRocket (ActionEvent event) {
			clearRocketProperties();
			clearFlightLog();
			//Clears current rocket information
			
			rocketlbl.setText("Selected Rocket: " + selectRocketComboBox.getValue());
			Rocket currentRocket = Team.findRocketByName(currentTeam,selectRocketComboBox.getValue());
			//Finds the choosen rocket based on name and teamID
			
			addPropertiesToTable(currentRocket);
			addFlightsToLog(currentRocket);
			//Adds properties to tables/logs
			
			System.out.println("Selected Rocket Name: " + currentRocket.getRocketName());
			
	}
	
	public void addPropertiesToTable(Rocket currentRocket) {
		
		rocketPropertiesTable.getItems().add(new RocketProperty("Rocket Name",currentRocket.getRocketName()));
		rocketPropertiesTable.getItems().add(new RocketProperty("RocketID",Integer.toString(currentRocket.getRocketID())));
		rocketPropertiesTable.getItems().add(new RocketProperty("TotalCost",Double.toString(currentRocket.getTotalCost())));
		String SimMassValue = Double.toString(currentRocket.getSimMass()) + " " + currentRocket.getSimMassUnits();
		rocketPropertiesTable.getItems().add(new RocketProperty("Simulated Mass",SimMassValue));
		String SimApogeeValue = Integer.toString(currentRocket.getSimApogee()) + " " + currentRocket.getSimApogeeUnits();
		rocketPropertiesTable.getItems().add(new RocketProperty ("Simulated Apogee", SimApogeeValue));
		//rocketProperties.getItems().add(new RocketProperty("RocketID",Integer.toString(currentRocket.getRocketID())));
	}
	
	public void addFlightsToLog(Rocket currentRocket) {
		 currentRocket = Team.findRocketByName(currentTeam,selectRocketComboBox.getValue());
		try {
			//Notes: the try section fails if the constructor takes a flight which has a field that is empty
			//TODO Improvements: Have way of allowing user to not completely fill out all fields to insert into the table
			ArrayList<FlightProperty> flightsLog = new ArrayList<FlightProperty>();
				for(Flight flight: currentRocket.getFlights()) {
					
					String formattedTime = flight.getTime() + " sec(s)";
					String formattedAltitude = flight.getAltitude() + " ft";
					String formattedWind = flight.getWindSpeed() +" "+ currentTeam.getSpeedUnits() +" "+ flight.getWindDirection();
					String formattedTemperature = flight.getTemperature() + " " + currentTeam.getTemperatureUnits();
					String formattedHumidity = flight.getHumidity() + "%";
					flightsLog.add(new FlightProperty(Integer.toString(flight.getFlightID()),flight.getLocation(), formattedAltitude, formattedTime, flight.getStability(), flight.getNotes(), flight.getFlightDate().toString(), formattedWind, formattedTemperature ,formattedHumidity));
				}
				flightLogTable.getItems().addAll(flightsLog);
		}
		catch(NullPointerException e) {
			System.out.println("Flights won't load!");
			if(currentRocket.getFlights().size() == 0) {
				System.out.println("No flights associated with this rocket!");
			}
		}
	}
	
	public void clearRocketProperties() {
		for(int i = 0; i < rocketPropertiesTable.getItems().size();i++) {
			rocketPropertiesTable.getItems().clear();
		}
	}

	public void clearFlightLog() {
		for(int i = 0; i < flightLogTable.getItems().size();i++) {
			flightLogTable.getItems().clear();
		}
	}
	
	public boolean isRocketChosen() {
		return !selectRocketComboBox.getSelectionModel().isEmpty();
	}
	
	//Modifying flight log methods
	public boolean addFlight(){
		//flightEditMode.setText("Add Flight");
		addFlightlbl.setText("");
		if(!isRocketChosen()) {
			addFlightlbl.setText("No rocket chosen");
			return false;
			}
		
		if(!checkFlightPropertyFields()) return false;
		
		
		Flight newFlight = new Flight();
		
		try {
			newFlight.setFlightDate(new SimpleDateFormat("MM/dd/yyyy").parse(newFlightDate.getText()));
			newFlight.setAltitude(Integer.parseInt(newAltitude.getText()));
			newFlight.setTime(Double.parseDouble(newTime.getText()));
			newFlight.setWindSpeed(Integer.parseInt(newWindSpeed.getText()));
			newFlight.setTemperature(Integer.parseInt(newTemperature.getText()));
			newFlight.setHumidity(Integer.parseInt(newHumidity.getText()));
			newFlight.setWindDirection(getFullCardinalDirection(windDirectionBox.getValue()));
			newFlight.setRocketID(Team.findRocketByName(currentTeam, selectRocketComboBox.getValue()).getRocketID());
			newFlight.setLocation(newLocation.getText());
			newFlight.setStability(newStability.getText());
			newFlight.setNotes(newNotes.getText());
		}
		catch(ParseException | NumberFormatException | NullPointerException e) {
			addFlightlbl.setText("Field not properly formatted");
			return false;
		}
		
		SQLDatabaseConnection.addFlight(newFlight);
		
		sync();
		//Syncs flights for all rockets
		chooseRocket(null);
		//Resets the chosen rocket to display new information
		resetFlightFields();
		return true;
	}
		
	public void addEditedFlight(int oldFlightID) {
		if(!checkFlightPropertyFields()) {return;}
		SQLDatabaseConnection.deleteFlight(oldFlightID);
		addFlight();		
		
		
		switchToAddMode();
	}
	
	public void editFlight() {
		if(!isRocketChosen()) {
			return;
		}
		switchToEditMode();
			
		try{
			FlightProperty selectedFlight = (FlightProperty) flightLogTable.getSelectionModel().getSelectedItem();
			//newFlightDate.setText(selectedFlight.getFlightDate());
			newLocation.setText(selectedFlight.getLocation());
			newAltitude.setText(Integer.toString(FlightProperty.extractIntegers(selectedFlight.getAltitude())));
			newTime.setText(Double.toString(FlightProperty.extractDoubles(selectedFlight.getTime())));
			windDirectionBox.getSelectionModel().clearAndSelect(getDirectionBoxNumber(selectedFlight.getWind()));
			newWindSpeed.setText(Integer.toString(FlightProperty.extractIntegers(selectedFlight.getWind())));
			newTemperature.setText(Integer.toString(FlightProperty.extractIntegers(selectedFlight.getTemperature())));
			newHumidity.setText(Integer.toString(FlightProperty.extractIntegers(selectedFlight.getHumidity())));
			newStability.setText(selectedFlight.getStability());
			newNotes.setText(selectedFlight.getNotes());
			
			submitFlightButton.setOnAction (e -> {
				addEditedFlight(Integer.parseInt(selectedFlight.getFlightID()));
			});
		}
		catch (NullPointerException e) {
			addFlightlbl.setText("No flight chosen");
		}
		
	}
	
	public void deleteFlight() {
		FlightProperty selectedFlight = (FlightProperty) flightLogTable.getSelectionModel().getSelectedItem();
		SQLDatabaseConnection.deleteFlight(Integer.parseInt(selectedFlight.getFlightID()));
		
		sync();
		//Syncs flights for all rockets
		chooseRocket(null);
		//Resets the chosen rocket to display new information
		resetFlightFields();
		
		flightEditMode.setText("Add flight:");
		deleteFlightButton.setDisable(true);
		deleteFlightButton.setVisible(false);
		submitFlightButton.setOnAction( e -> {
			addFlight();
		});
	}
	
	public void switchToEditMode() {
		flightEditMode.setText("Edit Flight");
		
		deleteFlightButton.setVisible(true);
		deleteFlightButton.setDisable(false);
		switchFlightModeButton.setVisible(true);
		switchFlightModeButton.setDisable(false);
		
	}
	
	public void switchToAddMode() {
		resetFlightFields();
		
		flightEditMode.setText("Add flight:");
		deleteFlightButton.setDisable(true);
		deleteFlightButton.setVisible(false);
		switchFlightModeButton.setVisible(false);
		switchFlightModeButton.setDisable(true);
		submitFlightButton.setOnAction( e -> {
			addFlight();
		});
	}
	
	public boolean checkFlightPropertyFields() {
		if(newFlightDate.getText().isBlank() || 
			newLocation.getText().isBlank() || 
			newAltitude.getText().isBlank() || 
			newTime.getText().isBlank() || 
			newTemperature.getText().isBlank() || 
			newWindSpeed.getText().isBlank() || 
			newHumidity.getText().isBlank() || 
			newStability.getText().isBlank() || 
			windDirectionBox.getSelectionModel().isEmpty()) {
			
			addFlightlbl.setText("All fields must be completed");
			return false;
		}
		if((Double.parseDouble(newWindSpeed.getText()) > 999.99)) {
			
			addFlightlbl.setText("Time must be within 999.99 " + currentTeam.getTimeUnits());
			return false;
		}
		return true;
	}
	
	private int getDirectionBoxNumber(String wind) {
		if(wind.contains("Northeast")) {
			return 4;
		}
		if(wind.contains("Northwest")) {
			return 5;
		}
		if(wind.contains("Southeast")) {
			return 6;
		}
		if(wind.contains("Southwest")) {
			return 7;
		}
		if(wind.contains("North")) {
			return 0;
		}
		if(wind.contains("South")) {
			return 1;
		}
		if(wind.contains("East")) {
			return 2;
		}
		if(wind.contains("West")) {
			return 3;
		}
		
		return -1;
	}
	
	public String getFullCardinalDirection(String shortForm) {
		switch(shortForm) {
		case "N":
			 return "North";
		
		 case "S":
			 return "South";
		
		 case "E":
			 return "East";

		 case "W":
			 return "West";

		 case "NE":
			 return "Northeast";

		 case "NW":
			 return "Northwest";

		 case "SE":
			 return "Southeast";

		 case "SW":
			 return "Southwest";
			}
		return "Not Defined";
		}
	
	public void setDate () {
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		 LocalDateTime now = LocalDateTime.now();  
		 newFlightDate.setText(dtf.format(now));
	}
	
	public void resetFlightFields() {
		newFlightDate.clear();
		newLocation.clear();
		newAltitude.clear();
		newTime.clear();
		newWindSpeed.clear();
		windDirectionBox.setValue(null);
		newTemperature.clear();
		newHumidity.clear();
		newStability.clear();
		newNotes.clear();
	}
	
}
