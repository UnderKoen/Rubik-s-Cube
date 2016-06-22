package nl.Under_Koen.RubiksCube.Cube;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import nl.Under_Koen.RubiksCube.Main;
import nl.Under_Koen.RubiksCube.Cube.Row.Row;
import nl.Under_Koen.RubiksCube.Cube.Row.Row.HRow;
import nl.Under_Koen.RubiksCube.Cube.Row.Row.VRow;
import nl.Under_Koen.RubiksCube.Cube.View.Direction;
import nl.Under_Koen.RubiksCube.Cube.View.Rotation;
import nl.Under_Koen.RubiksCube.Cube.View.View;

public class Cube {

	public HashMap<View, HashMap<Integer, Color>> ids = new HashMap<View, HashMap<Integer, Color>>();
	public HashMap<View, HashMap<Integer, Color>> ids2 = new HashMap<View, HashMap<Integer, Color>>();
	public HashMap<View, Rotation> rotations = new HashMap<View, Rotation>();
	//				Side, Upside
	
	public View view = View.FRONT;
	
	public Cube() {
		toDefault();
	}
	
	public void setIds(HashMap<View, HashMap<Integer, Color>> ids) {
		this.ids = ids;
		move(Direction.NONE);
	}
	
	//TODO methode works only with middle
	/**
	 * @throws Exception if dir is not relative with the row
	 */
	public void moveRow(Row row, Direction dir) throws Exception {
		HashMap<View, HashMap<Integer, Color>> oldIds = ids;
		HashMap<View, HashMap<Integer, Color>> newIds = oldIds;
		if (row instanceof HRow) {
			if (dir != Direction.LEFT && dir != Direction.RIGHT) {
				throw new Exception();
			}
			HRow hRow = (HRow) row;
			ArrayList<Integer> listIds = new ArrayList<Integer>();
			View view1 = view.getView(dir, this);
			View view2 = view.getView(Direction.OPPOSITE, this);
			View view3 = view.getView(dir.flip(), this);
			View[] listViews = {view, view1, view2, view3, view};
			ArrayList<Color> rowBefore = null;
			boolean first = true;
			switch (hRow) {
			case BOTTOM:
				/*for (View v : listViews) {
					Rotation test = rotations.get(v);
					ArrayList<Integer> oldListIds = listIds;
					switch (test) {
					case EAST:
						listIds.clear();
						listIds.add(0);
						listIds.add(3);
						listIds.add(6);
						break;
					case NORTH:
						listIds.clear();
						listIds.add(6);
						listIds.add(7);
						listIds.add(8);
						break;
					case SOUTH:
						listIds.clear();
						listIds.add(0);
						listIds.add(1);
						listIds.add(2);
						break;
					case WEST:
						listIds.clear();
						listIds.add(2);
						listIds.add(5);
						listIds.add(8);
						break;
					}
					ArrayList<Color> oldRow = rowBefore;
					rowBefore = new ArrayList<Color>();
					for (int i : oldListIds) {
						rowBefore.add(oldIds.get(v).get(i));
					}
					if (!first) {
						HashMap<Integer, Color> row2 = newIds.get(v);
						int i2 = 0;
						for (int i : listIds) {
							row2.remove(i);
							row2.put(i, oldRow.get(i2));
							i2++;
						}
						newIds.remove(v);
						newIds.put(v, row2);
					}
					first = false;
				}
				addRotation(view.getView(Direction.DOWN, this), dir, false);*/
				break;
			case MIDDLE:
				for (View v : listViews) {
					ArrayList<Integer> oldListIds = listIds;
					Rotation test = rotations.get(v);
					if (view.getView(Direction.OPPOSITE, this) == v) { 
						test = test.flip();
					}
					switch (test) {
					case EAST:
						listIds.clear();
						listIds.add(1);
						listIds.add(4);
						listIds.add(7);
						break;
					case NORTH:
						listIds.clear();
						listIds.add(3);
						listIds.add(4);
						listIds.add(5);
						break;
					case SOUTH:
						listIds.clear();
						listIds.add(5);
						listIds.add(4);
						listIds.add(3);
						break;
					case WEST:
						listIds.clear();
						listIds.add(7);
						listIds.add(4);
						listIds.add(1);
						break;
					}
					ArrayList<Color> oldRow = rowBefore;
					rowBefore = new ArrayList<Color>();
					for (int i : oldListIds) {
						rowBefore.add(oldIds.get(v).get(i));
					}
					if (!first) {
						HashMap<Integer, Color> row2 = newIds.get(v);
						int i2 = 0;
						for (int i : listIds) {
							row2.remove(i);
							row2.put(i, oldRow.get(i2));
							i2++;
						}
						newIds.remove(v);
						newIds.put(v, row2);
					}
					first = false;
				}
				break;
			case TOP:
				break;
			}
			addRotation(view, Direction.NONE);
			move(Direction.NONE);
		}
		if (row instanceof VRow) {
			if (dir != Direction.UP && dir != Direction.DOWN) {
				throw new Exception();
			}
			VRow vRow = (VRow) row;
			ArrayList<Integer> listIds = new ArrayList<Integer>();
			View view1 = view.getView(dir, this);
			View view2 = view.getView(Direction.OPPOSITE, this);
			View view3 = view.getView(dir.flip(), this);
			View[] listViews = {view, view1, view2, view3, view};
			ArrayList<Color> rowBefore = null;
			boolean first = true;
			switch (vRow) {
			case LEFT:
				break;
			case MIDDLE:
				for (View v : listViews) {
					ArrayList<Integer> oldListIds = listIds;
					Rotation test = rotations.get(v);
					if (view.getView(Direction.OPPOSITE, this) == v) { 
						test = test.flip();
					}
					switch (test) {
					case EAST:
						listIds.clear();
						listIds.add(3);
						listIds.add(4);
						listIds.add(5);
						break;
					case NORTH:
						listIds.clear();
						listIds.add(7);
						listIds.add(4);
						listIds.add(1);
						break;
					case SOUTH:
						listIds.clear();
						listIds.add(1);
						listIds.add(4);
						listIds.add(7);
						break;
					case WEST:
						listIds.clear();
						listIds.add(5);
						listIds.add(4);
						listIds.add(3);
						break;
					}
					ArrayList<Color> oldRow = rowBefore;
					rowBefore = new ArrayList<Color>();
					for (int i : oldListIds) {
						rowBefore.add(oldIds.get(v).get(i));
					}
					if (!first) {
						HashMap<Integer, Color> row2 = newIds.get(v);
						int i2 = 0;
						for (int i : listIds) {
							row2.remove(i);
							row2.put(i, oldRow.get(i2));
							i2++;
						}
						newIds.remove(v);
						newIds.put(v, row2);
					}
					first = false;
				}
				break;
			case RIGHT:
				break;
			}
			addRotation(view, Direction.NONE);
			move(Direction.NONE);
		}
	}
	
