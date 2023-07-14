package com.alan.repository;

import com.alan.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class  Repository<T extends Entity> {
    List<T> entities = new ArrayList<>();
    public void add(T entity) {
        if (entities.contains(entity)){
            throw new RuntimeException("");
        }
        entities.add(entity);
    }

    public void deleteById(int id){
        Optional<T> obj = getById(id);
        if (obj.isEmpty()){
            throw new RuntimeException("");
        }
        entities.remove(obj.get());
        if (entities.contains(obj.get())){
            throw new RuntimeException("");
        }
    }
    public boolean have(T obj){
        return entities.contains(obj);
    }
    public void modifyById(int id, T obj){
        Optional<T> objToReplace = getById(id);
        if (objToReplace.isEmpty()){
            throw new RuntimeException("");
        }
        entities.remove(obj);
        entities.add(obj);
    };
    public Optional<T> getById(int id){
        return entities.stream().filter(e -> e.getId() == id).findFirst();
    }

    public List<T> getEntities(){
        List<T> entitiesToReturn = new ArrayList<>(entities);
        return entitiesToReturn;
    }
}
