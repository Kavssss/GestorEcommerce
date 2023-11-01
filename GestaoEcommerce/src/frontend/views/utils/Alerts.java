package frontend.views.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {
	
	private static Boolean isConfirmed = Boolean.FALSE;
	
	public static void showAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static Boolean confirmationAlert(String title, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setContentText(content);
        
        alert.showAndWait().ifPresent(response -> {
			if (response == alert.getButtonTypes().get(0))
				isConfirmed = Boolean.TRUE;
			else if (response == alert.getButtonTypes().get(1))
				isConfirmed = Boolean.FALSE;
        });
        return isConfirmed;
	}
	
}
