package com.mahesha.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahesha.user_service.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
    
}
