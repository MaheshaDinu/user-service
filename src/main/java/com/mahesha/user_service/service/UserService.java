package com.mahesha.user_service.service;

import java.util.List;

import com.mahesha.user_service.dto.UserRequestDto;
import com.mahesha.user_service.dto.UserResponseDto;

public interface UserService {
    
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long id);
    List<UserResponseDto> getAllUsers();
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
    boolean deleteUser(Long id);
}
