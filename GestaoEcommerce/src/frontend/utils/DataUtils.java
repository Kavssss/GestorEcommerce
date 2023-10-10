package frontend.utils;

public class DataUtils {

	public static String addBarraData(String data) {
		if (data.length() == 2)
			return data.concat("/");
		if (data.length() == 5)
			return data.concat("/");
		return data;
	}

}
