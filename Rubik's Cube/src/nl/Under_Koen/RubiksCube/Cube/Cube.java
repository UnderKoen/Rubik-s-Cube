package nl.Under_Koen.RubiksCube.Cube;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import nl.Under_Koen.RubiksCube.Main;

public class Cube {

	private static HashMap<View, HashMap<Integer, Color>> ids = new HashMap<View, HashMap<Integer, Color>>();
	private static HashMap<View, HashMap<Integer, Color>> ids2 = new HashMap<View, HashMap<Integer, Color>>();
	private static HashMap<View, Rotation> rotations = new HashMap<View, Rotation>();
	//				Side, Upside
	
	private View view = View.FRONT;
	
	public Cube() {
		toDefault();
	}
	
	public void toDefault() {
		ids.clear();
		for (int i = 0; i != 6; i++) {
			HashMap<Integer, Color> colors = new HashMap<Integer, Color>();
			for (int i2 = 0; i2 != 9; i2++) {
				Color color = Color.getColor(i);
				if (i2 == 0) {
					color = Color.RED;
					if (i == 2) {
						color = Color.GREEN;
					}
				}
				colors.put(i2, color);
			}
			rotations.put(View.getView(i), Rotation.NORTH);
			ids.put(View.getView(i), colors);
			ids2.put(View.getView(i), colors);
		}
	}
	
	public void rotate (View view, Rotation rotation) {
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
		ids2.remove(view);
		ids2.put(view, newIds);
		rotations.remove(view);
		rotations.put(view, rotation);
		show(defaultPane, defaultX, defaultY);
	}
	
