package nl.Under_Koen.RubiksCube.Scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import nl.Under_Koen.RubiksCube.HomeMenu;
import nl.Under_Koen.RubiksCube.Main;
import nl.Under_Koen.RubiksCube.Cube.Cube;
import nl.Under_Koen.RubiksCube.Cube.Direction;
import nl.Under_Koen.RubiksCube.Main.OptionsTypes;

public class Game {

	public static Scene gameScene() {
		StackPane root = new StackPane();
        Scene main = new Scene(root);
        root.setAlignment(Pos.TOP_LEFT);
        HomeMenu menu = new HomeMenu();
        Cube cube = new Cube();
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.LEFT)) {
                    cube.move(Direction.LEFT);
                    show(root, 350, 100, cube);	
                }
                if (ke.getCode().equals(KeyCode.RIGHT)) {
                	cube.move(Direction.RIGHT);
                	show(root, 350, 100, cube);	
                }
                if (ke.getCode().equals(KeyCode.UP)) {
                	cube.move(Direction.UP);
                	show(root, 350, 100, cube);	
                }
                if (ke.getCode().equals(KeyCode.DOWN)) {
                	cube.move(Direction.DOWN);
                	show(root, 350, 100, cube);
                }
            }
        });
        menu.addButton("Reset", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Main.stage.setScene(gameScene());
            	show(root, 350, 100, cube);
            }
        });
        menu.addButton("Options", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
        show(root, 350, 100, cube);	
		return main;
	}
	
	private static void show(StackPane root, int x, int y, Cube cube) {
		if (Main.getOption(OptionsTypes.RENDER).contains("false")) {
			cube.showWide(root, x, y);
		} else {
			cube.showSmall(root, x, y);
		}
	}
}
