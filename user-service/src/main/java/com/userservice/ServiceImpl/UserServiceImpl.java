package com.userservice.ServiceImpl;

import com.userservice.Dto.UserDto;
import com.userservice.Exception.ResourceNotFoundException;
import com.userservice.Exception.UserAlreadyExistsException;
import com.userservice.Model.User;
import com.userservice.Repository.UserRepo;
import com.userservice.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepo.existsByEmail(userDto.getEmail())){
            throw new UserAlreadyExistsException("User","username",userDto.getEmail());
        }
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User userInfo = userRepo.save(user);
        return modelMapper.map(userInfo,UserDto.class);
    }

    @Override
    public UserDto findUserById(Integer id) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> modelMapper.map(user,UserDto.class)).toList();
        return userDtos;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
        userRepo.delete(user);
    }

    @Override
    public UserDto updateUser(Integer id, UserDto userDto) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAddress(userDto.getAddress());
        User updateUser = userRepo.save(user);
        return modelMapper.map(updateUser, UserDto.class);
    }

    @Override
    public UserDto findUserByName(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null){
            throw new IllegalArgumentException("User not found for username:" +username);
        }
         return modelMapper.map(user,UserDto.class);
    }
}
