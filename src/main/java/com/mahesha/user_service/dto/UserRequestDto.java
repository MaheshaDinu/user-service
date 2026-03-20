package com.mahesha.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String name;
    private String email;
    private String role; 
    private String password;
}