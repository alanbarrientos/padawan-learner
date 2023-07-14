package com.alan.repository;

import com.alan.entity.Tag;

import java.util.stream.Collectors;

public class TagRepository extends Repository<Tag>{
    private static final TagRepository instance = new TagRepository();

    private TagRepository() {
        Tag hardback = new Tag("Hardback");
        Tag afterJesus = new Tag("After Jesus");
        Tag detective = new Tag("Detective");
        Tag tag = new Tag("90's");
        entities.add(hardback);
        entities.add(afterJesus);
        entities.add(detective);
        entities.add(tag);
    }
    public static TagRepository getInstance(){
        return instance;
    }

    public Tag getByName(String name){
        return entities.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList()).get(0);
    }
}
