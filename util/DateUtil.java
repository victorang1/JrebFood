package util;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    
    public static Date getCurrentDateWithTimezone() {
        TimeZone timeZone = TimeZone.getTimeZone("UTC+7");
        Calendar cal = Calendar.getInstance(timeZone);
        return cal.getTime();
    }
}
