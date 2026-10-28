package com.example.hotelbooking.repository;

import com.example.hotelbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByRoomIdAndCheckInDateLessThanAndCheckOutDateGreaterThan(
            Long roomId, LocalDate checkOutDate, LocalDate checkInDate);
}
