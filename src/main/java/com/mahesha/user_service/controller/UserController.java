package com.mahesha.user_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahesha.user_service.dto.UserRequestDto;
import com.mahesha.user_service.dto.UserResponseDto;
import com.mahesha.user_service.service.UserService;

import lombok.RequiredArgsConstructor;






@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser( @RequestBody UserRequestDto entity) {
        return new ResponseEntity<>(userService.createUser(entity),HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto entity) {
        
        return new ResponseEntity<>(userService.updateUser(id, entity), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
        
        
}
