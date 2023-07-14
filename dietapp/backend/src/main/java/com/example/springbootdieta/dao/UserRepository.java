package com.example.springbootdieta.dao;

import com.example.springbootdieta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUserName(String userName);
    public boolean existsByEmail(String email);
    public boolean existsByUserName(String userName);
    public User getUserByUserName(String username);
    public User getUserByEmail(String email);
    Optional<User> findByEmail(String email);
}
