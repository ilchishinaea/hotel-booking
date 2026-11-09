package com.example.hotelbooking.statistics.repository;

import com.example.hotelbooking.statistics.document.UserRegistrationStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationStatisticsRepository extends MongoRepository<UserRegistrationStatistics, String> {
}
