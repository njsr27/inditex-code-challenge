package com.klagan.inditex.service.formatter;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.klagan.inditex.constants.DateConstants.ACCEPTED_DATE_FORMAT;

@Component
public class DateFormatter {
    public String format(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ACCEPTED_DATE_FORMAT);
        return localDateTime.format(formatter);
    }

    public LocalDateTime format(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ACCEPTED_DATE_FORMAT);
        return LocalDateTime.parse(date, formatter);
    }
}
