package com.userservice.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {

    @Email(message = "Enter a valid email address")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;

}
