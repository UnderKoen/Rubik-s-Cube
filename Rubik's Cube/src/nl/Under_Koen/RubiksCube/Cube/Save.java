package nl.Under_Koen.RubiksCube.Cube;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

import nl.Under_Koen.RubiksCube.Cube.Cube.Color;
import nl.Under_Koen.RubiksCube.Cube.View.View;
import nl.Under_Koen.RubiksCube.Cube.View.Direction;
import nl.Under_Koen.RubiksCube.Cube.View.Rotation;

public class Save {
	
	public View view;
	public Rotation rotation;
	public File file;
	public HashMap<View, HashMap<Integer, Color>> ids;
	
	public Save (Cube cube, File file) {
		try {
			this.view = cube.view;
			this.rotation = cube.rotations.get(view);
			this.file = file;
			this.ids = cube.ids;
			makeSave();
			setSave();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void makeSave() throws Exception {
		file.createNewFile();
	}
	
	private void setSave() {
		try {
			File saveFile = file;
			if (!saveFile.exists()) {
				makeSave();
			}
			FileOutputStream fos = new FileOutputStream(saveFile);
			BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(fos));
			fileWriter.write(view.getId() + "");
			fileWriter.newLine();
			fileWriter.write(rotation.getId() + "");
			fileWriter.newLine();
			for (int i = 0; i != 6; i++) {
				StringBuilder ids = new StringBuilder();
				for (int i2 = 0; i2 != 9; i2++) {
					View newView = View.getView(i);
					Color newColor = this.ids.get(newView).get(i2);
					if (newView == null || newColor == null) {
						System.out.println("NULL");
					}
					ids.append(newColor.getId());
					if (i2 != 8) {
						ids.append(".");
					}
				}
				fileWriter.write(ids.toString());
				fileWriter.newLine();
			}
			fileWriter.close();
			
		}	catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Save getSave(File file) {
		File saveFile = file;
		if (!saveFile.exists()) {
			return null;
		}
		View view;
		Rotation rotation;
		try (Scanner scanner = new Scanner(saveFile)) {
			view = View.getView(scanner.nextInt());
			rotation = Rotation.getRotation(scanner.nextInt());
			HashMap<View, HashMap<Integer, Color>> ids = new HashMap<View, HashMap<Integer, Color>>();
			for (int i = 0; i != 6; i++) {
				HashMap<Integer, Color> ids2 = new HashMap<Integer, Color>();
				int i2 = 0;
				String[] ids3 = scanner.next().split("\\.");
				for (String s : ids3) {
					ids2.put(i2, Color.getColor(Integer.parseInt(s)));
					i2++;
				}
				ids.put(View.getView(i), ids2);
			}
			scanner.close();
			Cube cube = new Cube();
			cube.setIds(ids);
			cube.setView(view);
			cube.rotate(view, rotation);
			cube.move(Direction.NONE);
			return new Save(cube, file);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
