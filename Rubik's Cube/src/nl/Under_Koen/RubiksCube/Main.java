package nl.Under_Koen.RubiksCube;
	
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import nl.Under_Koen.RubiksCube.Scenes.*;

public class Main extends Application {
	
	
	//TODO rotations already did top rotion at left and right
	
	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		stage.getIcons().add(new Image (Main.class.getResource("/icon.png").toString()));
		stage.setTitle("Rubik's Cube");
		stage.setScene(Home.homeScene());
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
