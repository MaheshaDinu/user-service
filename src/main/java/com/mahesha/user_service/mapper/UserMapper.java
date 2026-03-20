package com.mahesha.user_service.mapper;

import org.mapstruct.Mapper;
import com.mahesha.user_service.dto.UserRequestDto;
import com.mahesha.user_service.entity.User;
import com.mahesha.user_service.dto.UserResponseDto;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper{

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", source = "password")
    User toEntity(UserRequestDto userRequestDto);

    UserResponseDto toDto(User user);
}