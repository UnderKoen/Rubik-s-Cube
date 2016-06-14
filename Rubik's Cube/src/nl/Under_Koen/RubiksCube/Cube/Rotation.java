package nl.Under_Koen.RubiksCube.Cube;

public enum Rotation {
	
	EAST("East"), 
	SOUTH("South"), 
	NORTH("North"),
	WEST("West");
	
	private String name;
	
	private Rotation(String name) {
		this.name = name;
	}
	
	public String toString () {
		return name;
	}
	
}
