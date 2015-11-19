package cs555.util;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.HashMap;
import java.util.Map;

public class AgeUtils {
	public static long getAge(LocalDate date) {
		return ChronoUnit.YEARS.between(date, LocalDate.now());
	}

	public static long getAgeDays(LocalDate date) {
		return ChronoUnit.DAYS.between(date, LocalDate.now());
	}
	

	/**
	 * Compare two dates by Year, Month, Day
	 * 
	 * @param type
	 *            (=0:Year, =1:Month, =2:Day)
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getTimeGap(int type, LocalDate date1, LocalDate date2) {
		if (type == 0)
			return ChronoUnit.YEARS.between(date1, date2);
		if (type == 1)
			return ChronoUnit.MONTHS.between(date1, date2);
		if (type == 2)
			return date2.get(ChronoField.DAY_OF_YEAR) - date1.get(ChronoField.DAY_OF_YEAR);
		return 0;
	}

	public static String getLastName(String name) {
		String lastname = name;
		String[] sname = name.split("\\/", 3);
		if (sname.length == 3)
			lastname = sname[1];
		return lastname;
	}

	public static boolean isBadDate(String date) {
		Map<String, Integer> months = new HashMap<>();
		months.put("JAN", 1);
		months.put("FEB", 2);
		months.put("MAR", 3);
		months.put("APR", 4);
		months.put("MAY", 5);
		months.put("JUN", 6);
		months.put("JUL", 7);
		months.put("AUG", 8);
		months.put("SEP", 9);
		months.put("OCT", 10);
		months.put("NOV", 11);
		months.put("DEC", 12);
		String[] split = date.split(" ");
		int day = Integer.parseInt(split[0]);
		int month = months.get(split[1]);
		return ((month == 2 && day > 29) || (((month == 1) || (month == 2) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) && day > 31) || (((month == 4) || (month == 6) || (month == 9) || (month == 11)) && day > 30));
	}
}
