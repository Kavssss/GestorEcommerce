package frontend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

}
