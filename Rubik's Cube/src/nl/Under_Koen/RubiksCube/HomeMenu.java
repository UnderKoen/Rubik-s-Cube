package nl.Under_Koen.RubiksCube;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HomeMenu {

	private List<Button> buttons = new ArrayList<Button>();
	
	public HomeMenu() {
		buttons.clear();
	}
	
	public void show(Pane pane) {
		Rectangle layout = new Rectangle(0,0,150 * Main.getXscale(),560  * Main.getYscale());
        layout.setFill(Color.rgb(100, 100, 100, 0.4));
		pane.getChildren().add(layout);
		for (Button button : buttons) {
			pane.getChildren().add(button);
		}
	}
	
	public void addButton(String name, EventHandler<ActionEvent> event) {
		Button button = new Button();
		 button.setMinWidth(100);
		 button.setOnAction(event);
		 button.setCursor(Cursor.HAND);
		 button.setText(name);
		 if (buttons.isEmpty()) {
			 button.setTranslateX(20);
			 button.setTranslateY(10);
			 buttons.add(button);
			 return;
		 }
		 Button lastButton = buttons.get(buttons.size() -1);
		 button.setTranslateX(lastButton.getTranslateX());
		 button.setTranslateY(lastButton.getTranslateY() + lastButton.getHeight() + 35);
		 buttons.add(button);
	}
	
	public void addButton(String name) {
		 Button button = new Button();
		 button.setMinWidth(100);
		 button.setCursor(Cursor.HAND);
		 button.setText(name);
		 if (buttons.isEmpty()) {
			 button.setTranslateX(20);
			 button.setTranslateY(10);
			 buttons.add(button);
			 return;
		 }
		 Button lastButton = buttons.get(buttons.size() -1);
		 button.setTranslateX(lastButton.getTranslateX());
		 button.setTranslateY(lastButton.getTranslateY() + lastButton.getHeight() + 35);
		 buttons.add(button);
	}
	
	public void addButton(Button button) {
		button.setMinWidth(100);
		if (buttons.isEmpty()) {
			button.setTranslateX(20);
			button.setTranslateY(10);
			buttons.add(button);
			return;
		}
		Button lastButton = buttons.get(buttons.size() -1);
		button.setTranslateX(lastButton.getTranslateX());
		button.setTranslateY(lastButton.getTranslateY() + lastButton.getHeight() + 35);
		buttons.add(button);
	}
}
