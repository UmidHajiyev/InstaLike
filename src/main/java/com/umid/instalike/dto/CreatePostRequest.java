package com.umid.instalike.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePostRequest(
        @NotBlank(message = "Caption is required")
        String caption,
        @NotNull(message = "Author id is required")
        Long authorId
        ) {
}
