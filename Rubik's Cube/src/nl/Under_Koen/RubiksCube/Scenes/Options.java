package nl.Under_Koen.RubiksCube.Scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import nl.Under_Koen.RubiksCube.HomeMenu;
import nl.Under_Koen.RubiksCube.Main;
import nl.Under_Koen.RubiksCube.Main.OptionsTypes;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class Options {
	
	public static Scene optionsScene() {
        StackPane root = new StackPane();
        ToggleButton wide = new ToggleButton("Wide");
        ToggleButton small = new ToggleButton("Small");
        ToggleGroup grp = new ToggleGroup();
        wide.setTranslateX(300);
        wide.setPrefWidth(100);
        small.setPrefWidth(100);
        wide.setTranslateY(100);
        small.setTranslateX(400);
        small.setTranslateY(100);
        wide.setToggleGroup(grp);
        small.setToggleGroup(grp);
        if (Main.getOption(OptionsTypes.RENDER).contains("false")) {
        	grp.selectToggle(wide);
        } else {
        	grp.selectToggle(small);
        }
        wide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	grp.selectToggle(wide);
            	Main.setOption(OptionsTypes.RENDER, false);
            }
        });
        small.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	grp.selectToggle(small);
            	Main.setOption(OptionsTypes.RENDER, true);
            }
        });
        root.getChildren().addAll(wide, small);
        Scene main = new Scene(root);
        root.setAlignment(Pos.TOP_LEFT);
        HomeMenu menu = new HomeMenu();
        menu.addButton("Back", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Main.stage.setScene(Home.homeScene());
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