package cs555.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AgeUtils {
	public static long getAge(LocalDate date) {
		return ChronoUnit.YEARS.between(date, LocalDate.now());
	}

	public static long getYearGap(LocalDate date1, LocalDate date2) {
		return ChronoUnit.YEARS.between(date1, date2);
	}

	public static long getMonthGap(LocalDate date1, LocalDate date2) {
		return ChronoUnit.MONTHS.between(date1, date2);
	}

	public static String getLastName(String name) {
		String lastname = name;
		String[] sname = name.split("\\/", 3);
		if (sname.length==3)
			lastname = sname[1];
		return lastname;
	}
}
