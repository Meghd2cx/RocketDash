package application;
	
import ApplicationData.Team;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
		
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root,300,350);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			LoginController.primaryStage.setScene(scene);
			LoginController.primaryStage.setResizable(false);
			LoginController.primaryStage.getIcons().add(new Image("/img/RDSmall.png"));
			LoginController.primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
