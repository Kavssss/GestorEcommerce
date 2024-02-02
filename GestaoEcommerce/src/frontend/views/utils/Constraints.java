package frontend.views.utils;

import frontend.utils.enums.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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

	public static void setReal(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				if (!newValue.isEmpty() && newValue.charAt(0) == ',') {
					if (newValue.length() == 1)
						txt.setText("0,");
					else
						txt.setText("0,".concat(oldValue.split(",")[1]));
					return;
				}
				
	            if (!newValue.matches("\\d*([\\,]\\d*)?")) {
	                txt.setText(oldValue);
	            }
	            
	            if (newValue.split(",").length > 1) {
	                if (newValue.split(",")[1].length() > 2) {
	                	txt.setText(oldValue);
	                }
	            }
			}
        });

		txt.focusedProperty().addListener((obss, oldVal, newVal) -> {
			if (txt.getText().isEmpty())
				return;
			
			if (!newVal) {  // desfocou
				String texto = txt.getText().replace("R$ ", "");  //   1,32
				String[] split = texto.split(","); //  
				String saida = "";

				if (split.length == 1) {
					saida = split[0].concat(",00");
				} else {
					if (split[1].length() == 1) {
						saida = split[0].concat("0");
					}
				}
				
//				txt.setText("R$ " + texto);
				txt.setText(texto);
			}
		});
    }

    private static void formatCurrency(TextField txt) {
        String plainText = txt.getText().replaceAll("[^\\d]", "").replace(",", "");

        if (!plainText.isEmpty()) {
            // Converte para valor monetário formatado
            double value = Double.parseDouble(plainText) / 100.0;
            String formattedText = String.format("R$%.2f", value);

            // Define o texto formatado no TextField
            txt.setText(formattedText);
        }
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
