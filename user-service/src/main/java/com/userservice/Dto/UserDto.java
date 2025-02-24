package com.userservice.Dto;

import com.userservice.Model.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String username;

    @Email(message = "Enter a valid email address")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    @NotBlank(message = "Address cannot be blank")
    @Size(min = 4, max = 50, message = "Address must be between 6 and 20 characters")
    private String address;

    private Set<@Valid Role> roles = new HashSet<>();

}
