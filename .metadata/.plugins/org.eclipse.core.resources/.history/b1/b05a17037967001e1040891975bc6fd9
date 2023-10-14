package backend.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtils {
	
	public static LocalDate stringExcelToLocalDate(String in) {  // entrada no formato dd/mmm./aaaa
		String dia = in.substring(0, 2);
		String mes = retornaMes(in.substring(3, 6));
		String ano = in.substring(8, 12);
		
		StringBuilder data = new StringBuilder(dia).append("/").append(mes).append("/").append(ano);
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		return LocalDate.parse(data.toString(), formato);
	}
	
	public static LocalDate stringToLocalDate(String in) {  // entrada no formato dd/MM/yyyy
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyy"); 
		return LocalDate.parse(in, formato);
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
		switch(in) {
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

