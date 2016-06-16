package nl.Under_Koen.RubiksCube.Scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import nl.Under_Koen.RubiksCube.Main;
import nl.Under_Koen.RubiksCube.Cube.Cube;
import nl.Under_Koen.RubiksCube.Cube.Direction;

public class Game {

	public static Scene gameScene() {
		StackPane root = new StackPane();
        Scene main = new Scene(root,1000,560);
        root.setAlignment(Pos.TOP_LEFT);
        HomeMenu menu = new HomeMenu();
        menu.addButton("Reset", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Main.stage.setScene(gameScene());
            }
        });
        menu.addButton("Options");
        menu.addButton("Quit", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Main.stage.close();
            }
        });
        menu.show(root);
        Cube cube = new Cube();
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.LEFT)) {
                    cube.move(Direction.LEFT);
                }
                if (ke.getCode().equals(KeyCode.RIGHT)) {
                	cube.move(Direction.RIGHT);
                }
                if (ke.getCode().equals(KeyCode.UP)) {
                	cube.move(Direction.UP);
                }
                if (ke.getCode().equals(KeyCode.DOWN)) {
                	cube.move(Direction.DOWN);
                }
            }
        });
        cube.show(root, 350, 100);
		return main;
	}
}
