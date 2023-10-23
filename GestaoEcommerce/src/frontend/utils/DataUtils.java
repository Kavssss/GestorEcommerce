package frontend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.sql.Date;

public class DataUtils {

	public static String addBarraData(String data) {
		if (data.length() == 2)
			return data.concat("/");
		if (data.length() == 5)
			return data.concat("/");
		return data;
	}
	
	public static Date stringToDate(String in) {  // entrada no formato dd/MM/yyyy
		if (in.isBlank())
			return null;

		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			return new java.sql.Date(formato.parse(in).getTime());
		} catch (ParseException e) {
			return null;
		}		
	}
	
	public static void formataData(TextField textField, KeyEvent event) {
		String text = textField.getText();
		
		if (text.contains("//"))
			textField.setText(text.replace("//", "/"));

		if (event.getCode() != KeyCode.BACK_SPACE) {
			textField.setText(DataUtils.addBarraData(text));
			if (text.length() == 3) {
				String formattedDate = new StringBuilder(text.substring(0, 2)).append("/").append(text.substring(2))
						.toString();
				textField.setText(formattedDate);
			}
			if (text.length() == 6) {
				String formattedDate = new StringBuilder(text.substring(0, 5)).append("/").append(text.substring(5))
						.toString();
				textField.setText(formattedDate);
			}
			textField.positionCaret(textField.getLength());
		}
	}

}
