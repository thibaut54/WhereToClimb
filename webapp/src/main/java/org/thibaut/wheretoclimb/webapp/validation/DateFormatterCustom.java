package org.thibaut.wheretoclimb.webapp.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class DateFormatterCustom {

	public static Date getCurrentDate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Date date = new Date (dtf.format(now));
		return date;
	}
}
