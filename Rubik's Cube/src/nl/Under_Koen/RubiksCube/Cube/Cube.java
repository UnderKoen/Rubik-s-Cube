package nl.Under_Koen.RubiksCube.Cube;

import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import nl.Under_Koen.RubiksCube.Main;

public class Cube {

	private HashMap<View, HashMap<Integer, Color>> ids = new HashMap<View, HashMap<Integer, Color>>();
	private HashMap<View, HashMap<Integer, Color>> ids2 = new HashMap<View, HashMap<Integer, Color>>();
	private HashMap<View, Rotation> rotations = new HashMap<View, Rotation>();
	//				Side, Upside
	
	private boolean debug = false;
	
	private View view = View.FRONT;
	
	public Cube() {
		toDefault();
	}
	
	public Cube(Rotation rot) {
		debug = true;
		toDefault(rot);
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
					if (i == 4) {
						color = Color.BLUE;
					}
				}
				colors.put(i2, color);
			}
			rotations.put(View.getView(i), Rotation.NORTH);
			ids.put(View.getView(i), colors);
			ids2.put(View.getView(i), colors);
		}
	}
	
	public void toDefault(Rotation rot) {
		ids.clear();
		for (int i = 0; i != 6; i++) {
			HashMap<Integer, Color> colors = new HashMap<Integer, Color>();
			for (int i2 = 0; i2 != 9; i2++) {
				Color color = Color.getColor(i);
				if (i2 == 8) {
					color = Color.RED;
					if (i == 2) {
						color = Color.GREEN;
					}
					if (i == 4) {
						color = Color.BLUE;
					}
				}
				colors.put(i2, color);
			}
			rotations.put(View.getView(i), rot);
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
	public Group cube = new Group();
	
	public void show(Pane pane, double x, double y) {
		defaultX = x;
		defaultY = y;
		defaultPane = pane;
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
			
			imageView.setTranslateX(x + (70 * Hrow));
			imageView.setTranslateY(y + (70 * Vrow));
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
		if (debug) {
			cube.setRotate(180);
		}
		pane.getChildren().add(cube);
	}
	
	public void move (Direction dir) {
		view = view.getView(dir, this);
		switch (view) {
		case BACK:
			switch (rotations.get(view)) {
			case EAST:
				break;
			case NORTH:
				rotate(View.BACK, Rotation.NORTH);
				rotate(View.TOP, Rotation.SOUTH);
				rotate(View.BOTTOM, Rotation.SOUTH);
				rotate(View.LEFT, Rotation.NORTH);
				rotate(View.RIGHT, Rotation.NORTH);
				break;
			case SOUTH:
				rotate(View.BACK, Rotation.SOUTH);
				rotate(View.TOP, Rotation.NORTH);
				rotate(View.BOTTOM, Rotation.NORTH);
				rotate(View.LEFT, Rotation.SOUTH);
				rotate(View.RIGHT, Rotation.SOUTH);
				break;
			case WEST:
				break;
			}
			break;
		case BOTTOM:
			switch (rotations.get(view)) {
			case EAST:
				rotate(View.FRONT, Rotation.EAST);
				rotate(View.BOTTOM, Rotation.EAST);
				rotate(View.BACK, Rotation.WEST);
				rotate(View.LEFT, Rotation.EAST);
				rotate(View.RIGHT, Rotation.NORTH);
				break;
			case NORTH:
				rotate(View.FRONT, Rotation.NORTH);
				rotate(View.BOTTOM, Rotation.NORTH);
				rotate(View.BACK, Rotation.SOUTH);
				rotate(View.LEFT, Rotation.EAST);
				rotate(View.RIGHT, Rotation.WEST);
				break;
			case SOUTH:
				rotate(View.FRONT, Rotation.SOUTH);
				rotate(View.BOTTOM, Rotation.SOUTH);
				rotate(View.BACK, Rotation.NORTH);
				rotate(View.LEFT, Rotation.WEST);
				rotate(View.RIGHT, Rotation.EAST);
				break;
			case WEST:
				rotate(View.FRONT, Rotation.WEST);
				rotate(View.BOTTOM, Rotation.WEST);
				rotate(View.BACK, Rotation.EAST);
				rotate(View.LEFT, Rotation.NORTH);
				rotate(View.RIGHT, Rotation.EAST);
				break;
			}
			break;
		case FRONT:
			switch (rotations.get(view)) {
			case EAST:
				break;
			case NORTH:
				rotate(View.FRONT, Rotation.NORTH);
				rotate(View.TOP, Rotation.NORTH);
				rotate(View.BOTTOM, Rotation.NORTH);
				rotate(View.LEFT, Rotation.NORTH);
				rotate(View.RIGHT, Rotation.NORTH);
				break;
			case SOUTH:
				rotate(View.FRONT, Rotation.SOUTH);
				rotate(View.TOP, Rotation.SOUTH);
				rotate(View.BOTTOM, Rotation.SOUTH);
				rotate(View.LEFT, Rotation.SOUTH);
				rotate(View.RIGHT, Rotation.SOUTH);
				break;
			case WEST:
				break;
			}
			break;
		case LEFT:
			switch (rotations.get(view)) {
			case EAST:
				break;
			case NORTH:
				rotate(View.FRONT, Rotation.NORTH);
				rotate(View.TOP, Rotation.EAST);
				rotate(View.BOTTOM, Rotation.WEST);
				rotate(View.LEFT, Rotation.NORTH);
				rotate(View.BACK, Rotation.NORTH);
				break;
			case SOUTH:
				rotate(View.FRONT, Rotation.SOUTH);
				rotate(View.TOP, Rotation.WEST);
				rotate(View.BOTTOM, Rotation.EAST);
				rotate(View.LEFT, Rotation.SOUTH);
				rotate(View.BACK, Rotation.SOUTH);
				break;
			case WEST:
				break;
			}
			break;
		case RIGHT:
			switch (rotations.get(view)) {
			case EAST:
				break;
			case NORTH:
				rotate(View.FRONT, Rotation.NORTH);
				rotate(View.TOP, Rotation.WEST);
				rotate(View.BOTTOM, Rotation.EAST);
				rotate(View.LEFT, Rotation.NORTH);
				rotate(View.BACK, Rotation.NORTH);
				break;
			case SOUTH:
				rotate(View.FRONT, Rotation.SOUTH);
				rotate(View.TOP, Rotation.EAST);
				rotate(View.BOTTOM, Rotation.WEST);
				rotate(View.LEFT, Rotation.SOUTH);
				rotate(View.BACK, Rotation.SOUTH);
				break;
			case WEST:
				break;
			}
			break;
		case TOP:
			switch (rotations.get(view)) {
			case EAST:
				rotate(View.FRONT, Rotation.EAST);
				rotate(View.TOP, Rotation.EAST);
				rotate(View.BACK, Rotation.WEST);
				rotate(View.LEFT, Rotation.NORTH);
				rotate(View.RIGHT, Rotation.SOUTH);
				break;
			case NORTH:
				rotate(View.FRONT, Rotation.NORTH);
				rotate(View.TOP, Rotation.NORTH);
				rotate(View.BACK, Rotation.SOUTH);
				rotate(View.LEFT, Rotation.WEST);
				rotate(View.RIGHT, Rotation.EAST);
				break;
			case SOUTH:
				rotate(View.FRONT, Rotation.SOUTH);
				rotate(View.TOP, Rotation.SOUTH);
				rotate(View.BACK, Rotation.NORTH);
				rotate(View.LEFT, Rotation.EAST);
				rotate(View.RIGHT, Rotation.WEST);
				break;
			case WEST:
				rotate(View.FRONT, Rotation.WEST);
				rotate(View.TOP, Rotation.WEST);
				rotate(View.BACK, Rotation.EAST);
				rotate(View.LEFT, Rotation.SOUTH);
				rotate(View.RIGHT, Rotation.NORTH);
				break;
			}
			break;
		}
		
		System.out.println("-=-");
		System.out.println(view + " -=- " + rotations.get(view));
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
		
		private View getDown (Cube cube) {
			switch (this) {
			case BACK:
				switch (cube.rotations.get(this)) {
				case EAST:
					return RIGHT;
				case NORTH:
					return BOTTOM; 
				case SOUTH:
					return TOP;  
				case WEST:
					return LEFT;
				}
			case BOTTOM:
				switch (cube.rotations.get(this)) {
				case EAST:
					return LEFT; 
				case NORTH:
					return BACK;  
				case SOUTH:
					return FRONT; 
				case WEST:
					return RIGHT;
				}
			case FRONT:
				switch (cube.rotations.get(this)) {
				case EAST:
					return LEFT;
				case NORTH:
					return BOTTOM;  
				case SOUTH:
					return TOP;  
				case WEST:
					return RIGHT;
				}
			case LEFT:
				switch (cube.rotations.get(this)) {
				case EAST:
					return RIGHT;
				case NORTH:
					return BOTTOM;  
				case SOUTH:
					return TOP;
				case WEST:
					return FRONT;
				}
			case RIGHT:
				switch (cube.rotations.get(this)) {
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
				switch (cube.rotations.get(this)) {
				case EAST:
					return LEFT;
				case NORTH:
					return FRONT; 
				case SOUTH:
					return BACK;  
				case WEST:
					return RIGHT;
				}
			default:
				break;
			}
			return null;
		}
		private View getLeft (Cube cube) {
			switch (this) {
			case BACK:
				switch (cube.rotations.get(this)) {
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
				switch (cube.rotations.get(this)) {
				case EAST:
					return FRONT;
				case NORTH:
					return LEFT;
				case SOUTH:
					return RIGHT;
				case WEST:
					return BACK;
				}
			case FRONT:
				switch (cube.rotations.get(this)) {
				case EAST:
					return TOP;
				case NORTH:
					return LEFT;
				case SOUTH:
					return RIGHT;
				case WEST:
					return RIGHT;
				}
			case LEFT:
				switch (cube.rotations.get(this)) {
				case EAST:
					return TOP;
				case NORTH:
					return BACK;
				case SOUTH:
					return FRONT;
				case WEST:
					return BOTTOM; 
				}
			case RIGHT:
				switch (cube.rotations.get(this)) {
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
				switch (cube.rotations.get(this)) {
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
		private View getRight (Cube cube) {
			switch (this) {
			case BACK:
				switch (cube.rotations.get(this)) {
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
				switch (cube.rotations.get(this)) {
				case EAST:
					return BACK;
				case NORTH:
					return RIGHT;
				case SOUTH:
					return LEFT;
				case WEST:
					return FRONT;
				}
			case FRONT:
				switch (cube.rotations.get(this)) {
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
				switch (cube.rotations.get(this)) {
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
				switch (cube.rotations.get(this)) {
				case EAST:
					return BOTTOM;
				case NORTH:
					return BACK;
				case SOUTH:
					return FRONT;
				case WEST:
					return BOTTOM;
				}
			case TOP:
				switch (cube.rotations.get(this)) {
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
		private View getUp (Cube cube) {
			switch (this) {
			case BACK:
				switch (cube.rotations.get(this)) {
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
				switch (cube.rotations.get(this)) {
				case EAST:
					return RIGHT;
				case NORTH:
					return FRONT;
				case SOUTH:
					return BACK;
				case WEST:
					return LEFT;
				}
			case FRONT:
				switch (cube.rotations.get(this)) {
				case EAST:
					return RIGHT;
				case NORTH:
					return TOP;
				case SOUTH:
					return BOTTOM;
				case WEST:
					return RIGHT;
				}
			case LEFT:
				switch (cube.rotations.get(this)) {
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
				switch (cube.rotations.get(this)) {
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
				switch (cube.rotations.get(this)) {
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
				return null;
			}
		}
		
		public View getView(Direction direction, Cube cube) {
			switch(direction) {
			case DOWN:
				return getDown(cube);
			case LEFT:
				return getLeft(cube);
			case RIGHT:
				return getRight(cube);
			case UP:
				return getUp(cube);
			case NONE:
				return this;
			}
			return null;
		}
	}
}
