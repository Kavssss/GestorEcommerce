package frontend.utils;

import javafx.scene.paint.Color;

public class ColorUtils {

	public static String colorConversion(Color color) {
		return color.toString().replace("0x", "#");
	}
	
}
