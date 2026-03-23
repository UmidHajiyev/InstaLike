package com.umid.instalike.dto;

import java.time.LocalDateTime;

public record UserResponse(
        Long Id,
        String username,
        String email,
        LocalDateTime createdAt
) {}
