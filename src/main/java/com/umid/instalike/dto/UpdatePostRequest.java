package com.umid.instalike.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePostRequest(

        @NotBlank(message = "Caption is required")
        String caption
) {
}