	public void toDefault() {
		ids.clear();
		ids2.clear();
		rotations.clear();
		for (int i = 0; i != 6; i++) {
			HashMap<Integer, Color> colors = new HashMap<Integer, Color>();
			for (int i2 = 0; i2 != 9; i2++) {
				Color color = Color.getColor(i);
				colors.put(i2, color);
			}
			rotations.put(View.getView(i), Rotation.NORTH);
			ids.put(View.getView(i), colors);
			ids2.put(View.getView(i), colors);
		}
		view = View.FRONT;
	}
	
	public void setView (View view) { 
		this.view = view;
		move(Direction.NONE);
	}
	
	public void reset() {
		toDefault();
		move(Direction.NONE);
	}
	
	public void loadSave(File file) {
		Save save = Save.getSave(file);
		if (save == null) {
			return;
		}
		setIds(save.ids);
		view = save.view;
		rotate(view, save.rotation);
		move(Direction.NONE);
	}
	
	public void saveSave(File file) {
		new Save(this, file);
	}
	
	public void rotate (View view, Rotation rotation) {
		rotate(view, rotation, true);
	}
	
	private void rotate (View view, Rotation rotation, boolean save) {
		HashMap<Integer, Color> oldIds = ids.get(view);
		HashMap<Integer, Color> newIds = new HashMap<Integer, Color>();
		switch (rotation) {
		case WEST:
			for (int i = 0; i != 9; i++) {
				int newNumber = i;
				switch (i) {
				case 0:
					newNumber = 2;
					break;
				case 1:
					newNumber = 5;
					break;
				case 2:
					newNumber = 8;
					break;
				case 3:
					newNumber = 1;
					break;
				case 4:
					break;
				case 5:
					newNumber = 7;
					break;
				case 6:
					newNumber = 0;
					break;
				case 7:
					newNumber = 3;
					break;
				case 8:
					newNumber = 6;
					break;
				}
				newIds.put(newNumber, oldIds.get(i));
			}
			break;
		case NORTH:
			newIds = oldIds;
			break;
		case SOUTH:
			for (int i = 0; i != 9; i++) {
				int newNumber = i;
				switch (i) {
				case 0:
					newNumber = 8;
					break;
				case 1:
					newNumber = 7;
					break;
				case 2:
					newNumber = 6;
					break;
				case 3:
					newNumber = 5;
					break;
				case 4:
					break;
				case 5:
					newNumber = 3;
					break;
				case 6:
					newNumber = 2;
					break;
				case 7:
					newNumber = 1;
					break;
				case 8:
					newNumber = 0;
					break;
				}
				newIds.put(newNumber, oldIds.get(i));
			}
			break;
		case EAST:
			for (int i = 0; i != 9; i++) {
				int newNumber = i;
				switch (i) {
				case 0:
					newNumber = 6;
					break;
				case 1:
					newNumber = 3;
					break;
				case 2:
					newNumber = 0;
					break;
				case 3:
					newNumber = 7;
					break;
				case 4:
					break;
				case 5:
					newNumber = 1;
					break;
				case 6:
					newNumber = 8;
					break;
				case 7:
					newNumber = 5;
					break;
				case 8:
					newNumber = 2;
					break;
				}
				newIds.put(newNumber, oldIds.get(i));
			}
			break;
		default:
			break;
		}
		if (save) {
			ids2.remove(view);
			ids2.put(view, newIds);
			rotations.remove(view);
			rotations.put(view, rotation);
		} else {
			ids.remove(view);
			ids.put(view, newIds);
		}
	}
	
