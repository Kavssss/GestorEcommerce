package frontend.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javafx.scene.control.TextField;

public class DoubleUtils {

//	public static String formataDouble(TextField textField) {
//		Double d = Double.valueOf(textField.getText());
//		Locale ptBr = new Locale("pt", "BR");
//		return NumberFormat.getCurrencyInstance(ptBr).format(d);
//	}
	
	
	public static String formataDouble(Double value) {
        return new DecimalFormat("#.00").format(value);
	}
	
}
