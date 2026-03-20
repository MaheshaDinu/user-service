package com.mahesha.user_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mahesha.user_service.dto.UserRequestDto;
import com.mahesha.user_service.dto.UserResponseDto;
import com.mahesha.user_service.entity.User;
import com.mahesha.user_service.mapper.UserMapper;
import com.mahesha.user_service.repository.UserRepo;
import com.mahesha.user_service.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        User user = userMapper.toEntity(userRequestDto);
        user.setPasswordHash(encodedPassword);
        User savedUser = userRepo.save(user);
        return userMapper.toDto(savedUser);

    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepo.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        existingUser.setName(userRequestDto.getName());
        existingUser.setEmail(userRequestDto.getEmail());
        existingUser.setRole(userRequestDto.getRole());
        existingUser.setPasswordHash(passwordEncoder.encode(userRequestDto.getPassword()));

        User updatedUser = userRepo.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public boolean deleteUser(Long id) {
        boolean isDeleted = userRepo.existsById(id);
        userRepo.deleteById(id);
        return isDeleted;
    }

}
