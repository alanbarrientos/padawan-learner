package com.example.springbootdieta.dao;

import com.example.springbootdieta.model.Role;
import com.example.springbootdieta.model.RoleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleList role);
}
