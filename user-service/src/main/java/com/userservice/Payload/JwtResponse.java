package com.userservice.Payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JwtResponse {

    private String jwtToken;
    private String refreshToken;
    private String username;

}
