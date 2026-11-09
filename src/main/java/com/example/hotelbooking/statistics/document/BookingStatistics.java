package com.example.hotelbooking.statistics.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "booking_statistics")
@Getter
@Setter
@NoArgsConstructor
public class BookingStatistics {

    @Id
    private String id;

    private Long userId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;
}
