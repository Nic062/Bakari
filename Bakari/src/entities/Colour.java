package entities;

import java.awt.Color;

public enum Colour {
	GREEN,
	PINK,
	BLUE,
	YELLOW,
	WHITE,
	BLACK,
	ORANGE,
	BROWN;
	
	public static Color colourToColor(Colour c) {
		switch(c) {
		case GREEN:
			return Color.GREEN;
		case PINK:
			return Color.PINK;
		case BLUE:
			return Color.BLUE;
		case YELLOW:
			return Color.YELLOW;
		case WHITE:
			return Color.WHITE;
		case BLACK:
			return Color.BLACK;
		case ORANGE:
			return Color.ORANGE;
		case BROWN:
			return new Color(180, 120, 80);
		default:
			return null;
		}
	}
}