	public void addRotation (View view, Direction rotation) {
		switch (rotation) {
		case RIGHT:
			switch (rotations.get(view)) {
			case EAST:
				rotate(view, Rotation.NORTH);
				break;
			case NORTH:
				rotate(view, Rotation.WEST);
				break;
			case SOUTH:
				rotate(view, Rotation.EAST);
				break;
			case WEST:
				rotate(view, Rotation.SOUTH);
				break;
			default:
				break;
			}
			break;
		case LEFT:
			switch (rotations.get(view)) {
			case EAST:
				rotate(view, Rotation.SOUTH);
				break;
			case NORTH:
				rotate(view, Rotation.EAST);
				break;
			case SOUTH:
				rotate(view, Rotation.WEST);
				break;
			case WEST:
				rotate(view, Rotation.NORTH);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}
	
	private double defaultX = 0;
	private double defaultY = 0;
	private Pane defaultPane = null;
	
	public void show(Pane pane, double x, double y) {
		defaultX = x;
		defaultY = y;
		defaultPane = pane;
		int Hrow = 0;
		int Vrow = 0;
		for (int i2 = 0; i2 != 9; i2++) {
			Color color = ids2.get(view).get(i2);
			Image image = new Image (Main.class.getResource("/Tiles/Default/Default_Tile_" + color + ".png").toString());
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);
			
			imageView.setTranslateX(x + (70 * Hrow));
			imageView.setTranslateY(y + (70 * Vrow));
			Hrow++;
			if (Hrow >= 3) {
				Hrow = 0;
				Vrow++;
			}
			pane.getChildren().add(imageView);
		}
		Vrow = 0;
		Hrow = 0;
		for (int i = 0; i != 6; i++) {
			if (view.getView(Direction.LEFT) == View.getView(i)) {
				for (int i2 = 0; i2 != 9; i2++) {
					Color color = ids2.get(view.getView(Direction.LEFT)).get(i2);
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
					pane.getChildren().add(imageView);
				}
			}
		}
		Vrow = 0;
		Hrow = 0;
		for (int i = 0; i != 6; i++) {
			if (view.getView(Direction.UP).getId() == View.getView(i).getId()) {
				for (int i2 = 0; i2 != 9; i2++) {
					Color color = ids2.get(view.getView(Direction.UP)).get(i2);
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
					pane.getChildren().add(imageView);
				}
			}
		}
	}
	
	public void move (Direction dir) {
		View viewNew = view.getView(dir);
		switch (dir) {
		case DOWN:
			addRotation(View.LEFT, Direction.LEFT);
			addRotation(View.RIGHT, Direction.LEFT);
			addRotation(View.FRONT, Direction.LEFT);
			addRotation(View.BACK, Direction.LEFT);
			break;
		case LEFT:
			addRotation(View.TOP, Direction.LEFT);
			addRotation(View.BOTTOM, Direction.LEFT);
			break;
		case NONE:
			break;
		case RIGHT:
			addRotation(View.TOP, Direction.RIGHT);
			addRotation(View.BOTTOM, Direction.RIGHT);
			break;
		case UP:
			addRotation(View.LEFT, Direction.RIGHT);
			addRotation(View.RIGHT, Direction.RIGHT);
			addRotation(View.FRONT, Direction.RIGHT);
			addRotation(View.BACK, Direction.RIGHT);
			break;
		}
		view = viewNew;
		//System.out.println(view + " -=- " + rotations.get(view));
		show(defaultPane, defaultX, defaultY);
	}
	
	public static enum Color {
		BLUE(0, "Blue"),
		GREEN(1, "Green"),
		RED(2, "Red"),
		WHITE(3, "White"),
		ORANGE(4, "Orange"),
		YELLOW(5, "Yellow");
		
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
				return GREEN;	
			case 2:
				return RED;
			case 3:
				return WHITE;
			case 4:
				return ORANGE;
			case 5:
				return YELLOW;
			default:
				return null;
			}
		}
	}
	
	public static enum View {
		FRONT(0, "Front"),
		LEFT(1, "Left"),
		RIGHT(2, "Right"),
		TOP(3, "Top"),
		BOTTOM(4, "Bottom"),
		BACK(5, "Back");
		
		private int id;
		
		private String name;
		
		View(int id, String name) {
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
		
		public static View getView(int id) {
			switch (id) {
			case 0:
				return FRONT;
			case 1:
				return LEFT;	
			case 2:
				return RIGHT;
			case 3:
				return TOP;
			case 4:
				return BOTTOM;
			case 5:
				return BACK;
			default:
				return null;
			}
		}
		
		private View getDown () {
			switch (this) {
			case BACK:
				switch (rotations.get(this)) {
				case EAST:
					return RIGHT;
				case NORTH:
					return BOTTOM; //DID
				case SOUTH:
					return TOP;  //DID
				case WEST:
					return LEFT;
				}
			case BOTTOM:
				switch (rotations.get(this)) {
				case EAST:
					return RIGHT; //DID
				case NORTH:
					return BACK;  //DID
				case SOUTH:
					return FRONT; //DID
				case WEST:
					return LEFT;
				}
			case FRONT:
				switch (rotations.get(this)) {
				case EAST:
					return LEFT;
				case NORTH:
					return BOTTOM;  //DID
				case SOUTH:
					return TOP;  //DID
				case WEST:
					return RIGHT;
				}
			case LEFT:
				switch (rotations.get(this)) {
				case EAST:
					return RIGHT;
				case NORTH:
					return BOTTOM;  //DID
				case SOUTH:
					return TOP;
				case WEST:
					return FRONT;
				}
			case RIGHT:
				switch (rotations.get(this)) {
				case EAST:
					return RIGHT;
				case NORTH:
					return BOTTOM;
				case SOUTH:
					return TOP; 
				case WEST:
					return LEFT;
				}
			case TOP:
				switch (rotations.get(this)) {
				case EAST:
					return LEFT;
				case NORTH:
					return FRONT; //DID
				case SOUTH:
					return BACK;  //DID
				case WEST:
					return RIGHT;
				}
			default:
				break;
			}
			return null;
		}
		private View getLeft () {
			switch (this) {
			case BACK:
				switch (rotations.get(this)) {
				case EAST:
					return TOP;
				case NORTH:
					return RIGHT;
				case SOUTH:
					return LEFT;
				case WEST:
					return BOTTOM;
				}
			case BOTTOM:
				switch (rotations.get(this)) {
				case EAST:
					return FRONT;
				case NORTH:
					return LEFT;
				case SOUTH:
					return RIGHT;
				case WEST:
					return FRONT;
				}
			case FRONT:
				switch (rotations.get(this)) {
				case EAST:
					return BOTTOM;
				case NORTH:
					return LEFT;
				case SOUTH:
					return RIGHT;
				case WEST:
					return RIGHT;
				}
			case LEFT:
				switch (rotations.get(this)) {
				case EAST:
					return TOP;
				case NORTH:
					return BACK;
				case SOUTH:
					return FRONT;
				case WEST:
					return TOP; //DID
				}
			case RIGHT:
				switch (rotations.get(this)) {
				case EAST:
					return TOP;
				case NORTH:
					return FRONT;
				case SOUTH:
					return BACK;
				case WEST:
					return TOP;
				}
			case TOP:
				switch (rotations.get(this)) {
				case EAST:
					return BACK;
				case NORTH:
					return LEFT;
				case SOUTH:
					return RIGHT;
				case WEST:
					return FRONT;
				}
			default:
				break;
			}
			return null;
		}
		private View getRight () {
			switch (this) {
			case BACK:
				switch (rotations.get(this)) {
				case EAST:
					return TOP;
				case NORTH:
					return LEFT;
				case SOUTH:
					return RIGHT;
				case WEST:
					return BOTTOM;
				}
			case BOTTOM:
				switch (rotations.get(this)) {
				case EAST:
					return FRONT;
				case NORTH:
					return RIGHT;
				case SOUTH:
					return LEFT;
				case WEST:
					return BACK;
				}
			case FRONT:
				switch (rotations.get(this)) {
				case EAST:
					return TOP;
				case NORTH:
					return RIGHT;
				case SOUTH:
					return LEFT;
				case WEST:
					return BOTTOM;
				}
			case LEFT:
				switch (rotations.get(this)) {
				case EAST:
					return BOTTOM;
				case NORTH:
					return FRONT;
				case SOUTH:
					return BACK;
				case WEST:
					return TOP;
				}
			case RIGHT:
				switch (rotations.get(this)) {
				case EAST:
					return TOP;
				case NORTH:
					return BACK;
				case SOUTH:
					return FRONT;
				case WEST:
					return BOTTOM;
				}
			case TOP:
				switch (rotations.get(this)) {
				case EAST:
					return FRONT;
				case NORTH:
					return RIGHT;
				case SOUTH:
					return LEFT;
				case WEST:
					return BACK;
				}
			default:
				break;
			}
			return null;
		}
		private View getUp () {
			switch (this) {
			case BACK:
				switch (rotations.get(this)) {
				case EAST:
					return RIGHT;
				case NORTH:
					return TOP;
				case SOUTH:
					return BOTTOM;
				case WEST:
					return LEFT;
				}
			case BOTTOM:
				switch (rotations.get(this)) {
				case EAST:
					return LEFT;
				case NORTH:
					return FRONT;
				case SOUTH:
					return BACK;
				case WEST:
					return RIGHT;
				}
			case FRONT:
				switch (rotations.get(this)) {
				case EAST:
					return LEFT;
				case NORTH:
					return TOP;
				case SOUTH:
					return BOTTOM;
				case WEST:
					return RIGHT;
				}
			case LEFT:
				switch (rotations.get(this)) {
				case EAST:
					return FRONT;
				case NORTH:
					return TOP;
				case SOUTH:
					return BOTTOM;
				case WEST:
					return BACK;
				}
			case RIGHT:
				switch (rotations.get(this)) {
				case EAST:
					return BACK;
				case NORTH:
					return TOP;
				case SOUTH:
					return BOTTOM;
				case WEST:
					return BACK;
				}
			case TOP:
				switch (rotations.get(this)) {
				case EAST:
					return RIGHT;
				case NORTH:
					return BACK;
				case SOUTH:
					return FRONT;
				case WEST:
					return LEFT;
				}
			default:
				break;
			}
			return null;
		}
		
		public View getView(Direction direction) {
			switch(direction) {
			case DOWN:
				return getDown();
			case LEFT:
				return getLeft();
			case RIGHT:
				return getRight();
			case UP:
				return getUp();
			case NONE:
				return this;
			}
			return null;
		}
	}
}
