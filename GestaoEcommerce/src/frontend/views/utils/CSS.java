package frontend.views.utils;

import frontend.utils.ColorUtils;
import javafx.scene.paint.Color;

public interface CSS {

	interface COLOR {
		String WHITE = "#fff";
		String BLACK_BLUE = "linear-gradient(to top, #000, #002)";
		String GREEN = "#0f0";
	}
	
	interface FIELD {
		String BACKGROUND_COLOR = "-fx-background-color";
		String BACKGROUND_RADIUS = "-fx-background-radius";
	}
	
	interface LIGHT_THEME {
		String BACKGROUND = "-fx-background-color: linear-gradient(to bottom,"
				+ ColorUtils.colorConversion(Color.LIGHTGRAY) + ","
				+ ColorUtils.colorConversion(Color.GRAY) + ")";
	}
	
}
