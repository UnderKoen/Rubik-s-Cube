package nl.Under_Koen.RubiksCube.Cube.View;

public enum Direction {
	NONE,
	LEFT,
	RIGHT,
	UP,
	DOWN,
	OPPOSITE;
	
	public Direction flip() {
		switch (this) {
		case DOWN:
			return UP;
		case LEFT:
			return RIGHT;
		case NONE:
			return OPPOSITE;
		case OPPOSITE:
			return NONE;
		case RIGHT:
			return LEFT;
		case UP:
			return DOWN;
		default:
			return null;
		}
	}
}
