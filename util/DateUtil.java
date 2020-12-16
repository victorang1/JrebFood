package util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
    
    public static Date getCurrentDateWithTimezone() {
        TimeZone timeZone = TimeZone.getTimeZone("UTC+7");
        Calendar cal = Calendar.getInstance(timeZone);
        return cal.getTime();
    }

    public static Date parseDate(String str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            return format.parse(str);
        } catch (Exception e) {
            return null;
        }
    }
}
