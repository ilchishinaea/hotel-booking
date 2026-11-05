package com.example.hotelbooking.specification;

import com.example.hotelbooking.dto.HotelFilter;
import com.example.hotelbooking.entity.Hotel;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public final class HotelSpecification {

    private HotelSpecification() {
    }

    public static Specification<Hotel> withFilter(HotelFilter filter) {
        return Specification.where(byId(filter.id()))
                .and(contains("name", filter.name()))
                .and(contains("advertisementTitle", filter.advertisementTitle()))
                .and(contains("city", filter.city()))
                .and(contains("address", filter.address()))
                .and(byDistanceFromCenter(filter.distanceFromCenter()))
                .and(byRating(filter.rating()))
                .and(byNumberOfRatings(filter.numberOfRatings()));
    }

    private static Specification<Hotel> byId(Long id) {
        return id == null ? null : (root, query, builder) -> builder.equal(root.get("id"), id);
    }

    private static Specification<Hotel> contains(String field, String value) {
        return value == null ? null : (root, query, builder) ->
                builder.like(builder.lower(root.get(field)), "%" + value.toLowerCase() + "%");
    }

    private static Specification<Hotel> byDistanceFromCenter(Double distanceFromCenter) {
        return distanceFromCenter == null ? null : (root, query, builder) ->
                builder.lessThanOrEqualTo(root.get("distanceFromCenter"), distanceFromCenter);
    }

    private static Specification<Hotel> byRating(BigDecimal rating) {
        return rating == null ? null : (root, query, builder) ->
                builder.greaterThanOrEqualTo(root.get("rating"), rating);
    }

    private static Specification<Hotel> byNumberOfRatings(Integer numberOfRatings) {
        return numberOfRatings == null ? null : (root, query, builder) ->
                builder.greaterThanOrEqualTo(root.get("numberOfRatings"), numberOfRatings);
    }
}
