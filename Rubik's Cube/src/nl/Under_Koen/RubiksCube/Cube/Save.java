package nl.Under_Koen.RubiksCube.Cube;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import nl.Under_Koen.RubiksCube.Cube.Cube.View;

public class Save {
	
	public View view;
	public Rotation rotation;
	public String name;
	
	public Save (Cube cube, String fileName) {
		try {
			this.view = cube.view;
			this.rotation = cube.rotations.get(view);
			name = fileName;
			makeSave();
			setSave();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void makeSave() throws Exception {
		File saveFolder = new File("/Users/gebruiker/Desktop/Rubik's Cube/Saves");
		File saveFile = new File(saveFolder, name + ".save");
		saveFile.createNewFile();
	}
	
	private void setSave() {
		try {
			File saveFolder = new File("/Users/gebruiker/Desktop/Rubik's Cube/Saves");
			File saveFile = new File(saveFolder, name + ".save");
			if (!saveFile.exists()) {
				makeSave();
			}
			FileOutputStream fos = new FileOutputStream(saveFile);
			BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(fos));
			fileWriter.write(view.getId() + "");
			fileWriter.newLine();
			fileWriter.write(rotation.getId() + "");
			fileWriter.close();
			
		}	catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Save getSave(String name) {
		File saveFolder = new File("/Users/gebruiker/Desktop/Rubik's Cube/Saves");
		File saveFile = new File(saveFolder, name + ".save");
		if (!saveFile.exists()) {
			return null;
		}
		View view;
		Rotation rotation;
		try (Scanner scanner = new Scanner(saveFile)) {
			view = View.getView(scanner.nextInt());
			rotation = Rotation.getRotation(scanner.nextInt());
			scanner.close();
			Cube cube = new Cube();
			cube.setView(view);
			cube.rotate(view, rotation);
			cube.move(Direction.NONE);
			return new Save(cube, name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
