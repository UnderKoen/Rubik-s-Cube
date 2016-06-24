package nl.Under_Koen.RubiksCube;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import nl.Under_Koen.RubiksCube.Scenes.*;

public class Main extends Application {
	
	public static Stage stage;
	
	private static Double defaultWidth = 1000.0;
	private static Double defaultHeight = 560.0;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		stage = primaryStage;
		stage.setWidth(defaultWidth);
		stage.setHeight(defaultHeight);
		stage.setMaxWidth(defaultWidth);
		stage.setMaxHeight(defaultHeight);
		stage.getIcons().add(new Image (Main.class.getResource("/icon.png").toString()));
		stage.setTitle("Rubik's Cube");
		stage.setScene(Home.homeScene());
		stage.show();
		makeFolders();
		makeFiles();
	}
	
	private static void makeFolders() throws Exception{
		File mainFolder = new File("/Users/gebruiker/Desktop/Rubik's Cube");
		if (!mainFolder.exists()) {
			mainFolder.mkdirs();
		}
		File savesFolder = new File(mainFolder, "Saves");
		if (!savesFolder.exists()) {
			savesFolder.mkdirs();
		}
		File optionFolder = new File(mainFolder, "Options");
		if (!optionFolder.exists()) {
			optionFolder.mkdirs();
		}
	}
	
	private static void makeFiles() throws Exception {
		makeOptionFiles();
	}
	
	private static void makeOptionFiles() throws Exception {
		File optionsFolder = new File("/Users/gebruiker/Desktop/Rubik's Cube/Options");
		File optionsFile = new File(optionsFolder, "Options.options");
		if (!optionsFile.exists()) {
			optionsFile.createNewFile();
			makeOptionFilesToDefault();
		}
	}
	
	private static void makeOptionFilesToDefault() throws Exception {
		setOption(OptionsTypes.RENDER, false);
	}
	
	public static enum OptionsTypes {
		RENDER(0),
		INVERTEDKEYS(1);
		
		private int line;
		
		private OptionsTypes (int line) {
			this.line = line;
		}
		
		public int getLine() {
			return line;
		}
	}

	public static void setOption(OptionsTypes type, Object newValue) {
		try {
			File optionsFile = new File("/Users/gebruiker/Desktop/Rubik's Cube/Options/Options.options");
			File thempOptionsFile = new File("/Users/gebruiker/Desktop/Rubik's Cube/Options/.Options.options");
			thempOptionsFile.createNewFile();
			if (!optionsFile.exists()) {
				makeOptionFiles();
			}
			try (Scanner scanner = new Scanner(optionsFile)){
				FileOutputStream fos = new FileOutputStream(thempOptionsFile);
				 
				BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(fos));
				
				for (int i = 0; i != type.getLine(); i++) {
					String next = scanner.next();
					fileWriter.write(next);
					fileWriter.newLine();
				}
				fileWriter.write(newValue.toString());
				fileWriter.newLine();
				if (scanner.hasNext()) {
					scanner.next();
				}
				while (scanner.hasNext()) {
					fileWriter.write(scanner.next());
					fileWriter.newLine();
				}
				fileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try (Scanner scanner = new Scanner(thempOptionsFile)){
				FileOutputStream fos = new FileOutputStream(optionsFile);
				 
				BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(fos));
				while (scanner.hasNext()) {
					fileWriter.write(scanner.next());
					fileWriter.newLine();
				}
				fileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			thempOptionsFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getOption(OptionsTypes type) {
		File optionsFile = new File("/Users/gebruiker/Desktop/Rubik's Cube/Options/Options.options");
		String output = "";
		try (Scanner scanner = new Scanner(optionsFile)) {
			for (int i = 0; i != type.getLine() && scanner.hasNextLine(); i++) {
				scanner.nextLine();
			}
			if (scanner.hasNextLine()) {
				output = scanner.nextLine();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			return null;
		}
		return output;
		 
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Double getXscale() {
		return stage.getWidth() / defaultWidth;
	}
	
	public static Double getYscale() {
		return stage.getHeight() / defaultHeight;
	}
}
