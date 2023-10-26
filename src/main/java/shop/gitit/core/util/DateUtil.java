package shop.gitit.core.util;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    private static final int zero = 0;

    /**
     * Date를 당일 0시 0분 0초 LocalDateTime으로 변환
     *
     * @param date
     * @return localDateTime
     */
    public static LocalDateTime dateToLocalDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return LocalDateTime.of(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH),
                zero,
                zero,
                zero);
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
        return LocalDateTime.of(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND));
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
                zero,
                zero,
                zero);
    }

    public static boolean isToday(LocalDateTime stdDate, LocalDateTime target) {
        return target.compareTo(stdDate) == 0;
    }

    public static boolean isPast(LocalDateTime stdDate, LocalDateTime target) {
        return target.compareTo(stdDate) < 0;
    }

    public static boolean isFuture(LocalDateTime stdDate, LocalDateTime target) {
        return target.compareTo(stdDate) > 0;
    }
}