package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.Scanner;

import SQLManager.SQLDatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SignupController {

	
	@FXML
	private TextField txtnewusername;
	@FXML
	private TextField txtnewpassword;
	@FXML
	private TextField txtnewpasswordconfirm;
	@FXML
	private Label lbladduser;
	@FXML
	private ImageView loadingView;
	
	public void returnToSignin() throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root,300,350);
		LoginController.primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		LoginController.primaryStage.show();
	}
	

	
	public void addUser(ActionEvent event) throws SQLException, IOException {
		long starttime = System.currentTimeMillis();
		String newuser = txtnewusername.getText();
		String password = txtnewpassword.getText();
		String passwordconfirm = txtnewpasswordconfirm.getText();
		
		//loadingView.setOpacity(1);
		
		if(newuser.isBlank() || password.isBlank() || passwordconfirm.isBlank()) {
			lbladduser.setText("One or more fields are empty");
		//	loadingView.setOpacity(0);
			return;
		}
		if(SQLDatabaseConnection.loginVerification(newuser, password)) {
			lbladduser.setText("User already exists");
		//	loadingView.setOpacity(0);
			return;
		}
		if(password.toLowerCase().equals("password")||password.length()<4) {
			lbladduser.setText("Password not valid");
		//	loadingView.setOpacity(0);
			return;
		}
		boolean signInSuccess = SQLDatabaseConnection.addUser(newuser,password);
		if(!signInSuccess) {
			loadingView.setOpacity(0);
			lbladduser.setText("User registration failed!");
			return;
		}
		else {
		
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root,300,350);
		LoginController.primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		//loadingView.setOpacity(0);
		LoginController.primaryStage.show();
		}
		
		System.out.println("User sign up: " + (System.currentTimeMillis()-starttime) + " ms");

	}
	

	
}
