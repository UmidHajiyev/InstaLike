package com.umid.instalike.service;


import com.umid.instalike.dto.CreateUserRequest;
import com.umid.instalike.dto.UserResponse;
import com.umid.instalike.entity.User;
import com.umid.instalike.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers()
    {
        return userRepository
                .findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public UserResponse createUser(CreateUserRequest request)
    {
        User user = new User(
                request.username(),
                request.email());

        User savedUser = userRepository.save(user);
        return toResponse(savedUser);
    }

    public UserResponse getUserById(Long id)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found!"));
        return toResponse(user);
    }

    private UserResponse toResponse(User user)
    {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
