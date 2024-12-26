package vn.com.t3h.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String MMMM_YY_DD="yyyy-MM-dd";
    public static LocalDate strToDate(String str) {
        if (str == null || str.isEmpty()){
            return null;
        }
        str = str.trim();
        return LocalDate.parse(str, DateTimeFormatter.ofPattern(MMMM_YY_DD));
    }
}
