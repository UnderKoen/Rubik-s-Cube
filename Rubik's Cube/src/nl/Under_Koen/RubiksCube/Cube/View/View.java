package nl.Under_Koen.RubiksCube.Cube.View;

import nl.Under_Koen.RubiksCube.Cube.Cube;

public enum View {
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
				return BACK;
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
				return FRONT;
			case NORTH:
				return BOTTOM;
			case SOUTH:
				return TOP; 
			case WEST:
				return BACK;
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
				return BOTTOM;
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
				return BOTTOM;
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
				return BOTTOM;
			case NORTH:
				return LEFT;
			case SOUTH:
				return RIGHT;
			case WEST:
				return TOP;
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
				return BOTTOM;
			case NORTH:
				return RIGHT;
			case SOUTH:
				return LEFT;
			case WEST:
				return TOP;
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
				return TOP;
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
				return LEFT;
			case NORTH:
				return TOP;
			case SOUTH:
				return BOTTOM;
			case WEST:
				return RIGHT;
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
				return LEFT;
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
				return FRONT;
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
	private View getOpposite (Cube cube) {
		switch (this) {
		case BACK:
			return FRONT;
		case BOTTOM:
			return TOP;
		case FRONT:
			return BACK;
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		case TOP:
			return BOTTOM;
		}
		return null;
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
		case OPPOSITE:
			return getOpposite(cube);
		}
		return null;
	}
}
