package frontend.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DataUtils {

	public static final String MAX_DATE = "9999-12-31";
	public static final String MIN_DATE = "0001-01-01";
	public final Date TODAY = Date.valueOf(LocalDate.now());
	
	public static String addBarraData(String data) {
		if (data.length() == 2)
			return data.concat("/");
		if (data.length() == 5)
			return data.concat("/");
		return data;
	}

	public static Date stringToDate(String in) { // entrada no formato dd/MM/yyyy
		if (in.isBlank())
			return null;

		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			return new java.sql.Date(formato.parse(in).getTime());
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date stringExcelToDate(String in) { // entrada no formato yyyy-MM-dd
		if (in.isBlank())
			return null;

		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			return new java.sql.Date(formato.parse(in).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	public static String dateToString(Date in) { // saída no formato dd/MM/yyyy
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(in);
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

	public static List<String> getListMeses() {
		return Arrays.asList(Constants.MES.JANEIRO, Constants.MES.FEVEREIRO, Constants.MES.MARCO, Constants.MES.ABRIL,
				Constants.MES.MAIO, Constants.MES.JUNHO, Constants.MES.JULHO, Constants.MES.AGOSTO,
				Constants.MES.SETEMBRO, Constants.MES.OUTUBRO, Constants.MES.NOVEMBRO, Constants.MES.DEZEMBRO);
	}

	public static List<String> getListSemestres() {
		return Arrays.asList(Constants.SEMESTRE.PRIMEIRO, Constants.SEMESTRE.SEGUNDO);
	}

	public static List<String> getListDias(Integer mes) {
		List<String> dias = new ArrayList<>();
		for (int i = 1; i <= 28; i++)
			dias.add(String.valueOf(i));

		if ((mes >= 1 || mes <= 12) && !mes.equals(2)) {
			dias.addAll(Arrays.asList("29", "30"));
			switch (mes) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				dias.add("31");
				break;
			}
		}
		return dias;
	}

	public static Integer converteMesToInt(String mes) {
		if (Objects.isNull(mes))
			return null;

		switch (mes) {
		case Constants.MES.JANEIRO:
			return 1;
		case Constants.MES.FEVEREIRO:
			return 2;
		case Constants.MES.MARCO:
			return 3;
		case Constants.MES.ABRIL:
			return 4;
		case Constants.MES.MAIO:
			return 5;
		case Constants.MES.JUNHO:
			return 6;
		case Constants.MES.JULHO:
			return 7;
		case Constants.MES.AGOSTO:
			return 8;
		case Constants.MES.SETEMBRO:
			return 9;
		case Constants.MES.OUTUBRO:
			return 10;
		case Constants.MES.NOVEMBRO:
			return 11;
		case Constants.MES.DEZEMBRO:
			return 12;
		default:
			return null;
		}
	}

}
