package nl.Under_Koen.RubiksCube.Cube.Row;

public interface Row {
	enum VRow implements Row{
		LEFT,
		MIDDLE,
		RIGHT
	}

	enum HRow implements Row {
		TOP,
		MIDDLE,
		BOTTOM
	}
}
