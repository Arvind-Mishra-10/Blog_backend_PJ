package com.example.pj.pj.repository;

import com.example.pj.pj.entity.User;
import com.example.pj.pj.payload.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameOrEmail(String username, String email);


    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}