	public void addRotation (View view, Direction rotation) {
		addRotation(view, rotation, true);
	}
	
	private void addRotation (View view, Direction rotation, boolean save) {
		switch (rotation) {
		case RIGHT:
			switch (rotations.get(view)) {
			case EAST:
				rotate(view, Rotation.NORTH, save);
				break;
			case NORTH:
				rotate(view, Rotation.WEST, save);
				break;
			case SOUTH:
				rotate(view, Rotation.EAST, save);
				break;
			case WEST:
				rotate(view, Rotation.SOUTH, save);
				break;
			default:
				break;
			}
			break;
		case LEFT:
			switch (rotations.get(view)) {
			case EAST:
				rotate(view, Rotation.SOUTH, save);
				break;
			case NORTH:
				rotate(view, Rotation.EAST, save);
				break;
			case SOUTH:
				rotate(view, Rotation.WEST, save);
				break;
			case WEST:
				rotate(view, Rotation.NORTH, save);
				break;
			default:
				break;
			}
			break;
		default:
			rotate(view, rotations.get(view), save);
			break;
		}
	}
	
	public Group cube = new Group();
	
	public void showSmall(Pane pane, double x, double y) {
		pane.getChildren().remove(cube);
		cube = new Group();
		cube.setTranslateX(x);
		cube.setTranslateY(y);
		int Hrow = 0;
		int Vrow = 0;
		for (int i2 = 0; i2 != 9; i2++) {
			Color color = ids2.get(view).get(i2);
			Image image = new Image (Main.class.getResource("/Tiles/Default/Default_Tile_" + color + ".png").toString());
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);
			
			imageView.setTranslateX(x + 2 + (62 * Hrow));
			imageView.setTranslateY(y + (62 * Vrow));
			Hrow++;
			if (Hrow >= 3) {
				Hrow = 0;
				Vrow++;
			}
			cube.getChildren().add(imageView);
		}
		Vrow = 0;
		Hrow = 0;
		for (int i = 0; i != 6; i++) {
			if (view.getView(Direction.LEFT, this) == View.getView(i)) {
				for (int i2 = 0; i2 != 9; i2++) {
					Color color = ids2.get(view.getView(Direction.LEFT, this)).get(i2);
					Image image = new Image (Main.class.getResource("/Tiles/LeftEdge/LeftEdge_Tile_" + color + ".png").toString());
					ImageView imageView = new ImageView(image);
					imageView.setFitHeight(100);
					imageView.setFitWidth(100);
					
					imageView.setTranslateX(x-62 + (29 * Hrow));
					imageView.setTranslateY(y-2 + (62 * Vrow));
					if (Hrow == 0) {
						imageView.setTranslateY(y-62 + (62 * Vrow));
					}
					
					if (Hrow == 1) {
						imageView.setTranslateY(y-32 + (62 * Vrow));
					}
					
					Hrow++;
					if (Hrow >= 3) {
						Hrow = 0;
						Vrow++;
					}
					cube.getChildren().add(imageView);
				}
			}
		}
		Vrow = 0;
		Hrow = 0;
		for (int i = 0; i != 6; i++) {
			if (view.getView(Direction.UP, this).getId() == View.getView(i).getId()) {
				for (int i2 = 0; i2 != 9; i2++) {
					Color color = ids2.get(view.getView(Direction.UP, this)).get(i2);
					Image image = new Image (Main.class.getResource("/Tiles/TopEdge/TopEdge_Tile_" + color + ".png").toString());
					ImageView imageView = new ImageView(image);
					imageView.setFitHeight(100);
					imageView.setFitWidth(100);
					
					imageView.setTranslateX(x-2 + (64 * Hrow));
					imageView.setTranslateY(y-64 + (29 * Vrow));
					if (Vrow == 0) {
						imageView.setTranslateX(x-59 + (64 * Hrow));
					}
					
					if (Vrow == 1) {
						imageView.setTranslateX(x-30 + (64 * Hrow));
					}
					
					Hrow++;
					if (Hrow >= 3) {
						Hrow = 0;
						Vrow++;
					}
					cube.getChildren().add(imageView);
				}
			}
		}
		pane.getChildren().add(cube);
	}
	
	public void showWide(Pane pane, double x, double y) {
		pane.getChildren().remove(cube);
		cube = new Group();
		cube.setTranslateX(x);
		cube.setTranslateY(y);
		int Hrow = 0;
		int Vrow = 0;
		for (int i2 = 0; i2 != 9; i2++) {
			Color color = ids2.get(view).get(i2);
			if (color != null) {
				Image image = new Image (Main.class.getResource("/Tiles/Default/Default_Tile_" + color + ".png").toString());
				ImageView imageView = new ImageView(image);
				imageView.setFitHeight(100);
				imageView.setFitWidth(100);
				
				imageView.setTranslateX(x + (70 * Hrow));
				imageView.setTranslateY(y + (70 * Vrow));
				cube.getChildren().add(imageView);
			}
			Hrow++;
			if (Hrow >= 3) {
				Hrow = 0;
				Vrow++;
			}
		}
		Vrow = 0;
		Hrow = 0;
		for (int i = 0; i != 6; i++) {
			if (view.getView(Direction.LEFT, this) == View.getView(i)) {
				for (int i2 = 0; i2 != 9; i2++) {
					Color color = ids2.get(view.getView(Direction.LEFT, this)).get(i2);
					Image image = new Image (Main.class.getResource("/Tiles/LeftEdge/LeftEdge_Tile_" + color + ".png").toString());
					ImageView imageView = new ImageView(image);
					imageView.setFitHeight(100);
					imageView.setFitWidth(100);
					
					imageView.setTranslateX(x-70 + (30 * Hrow));
					imageView.setTranslateY(y-5 + (70 * Vrow));
					if (Hrow == 0) {
						imageView.setTranslateY(y-65 + (70 * Vrow));
					}
					
					if (Hrow == 1) {
						imageView.setTranslateY(y-35 + (70 * Vrow));
					}
					
					Hrow++;
					if (Hrow >= 3) {
						Hrow = 0;
						Vrow++;
					}
					cube.getChildren().add(imageView);
				}
			}
		}
		Vrow = 0;
		Hrow = 0;
		for (int i = 0; i != 6; i++) {
			if (view.getView(Direction.UP, this).getId() == View.getView(i).getId()) {
				for (int i2 = 0; i2 != 9; i2++) {
					Color color = ids2.get(view.getView(Direction.UP, this)).get(i2);
					Image image = new Image (Main.class.getResource("/Tiles/TopEdge/TopEdge_Tile_" + color + ".png").toString());
					ImageView imageView = new ImageView(image);
					imageView.setFitHeight(100);
					imageView.setFitWidth(100);
					
					imageView.setTranslateX(x + (70 * Hrow));
					imageView.setTranslateY(y-75 + (30 * Vrow+5));
					if (Vrow == 0) {
						imageView.setTranslateX(x-60 + (70 * Hrow));
					}
					
					if (Vrow == 1) {
						imageView.setTranslateX(x-30 + (70 * Hrow));
					}
					
					Hrow++;
					if (Hrow >= 3) {
						Hrow = 0;
						Vrow++;
					}
					cube.getChildren().add(imageView);
				}
			}
		}
		pane.getChildren().add(cube);
	}
	
	public void move (Direction dir) {
		view = view.getView(dir, this);
		switch (view) {
		case BACK:
			switch (rotations.get(view)) {
			case EAST:
				rotate(View.BACK, Rotation.EAST);
				rotate(View.TOP, Rotation.WEST);
				rotate(View.BOTTOM, Rotation.WEST);
				rotate(View.LEFT, Rotation.EAST);
				rotate(View.RIGHT, Rotation.EAST);
				rotate(View.FRONT, Rotation.EAST);
				break;
			case NORTH:
				rotate(View.BACK, Rotation.NORTH);
				rotate(View.TOP, Rotation.SOUTH);
				rotate(View.BOTTOM, Rotation.SOUTH);
				rotate(View.LEFT, Rotation.NORTH);
				rotate(View.RIGHT, Rotation.NORTH);
				rotate(View.FRONT, Rotation.NORTH);
				break;
			case SOUTH:
				rotate(View.BACK, Rotation.SOUTH);
				rotate(View.FRONT, Rotation.SOUTH);
				rotate(View.TOP, Rotation.NORTH);
				rotate(View.BOTTOM, Rotation.NORTH);
				rotate(View.LEFT, Rotation.SOUTH);
				rotate(View.RIGHT, Rotation.SOUTH);
				break;
			case WEST:
				rotate(View.BACK, Rotation.WEST);
				rotate(View.FRONT, Rotation.WEST);
				rotate(View.TOP, Rotation.EAST);
				rotate(View.BOTTOM, Rotation.EAST);
				rotate(View.LEFT, Rotation.WEST);
				rotate(View.RIGHT, Rotation.WEST);
				break;
			}
			break;
		case BOTTOM:
			switch (rotations.get(view)) {
			case EAST:
				rotate(View.FRONT, Rotation.EAST);
				rotate(View.BOTTOM, Rotation.EAST);
				rotate(View.TOP, Rotation.EAST);
				rotate(View.BACK, Rotation.WEST);
				rotate(View.LEFT, Rotation.EAST);
				rotate(View.RIGHT, Rotation.NORTH);
				break;
			case NORTH:
				rotate(View.FRONT, Rotation.NORTH);
				rotate(View.BOTTOM, Rotation.NORTH);
				rotate(View.TOP, Rotation.NORTH);
				rotate(View.BACK, Rotation.SOUTH);
				rotate(View.LEFT, Rotation.EAST);
				rotate(View.RIGHT, Rotation.WEST);
				break;
			case SOUTH:
				rotate(View.FRONT, Rotation.SOUTH);
				rotate(View.BOTTOM, Rotation.SOUTH);
				rotate(View.TOP, Rotation.SOUTH);
				rotate(View.BACK, Rotation.NORTH);
				rotate(View.LEFT, Rotation.WEST);
				rotate(View.RIGHT, Rotation.EAST);
				break;
			case WEST:
				rotate(View.FRONT, Rotation.WEST);
				rotate(View.BOTTOM, Rotation.WEST);
				rotate(View.TOP, Rotation.WEST);
				rotate(View.BACK, Rotation.EAST);
				rotate(View.LEFT, Rotation.NORTH);
				rotate(View.RIGHT, Rotation.EAST);
				break;
			}
			break;
		case FRONT:
			switch (rotations.get(view)) {
			case EAST:
				rotate(View.FRONT, Rotation.EAST);
				rotate(View.BACK, Rotation.EAST);
				rotate(View.TOP, Rotation.EAST);
				rotate(View.BOTTOM, Rotation.EAST);
				rotate(View.LEFT, Rotation.EAST);
				rotate(View.RIGHT, Rotation.EAST);
				break;
			case NORTH:
				rotate(View.FRONT, Rotation.NORTH);
				rotate(View.BACK, Rotation.NORTH);
				rotate(View.TOP, Rotation.NORTH);
				rotate(View.BOTTOM, Rotation.NORTH);
				rotate(View.LEFT, Rotation.NORTH);
				rotate(View.RIGHT, Rotation.NORTH);
				break;
			case SOUTH:
				rotate(View.FRONT, Rotation.SOUTH);
				rotate(View.BACK, Rotation.SOUTH);
				rotate(View.TOP, Rotation.SOUTH);
				rotate(View.BOTTOM, Rotation.SOUTH);
				rotate(View.LEFT, Rotation.SOUTH);
				rotate(View.RIGHT, Rotation.SOUTH);
				break;
			case WEST:
				rotate(View.FRONT, Rotation.WEST);
				rotate(View.BACK, Rotation.WEST);
				rotate(View.TOP, Rotation.WEST);
				rotate(View.BOTTOM, Rotation.WEST);
				rotate(View.LEFT, Rotation.WEST);
				rotate(View.RIGHT, Rotation.WEST);
				break;
			}
			break;
		case LEFT:
			switch (rotations.get(view)) {
			case EAST:
				rotate(View.FRONT, Rotation.EAST);
				rotate(View.TOP, Rotation.SOUTH);
				rotate(View.BOTTOM, Rotation.NORTH);
				rotate(View.LEFT, Rotation.EAST);
				rotate(View.RIGHT, Rotation.EAST);
				rotate(View.BACK, Rotation.EAST);
				break;
			case NORTH:
				rotate(View.FRONT, Rotation.NORTH);
				rotate(View.TOP, Rotation.EAST);
				rotate(View.BOTTOM, Rotation.WEST);
				rotate(View.LEFT, Rotation.NORTH);
				rotate(View.RIGHT, Rotation.NORTH);
				rotate(View.BACK, Rotation.NORTH);
				break;
			case SOUTH:
				rotate(View.FRONT, Rotation.SOUTH);
				rotate(View.TOP, Rotation.WEST);
				rotate(View.BOTTOM, Rotation.EAST);
				rotate(View.LEFT, Rotation.SOUTH);
				rotate(View.RIGHT, Rotation.SOUTH);
				rotate(View.BACK, Rotation.SOUTH);
				break;
			case WEST:
				rotate(View.FRONT, Rotation.WEST);
				rotate(View.TOP, Rotation.NORTH);
				rotate(View.BOTTOM, Rotation.SOUTH);
				rotate(View.LEFT, Rotation.WEST);
				rotate(View.RIGHT, Rotation.WEST);
				rotate(View.BACK, Rotation.WEST);
				break;
			}
			break;
		case RIGHT:
			switch (rotations.get(view)) {
			case EAST:
				rotate(View.FRONT, Rotation.EAST);
				rotate(View.TOP, Rotation.NORTH);
				rotate(View.BOTTOM, Rotation.SOUTH);
				rotate(View.RIGHT, Rotation.EAST);
				rotate(View.LEFT, Rotation.EAST);
				rotate(View.BACK, Rotation.EAST);
				break;
			case NORTH:
				rotate(View.FRONT, Rotation.NORTH);
				rotate(View.TOP, Rotation.WEST);
				rotate(View.BOTTOM, Rotation.EAST);
				rotate(View.RIGHT, Rotation.NORTH);
				rotate(View.LEFT, Rotation.NORTH);
				rotate(View.BACK, Rotation.NORTH);
				break;
			case SOUTH:
				rotate(View.FRONT, Rotation.SOUTH);
				rotate(View.TOP, Rotation.EAST);
				rotate(View.BOTTOM, Rotation.WEST);
				rotate(View.RIGHT, Rotation.SOUTH);
				rotate(View.LEFT, Rotation.SOUTH);
				rotate(View.BACK, Rotation.SOUTH);
				break;
			case WEST:
				rotate(View.FRONT, Rotation.WEST);
				rotate(View.TOP, Rotation.SOUTH);
				rotate(View.BOTTOM, Rotation.NORTH);
				rotate(View.RIGHT, Rotation.WEST);
				rotate(View.LEFT, Rotation.WEST);
				rotate(View.BACK, Rotation.WEST);
				break;
			}
			break;
		case TOP:
			switch (rotations.get(view)) {
			case EAST:
				rotate(View.FRONT, Rotation.EAST);
				rotate(View.TOP, Rotation.EAST);
				rotate(View.BOTTOM, Rotation.EAST);
				rotate(View.BACK, Rotation.WEST);
				rotate(View.LEFT, Rotation.NORTH);
				rotate(View.RIGHT, Rotation.SOUTH);
				break;
			case NORTH:
				rotate(View.FRONT, Rotation.NORTH);
				rotate(View.TOP, Rotation.NORTH);
				rotate(View.BOTTOM, Rotation.NORTH);
				rotate(View.BACK, Rotation.SOUTH);
				rotate(View.LEFT, Rotation.WEST);
				rotate(View.RIGHT, Rotation.EAST);
				break;
			case SOUTH:
				rotate(View.FRONT, Rotation.SOUTH);
				rotate(View.TOP, Rotation.SOUTH);
				rotate(View.BOTTOM, Rotation.SOUTH);
				rotate(View.BACK, Rotation.NORTH);
				rotate(View.LEFT, Rotation.EAST);
				rotate(View.RIGHT, Rotation.WEST);
				break;
			case WEST:
				rotate(View.FRONT, Rotation.WEST);
				rotate(View.TOP, Rotation.WEST);
				rotate(View.BOTTOM, Rotation.WEST);
				rotate(View.BACK, Rotation.EAST);
				rotate(View.LEFT, Rotation.SOUTH);
				rotate(View.RIGHT, Rotation.NORTH);
				break;
			}
			break;
		}
	}
	
	public static enum Color {
		// 0 = front
		// 1 = left
		// 2 = right
		// 3 = top
		// 4 = bottom
		// 5 = back
		BLUE(0, "Blue"), 
		GREEN(5, "Green"),
		RED(3, "Red"),
		WHITE(1, "White"),
		ORANGE(4, "Orange"),
		YELLOW(2, "Yellow");
		
		private int id;
		
		private String name;
		
		Color(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		};
		
		public int getId() {
			return id;
		}
		
		public static Color getColor(int id) {
			switch (id) {
			case 0:
				return BLUE;
			case 1:
				return WHITE;	
			case 2:
				return YELLOW;
			case 3:
				return RED;
			case 4:
				return ORANGE;
			case 5:
				return GREEN;
			default:
				return null;
			}
		}
	}
}
