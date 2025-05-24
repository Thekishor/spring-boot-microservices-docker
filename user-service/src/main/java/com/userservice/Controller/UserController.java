package com.userservice.Controller;

import com.userservice.Dto.RefreshTokenRequest;
import com.userservice.Dto.UserDto;
import com.userservice.Model.RefreshToken;
import com.userservice.Model.Role;
import com.userservice.Payload.JwtResponse;
import com.userservice.Payload.LoginRequest;
import com.userservice.Response.ApiResponse;
import com.userservice.Service.JwtService;
import com.userservice.Service.RefreshTokenService;
import com.userservice.Service.UserDetailsServiceImpl;
import com.userservice.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final UserDetailsServiceImpl userInfoDetails;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto userInfo = userService.createUser(userDto);
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest){
        try {
            doAuthenticate(loginRequest.getEmail(), loginRequest.getPassword());
            UserDetails userDetails = userInfoDetails.loadUserByUsername(loginRequest.getEmail());

            Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

            if(roles.contains("ADMIN") || roles.contains("CREATOR") || roles.contains("EDITOR")){
                String token = jwtService.generateToken(userDetails.getUsername(), roles);

                RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());
                JwtResponse response = JwtResponse.builder()
                        .jwtToken(token)
                        .refreshToken(refreshToken.getToken())
                        .username(userDetails.getUsername())
                        .build();
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("You do not have permission to access this service",HttpStatus.FORBIDDEN);
            }
        }catch (BadCredentialsException exception){
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }catch (Exception exception){
            return new ResponseEntity<>("Internal server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticationToken);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        try {
            RefreshToken refreshToken = refreshTokenService.verifyExpiration(refreshTokenService.findByToken(refreshTokenRequest.getRefreshToken())
                    .orElseThrow(()-> new RuntimeException("Invalid refresh token")));
            String token = jwtService.generateToken(refreshToken.getUser().getEmail(),refreshToken.getUser().getRoles()
                    .stream().map(Role::getName).collect(Collectors.toSet()));
            JwtResponse response = JwtResponse.builder()
                    .jwtToken(token)
                    .refreshToken(refreshToken.getToken())
                    .username(refreshToken.getUser().getEmail())
                    .build();
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (RuntimeException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("userId") Integer id){
        UserDto userDto = userService.findUserById(id);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> findAllUsers(){
        List<UserDto> userDtos = userService.findAllUsers();
        return new ResponseEntity<>(userDtos,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully with id",true),HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Integer id, @Valid @RequestBody UserDto userDto){
        UserDto updateUser = userService.updateUser(id,userDto);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<?> findUserByName(@RequestParam String username){
        try {
            UserDto userDto = userService.findUserByName(username);
            return new ResponseEntity<>(userDto,HttpStatus.OK);
        }
        catch(IllegalArgumentException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception exception){
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}