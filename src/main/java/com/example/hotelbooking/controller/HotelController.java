package com.example.hotelbooking.controller;

import com.example.hotelbooking.dto.HotelListResponse;
import com.example.hotelbooking.dto.HotelRequest;
import com.example.hotelbooking.dto.HotelResponse;
import com.example.hotelbooking.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/{id}")
    public HotelResponse findById(@PathVariable Long id) {
        return hotelService.findById(id);
    }

    @GetMapping
    public HotelListResponse findAll() {
        return hotelService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelResponse create(@Valid @RequestBody HotelRequest request) {
        return hotelService.create(request);
    }

    @PutMapping("/{id}")
    public HotelResponse update(@PathVariable Long id, @Valid @RequestBody HotelRequest request) {
        return hotelService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        hotelService.delete(id);
    }
}
