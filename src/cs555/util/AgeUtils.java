package cs555.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AgeUtils {
    public static long getAge(LocalDate date) {
        return ChronoUnit.YEARS.between(date, LocalDate.now());
    }
}
