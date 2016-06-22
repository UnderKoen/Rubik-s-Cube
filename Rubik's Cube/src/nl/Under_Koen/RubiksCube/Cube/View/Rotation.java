package nl.Under_Koen.RubiksCube.Cube.View;

public enum Rotation {
	
	EAST("East", 1), 
	SOUTH("South", 2), 
	NORTH("North", 0),
	WEST("West", 3);
	
	private String name;
	private int id;
	
	private Rotation(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public int getId () {
		return id;
	}
	
	public String toString () {
		return name;
	}
	
	public Rotation flip() {
		switch (this) {
		case EAST:
			return WEST;
		case WEST:
			return EAST;
		case NORTH:
			return SOUTH;
		case SOUTH:
			return NORTH;
		default:
			return null;
		}
	}
	
	public static Rotation getRotation(int id) {
		switch(id) {
		case 0:
			return Rotation.NORTH;
		case 1:
			return Rotation.EAST;
		case 2:
			return Rotation.SOUTH;
		case 3:
			return Rotation.WEST;
		}
		return null;
	}
	
}
