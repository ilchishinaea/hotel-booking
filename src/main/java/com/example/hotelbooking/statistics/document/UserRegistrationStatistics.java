package com.example.hotelbooking.statistics.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_registration_statistics")
@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationStatistics {

    @Id
    private String id;

    private Long userId;
}
