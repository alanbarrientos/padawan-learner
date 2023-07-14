package com.example.springbootdieta.dao;

import com.example.springbootdieta.model.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Integer>, WeightCustomRepository  {
  List<Weight> findAllByUser_UserNameOrderByDateDesc(String username);


}
