package com.example.springbootdieta.dao;

import com.example.springbootdieta.model.Weight;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.Query;
import java.util.List;

public interface WeightCustomRepository {
    List findFirstByUser_UserNameOrderByDateDateAsc(String username);
    List findFirstByUser_UserNameOrderByDateDateDesc(String username);
}
