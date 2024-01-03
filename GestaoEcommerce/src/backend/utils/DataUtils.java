package backend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

	public static Date stringExcelToDate(String in) { // entrada no formato dd/mmm./aaaa
		String dia = in.substring(0, 2);
		String mes = retornaMes(in.substring(3, 6));
		String ano = in.substring(8, 12);

		StringBuilder data = new StringBuilder(dia).append("/").append(mes).append("/").append(ano);
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return formato.parse(data.toString());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date stringToDate(String in) { // entrada no formato dd/MM/yyyy
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return formato.parse(in);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String formataData(Integer dia, Integer mes, Integer ano) {
		StringBuilder data = new StringBuilder();
		data.append(dia).append("-").append(mes).append("-").append(ano);
		return data.toString();
	}

	public static String formataMes(Integer mes) {
		return mes < 10 ? "0".concat(mes.toString()) : mes.toString();
	}

	private static String retornaMes(String in) {
		switch (in) {
		case "jan":
			return "01";
		case "fev":
			return "02";
		case "mar":
			return "03";
		case "abr":
			return "04";
		case "mai":
			return "05";
		case "jun":
			return "06";
		case "jul":
			return "07";
		case "ago":
			return "08";
		case "set":
			return "09";
		case "out":
			return "10";
		case "nov":
			return "11";
		case "dez":
			return "12";
		default:
			return null;
		}
	}

}
