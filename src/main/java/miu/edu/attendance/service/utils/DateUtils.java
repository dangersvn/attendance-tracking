package miu.edu.attendance.service.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateUtils {

    /**
     * Get Business days between two dates. Reference: https://howtodoinjava.com/java/date-time/calculate-business-days/
     * @param startDate
     * @param endDate
     * @param holidays
     * @return
     */
    public static List<LocalDate> getBusinessDaysBetween(LocalDate startDate, LocalDate endDate,
                                                            Optional<List<LocalDate>> holidays)
    {
        if (startDate == null || endDate == null || holidays == null) {
            throw new IllegalArgumentException("Invalid method argument(s) to countBusinessDaysBetween(" + startDate
                    + "," + endDate + "," + holidays + ")");
        }

        Predicate<LocalDate> isHoliday = date -> holidays.isPresent() ? holidays.get().contains(date) : false;

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        return Stream.iterate(startDate, date -> date.plusDays(1)).limit(daysBetween)
                .filter(isHoliday.or(isWeekend).negate()).collect(Collectors.toList());

    }
}
