package com.example.springbootdieta.dao;

import com.example.springbootdieta.model.Weight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class WeightCustomRepositoryImpl implements WeightCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List findFirstByUser_UserNameOrderByDateDateAsc(String username) {
//        SELECT weight.weight
//        from jwt.weight
//        inner join user on weight.user_id=user.id
//        where user.name="alan"
//        order by weight.date asc
//        limit 1;
        return entityManager.createNativeQuery("SELECT weight.weight from weight inner join user on weight.user_id=user.id where user.name='"+ username.toLowerCase() + "' order by weight.date asc limit 1").getResultList();
    }

    @Override
    public List findFirstByUser_UserNameOrderByDateDateDesc(String username) {
        return entityManager.createNativeQuery("SELECT weight.weight from weight inner join user on weight.user_id=user.id where user.name='"+ username.toLowerCase() + "' order by weight.date desc limit 1").getResultList();

    }

//    @Override
//    public Integer getFirst() {
//
//
//        return first;
//    }
//
//    @Override
//    public Integer getLast() {
//        return null;
//    }

}
