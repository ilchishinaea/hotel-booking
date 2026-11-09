package com.example.hotelbooking.statistics.service;

import com.example.hotelbooking.statistics.document.BookingStatistics;
import com.example.hotelbooking.statistics.document.UserRegistrationStatistics;
import com.example.hotelbooking.statistics.event.RoomBookedEvent;
import com.example.hotelbooking.statistics.event.UserRegisteredEvent;
import com.example.hotelbooking.statistics.repository.BookingStatisticsRepository;
import com.example.hotelbooking.statistics.repository.UserRegistrationStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final UserRegistrationStatisticsRepository userRegistrationStatisticsRepository;
    private final BookingStatisticsRepository bookingStatisticsRepository;

    public void saveUserRegistration(UserRegisteredEvent event) {
        UserRegistrationStatistics statistics = new UserRegistrationStatistics();
        statistics.setUserId(event.userId());
        userRegistrationStatisticsRepository.save(statistics);
    }

    public void saveRoomBooking(RoomBookedEvent event) {
        BookingStatistics statistics = new BookingStatistics();
        statistics.setUserId(event.userId());
        statistics.setCheckInDate(event.checkInDate());
        statistics.setCheckOutDate(event.checkOutDate());
        bookingStatisticsRepository.save(statistics);
    }

    public List<UserRegistrationStatistics> findAllUserRegistrations() {
        return userRegistrationStatisticsRepository.findAll();
    }

    public List<BookingStatistics> findAllBookings() {
        return bookingStatisticsRepository.findAll();
    }
}
