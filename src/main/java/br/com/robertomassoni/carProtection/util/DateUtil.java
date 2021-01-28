package br.com.robertomassoni.carProtection.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    private static final SimpleDateFormat formatterYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

    public static Date today() {
        return new Date();
    }

    public static String todayStr() {
        return formatterYYYYMMDD.format(today());
    }

    public static String formattedDate(Date date) {
        return date != null ? formatterYYYYMMDD.format(date) : todayStr();
    }
    
    public static Date parseFormattedDate(String date) throws ParseException {
         return formatterYYYYMMDD.parse(date);
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

}
