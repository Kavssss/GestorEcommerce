package frontend.views.utils;

import frontend.utils.enums.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Constraints {

	public static TipoUsuario usuario;
	
	public static void setTextFieldNumber(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches("\\d*")) {
				txt.setText(oldValue);
			}
		});
	}

	public static void setTextFieldNumberBar(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches("[\\d/]*")) {
				txt.setText(oldValue);
			}
		});
	}

	public static void setTextFieldMaxLength(TextField txt, int max) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && newValue.length() > max) {
				txt.setText(oldValue);
			}
		});
	}
	
	public static void setNoSpaceTextField(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && newValue.matches(".*[\\s].*")) {
				txt.setText(oldValue);
			}
		});
	}
	
	public static Boolean validatePassword(String password) {
		return password.length() > 3;
	}

	public static void setTextFieldDouble(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches("\\d*([\\.]\\d*)?")) {
				txt.setText(oldValue);
			}
		});
	}

	public static void hoverEffect(Button btn) {
		String styles = btn.getStyle();
		btn.setStyle(styles.concat(" -fx-background-color: grey;"));
	}

	public static void unhoverEffect(Button btn) {
		String styles = btn.getStyle();
		btn.setStyle(styles.replace(" -fx-background-color: grey;", ""));

	}

	public static Stage currentStage(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	public static Stage currentStage(MouseEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	public static TipoUsuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(TipoUsuario usuario) {
		Constraints.usuario = usuario;
	}

}
