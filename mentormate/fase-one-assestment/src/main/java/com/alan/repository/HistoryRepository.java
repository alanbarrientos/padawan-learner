package com.alan.repository;

import com.alan.entity.History;
import com.alan.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryRepository extends Repository<History>{
    private static final HistoryRepository instance = new HistoryRepository();
    public static HistoryRepository getInstance(){
        return instance;
    }
    private HistoryRepository() {
    }
    public List<History> getHistoryByUserWhereIsNotReturned(User user){
         return this.entities.stream()
                 .filter(h -> h.getDateToReturn() != null && h.getDateReturned() == null).collect(Collectors.toList());
    }

}
