package com.umid.instalike.dto;

import java.time.LocalDateTime;

public record PostResponse(

        Long id,
        String caption,
        LocalDateTime createdAt,
        Long authorId,
        String authorUsername
) {
}
