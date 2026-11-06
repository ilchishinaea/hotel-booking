package com.example.hotelbooking.specification;

import com.example.hotelbooking.dto.RoomFilter;
import com.example.hotelbooking.entity.Booking;
import com.example.hotelbooking.entity.Room;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class RoomSpecification {

    private RoomSpecification() {
    }

    public static Specification<Room> withFilter(RoomFilter filter) {
        return Specification.where(byId(filter.id()))
                .and(byName(filter.name()))
                .and(byMinPrice(filter.minPrice()))
                .and(byMaxPrice(filter.maxPrice()))
                .and(byMaxGuests(filter.maxGuests()))
                .and(byHotelId(filter.hotelId()))
                .and(freeBetween(filter.checkInDate(), filter.checkOutDate()));
    }

    private static Specification<Room> byId(Long id) {
        return id == null ? null : (root, query, builder) -> builder.equal(root.get("id"), id);
    }

    private static Specification<Room> byName(String name) {
        return name == null ? null : (root, query, builder) ->
                builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    private static Specification<Room> byMinPrice(BigDecimal minPrice) {
        return minPrice == null ? null : (root, query, builder) ->
                builder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    private static Specification<Room> byMaxPrice(BigDecimal maxPrice) {
        return maxPrice == null ? null : (root, query, builder) ->
                builder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    private static Specification<Room> byMaxGuests(Integer maxGuests) {
        return maxGuests == null ? null : (root, query, builder) ->
                builder.greaterThanOrEqualTo(root.get("maxGuests"), maxGuests);
    }

    private static Specification<Room> byHotelId(Long hotelId) {
        return hotelId == null ? null : (root, query, builder) ->
                builder.equal(root.get("hotel").get("id"), hotelId);
    }

    private static Specification<Room> freeBetween(LocalDate checkInDate, LocalDate checkOutDate) {
        if (checkInDate == null || checkOutDate == null) {
            return null;
        }
        return (root, query, builder) -> {
            Subquery<Long> overlapping = query.subquery(Long.class);
            Root<Booking> booking = overlapping.from(Booking.class);
            overlapping.select(booking.get("id"));
            overlapping.where(
                    builder.equal(booking.get("room").get("id"), root.get("id")),
                    builder.lessThan(booking.get("checkInDate"), checkOutDate),
                    builder.greaterThan(booking.get("checkOutDate"), checkInDate));
            return builder.not(builder.exists(overlapping));
        };
    }
}
