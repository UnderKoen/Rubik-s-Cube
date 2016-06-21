package nl.Under_Koen.RubiksCube.Scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import nl.Under_Koen.RubiksCube.HomeMenu;
import nl.Under_Koen.RubiksCube.Main;

public class Home {

	public static Scene homeScene() {
        StackPane root = new StackPane();
        Scene main = new Scene(root);
        root.setAlignment(Pos.TOP_LEFT);
        HomeMenu menu = new HomeMenu();
        menu.addButton("Start", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Main.stage.setScene(Game.gameScene());
            }
        });
        menu.addButton("Options", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Options.setBack(0);
            	Main.stage.setScene(Options.optionsScene());
            }
        });
        menu.addButton("Quit", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Main.stage.setFullScreen(false);
            	Main.stage.close();
            }
        });
        menu.show(root);
        return main;
	}
}
