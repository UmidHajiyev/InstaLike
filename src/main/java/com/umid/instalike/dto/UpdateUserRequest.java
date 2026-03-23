package com.umid.instalike.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(

        @NotBlank(message = "Username is required")
        String username,


        @NotBlank(message ="Username is required")
        @Email(message = "Email must be valid")
        String email

) {
}
