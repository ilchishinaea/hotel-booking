package com.example.hotelbooking.service;

import com.example.hotelbooking.dto.RoomRequest;
import com.example.hotelbooking.dto.RoomResponse;
import com.example.hotelbooking.entity.Hotel;
import com.example.hotelbooking.entity.Room;
import com.example.hotelbooking.mapper.RoomMapper;
import com.example.hotelbooking.repository.HotelRepository;
import com.example.hotelbooking.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomMapper roomMapper;

    public RoomResponse findById(Long id) {
        return roomMapper.toResponse(getRoom(id));
    }

    @Transactional
    public RoomResponse create(RoomRequest request) {
        Room room = roomMapper.toEntity(request);
        room.setHotel(getHotel(request.hotelId()));
        return roomMapper.toResponse(roomRepository.save(room));
    }

    @Transactional
    public RoomResponse update(Long id, RoomRequest request) {
        Room room = getRoom(id);
        roomMapper.updateEntity(request, room);
        room.setHotel(getHotel(request.hotelId()));
        return roomMapper.toResponse(roomRepository.save(room));
    }

    @Transactional
    public void delete(Long id) {
        roomRepository.delete(getRoom(id));
    }

    private Room getRoom(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Комната с id " + id + " не найдена"));
    }

    private Hotel getHotel(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Отель с id " + id + " не найден"));
    }
}
