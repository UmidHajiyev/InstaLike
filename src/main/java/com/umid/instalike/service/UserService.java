package com.umid.instalike.service;


import com.umid.instalike.dto.CreateUserRequest;
import com.umid.instalike.dto.UpdateUserRequest;
import com.umid.instalike.dto.UserResponse;
import com.umid.instalike.entity.User;
import com.umid.instalike.repository.UserRepository;
import org.springframework.http.HttpStatus;
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
        if(userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already exist");
        }
        if (userRepository.existsByUsername(request.username())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username already exist");
        }


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
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

    public UserResponse updateUser(Long id, UpdateUserRequest request)
    {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not Found"));

        if ( !user.getUsername().equals(request.username())
                && userRepository.existsByUsername(request.username())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username already exist");
        }
        if(!user.getEmail().equals(request.email()) &&
                userRepository.existsByEmail(request.email())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already exist");
        }

        user.setUsername(request.username());
        user.setEmail(request.email());
        User updatedUser = userRepository.save(user);
        return toResponse(updatedUser);
    }

    public void deleteUser(Long id)
    {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));

        userRepository.deleteById(id);


    }
}
