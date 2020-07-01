package application;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import SQLManager.SQLDatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class LoginController {

	@FXML
	public Label lblstatus;
	@FXML
	public TextField txtusername;
	@FXML
	public TextField txtpassword;

	
	public static Stage primaryStage = new Stage();
	
	
	
	
	public void Login(ActionEvent event) throws Exception {
		long starttime = System.currentTimeMillis();
		
		
		if(!SQLDatabaseConnection.isConnected()) {
			lblstatus.setText("Must be online to sign in");
			return;
		}
		
		if(SQLDatabaseConnection.loginVerification(txtusername.getText(), txtpassword.getText())) {
			lblstatus.setText("Login Success");
			
	
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Main.fxml"));
			 Parent root = loader.load();
			 Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
			 Scene scene = new Scene(root,bounds.getWidth(),bounds.getHeight());
			//Sets main fxml as current scene
			
			UserController controller = loader.getController();
			int TeamID = SQLDatabaseConnection.getTeamID(txtusername.getText(), txtpassword.getText());
			//Gets logged in team's ID
			controller.initTeam(txtusername.getText(), TeamID);
			//Initalizes team info for UserController
			
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.setMaximized(true);
			primaryStage.show();
			//Sets primary stage settings
		}
		else {
			lblstatus.setText("Login Failed");
			}
		
		System.out.println("User sign in: " + (System.currentTimeMillis()-starttime) + " ms");
		
	}
	
	
	public void showLoginScreen () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root,300,350);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static boolean verification(String username, String password) throws FileNotFoundException {
		
		String querystring = username+","+ password;
		Scanner userscanner = new Scanner(new File("src/userdata/UserAccounts.txt"));
		
		boolean foundUser = false;
		while(userscanner.hasNext()) {
			if(userscanner.nextLine().equals(querystring)) {
				foundUser=true;
				userscanner.close();
				return true;
			}
			
		}
		userscanner.close();
		return false;
	}
	
	public void Clear(ActionEvent event) {
		lblstatus.setText("");
		txtusername.setText("");
		txtpassword.setText("");
	}
	
	public void Signup() throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
		Scene scene = new Scene(root,300,350);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	public void sumbitFormOnEnter (KeyEvent event) throws Exception {
		 if (event.getCode().equals(KeyCode.ENTER))
         {
             Login(null);
         }
	}
	
}
