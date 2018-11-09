package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	public Date transformaData(String date) {
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		dt.setLenient(false);
		Date data = new Date();
		if (!date.equals("")) {
			try {
				data = dt.parse(date);
			} catch (ParseException pe) {
				pe.printStackTrace();
			}
		}
		return data;
	}

}
