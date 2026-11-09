package com.example.hotelbooking.statistics.repository;

import com.example.hotelbooking.statistics.document.BookingStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingStatisticsRepository extends MongoRepository<BookingStatistics, String> {
}
