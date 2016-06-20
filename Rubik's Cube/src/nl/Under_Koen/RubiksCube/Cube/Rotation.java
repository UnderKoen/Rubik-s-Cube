package nl.Under_Koen.RubiksCube.Cube;

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
