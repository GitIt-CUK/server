package shop.gitit.core.util;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    /**
     * Date를 당일 0시 0분 0초 LocalDateTime으로 변환
     *
     * @param date
     * @return localDateTime
     */
    public static LocalDateTime dateToLocalDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        LocalDateTime localDateTime =
                LocalDateTime.of(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH),
                        0,
                        0,
                        0);
        return localDateTime;
    }

    /**
     * Date를 LocalDateTime으로 변환 (초 단위)
     *
     * @param date
     * @return localDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        LocalDateTime localDateTime =
                LocalDateTime.of(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.HOUR),
                        calendar.get(Calendar.MINUTE),
                        calendar.get(Calendar.SECOND));
        return localDateTime;
    }

    /**
     * @return 당일 0시 0분 0초 데이터를 반환
     */
    public static LocalDateTime getTodayLocalDate() {
        Date today = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);

        return LocalDateTime.of(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH),
                0,
                0,
                0);
    }
}
