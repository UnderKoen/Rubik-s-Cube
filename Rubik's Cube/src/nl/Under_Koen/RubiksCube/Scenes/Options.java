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
	
	public static int back;
	
	public static void setBack(int scene) {
		back = scene;
	}
	
	public static Scene optionsScene() {
        StackPane root = new StackPane();
        ToggleButton wide = new ToggleButton("Wide");
        ToggleButton small = new ToggleButton("Small");
        ToggleGroup grp = new ToggleGroup();
        wide.setTranslateX(170);
        wide.setPrefWidth(100);
        small.setPrefWidth(100);
        wide.setTranslateY(20);
        small.setTranslateX(270);
        small.setTranslateY(20);
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
        
        ToggleButton inverted = new ToggleButton("Inverted");
        ToggleButton nInverted = new ToggleButton("Not Inverted");
        ToggleGroup grp2 = new ToggleGroup();
        inverted.setTranslateX(170);
        inverted.setPrefWidth(100);
        nInverted.setPrefWidth(100);
        inverted.setTranslateY(70);
        nInverted.setTranslateX(270);
        nInverted.setTranslateY(70);
        inverted.setToggleGroup(grp2);
        nInverted.setToggleGroup(grp2);
        if (Main.getOption(OptionsTypes.INVERTEDKEYS).contains("false")) {
        	grp2.selectToggle(inverted);
        } else {
        	grp2.selectToggle(nInverted);
        }
        inverted.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	grp2.selectToggle(wide);
            	Main.setOption(OptionsTypes.INVERTEDKEYS, false);
            }
        });
        nInverted.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	grp2.selectToggle(nInverted);
            	Main.setOption(OptionsTypes.INVERTEDKEYS, true);
            }
        });
        root.getChildren().addAll(inverted, nInverted);
        
        Scene main = new Scene(root);
        root.setAlignment(Pos.TOP_LEFT);
        HomeMenu menu = new HomeMenu();
        menu.addButton("Back", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if (back == 0) {
            		Main.stage.setScene(Home.homeScene());
            	} else {
            		Main.stage.setScene(Game.gameScene());
            	}
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