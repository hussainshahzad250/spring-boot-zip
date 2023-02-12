package com.sas.utils;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {


    public static LocalDateTime stringToDateTime(String localDateTimeString, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return StringUtils.hasText(localDateTimeString) ? LocalDateTime.parse(localDateTimeString, dateTimeFormatter) : null;
    }

    public static String localDateToString(LocalDate localDate, String dateFormat) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        return localDate != null ? dateFormatter.format(localDate) : null;
    }

    public static LocalDate stringToLocalDate(String localDateString, String dateFormat) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        return StringUtils.hasText(localDateString) ? LocalDate.parse(localDateString, dateFormatter) : null;
    }
}
