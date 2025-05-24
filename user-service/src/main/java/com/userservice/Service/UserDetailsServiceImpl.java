package com.userservice.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.userservice.Model.User;
import com.userservice.Model.UserDetailsImpl;
import com.userservice.Repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepo.findUserByEmail(username);
            if (user == null){
                throw new UsernameNotFoundException("User not found with name:" +username);
            }
            return new UserDetailsImpl(user);
        } catch (Exception exception){
            throw new UsernameNotFoundException("Failed to receive user details for username: {}" +username, exception);
        }
    }
}
