package com.example.hotelbooking.statistics.service;

import com.example.hotelbooking.statistics.document.BookingStatistics;
import com.example.hotelbooking.statistics.document.UserRegistrationStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class StatisticsCsvService {

    private static final String HEADER = "event,userId,checkInDate,checkOutDate";
    private static final String REGISTRATION_EVENT = "registration";
    private static final String BOOKING_EVENT = "booking";
    private static final String SEPARATOR = ",";
    private static final String LINE_BREAK = "\n";

    private final StatisticsService statisticsService;

    public byte[] exportToCsv() {
        StringBuilder csv = new StringBuilder(HEADER).append(LINE_BREAK);
        for (UserRegistrationStatistics registration : statisticsService.findAllUserRegistrations()) {
            csv.append(REGISTRATION_EVENT).append(SEPARATOR)
                    .append(registration.getUserId()).append(SEPARATOR)
                    .append(SEPARATOR)
                    .append(LINE_BREAK);
        }
        for (BookingStatistics booking : statisticsService.findAllBookings()) {
            csv.append(BOOKING_EVENT).append(SEPARATOR)
                    .append(booking.getUserId()).append(SEPARATOR)
                    .append(booking.getCheckInDate()).append(SEPARATOR)
                    .append(booking.getCheckOutDate())
                    .append(LINE_BREAK);
        }
        return csv.toString().getBytes(StandardCharsets.UTF_8);
    }
}
