package frontend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

	public static String addBarraData(String data) {
		if (data.length() == 2)
			return data.concat("/");
		if (data.length() == 5)
			return data.concat("/");
		return data;
	}
	
	public static Date stringToDate(String in) {  // entrada no formato dd/MM/yyy
		if (in.isBlank())
			return null;

		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			return formato.parse(in);
		} catch (ParseException e) {
			return null;
		}		
	}

}
