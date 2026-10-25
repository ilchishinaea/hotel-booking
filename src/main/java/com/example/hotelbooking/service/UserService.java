package com.example.hotelbooking.service;

import com.example.hotelbooking.dto.UserRequest;
import com.example.hotelbooking.dto.UserResponse;
import com.example.hotelbooking.entity.User;
import com.example.hotelbooking.mapper.UserMapper;
import com.example.hotelbooking.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse findById(Long id) {
        return userMapper.toResponse(getUser(id));
    }

    public UserResponse findByUsername(String username) {
        return userMapper.toResponse(getUserByUsername(username));
    }

    @Transactional
    public UserResponse create(UserRequest request) {
        if (userRepository.existsByUsernameAndEmail(request.username(), request.email())) {
            throw new IllegalArgumentException("Пользователь с таким именем и почтой уже зарегистрирован");
        }
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("Имя пользователя " + request.username() + " уже занято");
        }
        return userMapper.toResponse(userRepository.save(userMapper.toEntity(request)));
    }

    @Transactional
    public UserResponse update(Long id, UserRequest request) {
        User user = getUser(id);
        userMapper.updateEntity(request, user);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Transactional
    public void delete(Long id) {
        userRepository.delete(getUser(id));
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id " + id + " не найден"));
    }

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь " + username + " не найден"));
    }
}
