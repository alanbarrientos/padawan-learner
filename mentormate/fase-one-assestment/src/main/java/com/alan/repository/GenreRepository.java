package com.alan.repository;

import com.alan.entity.Genre;

import java.util.stream.Collectors;

public class GenreRepository extends Repository<Genre>{
    private static final GenreRepository instance = new GenreRepository();

    private GenreRepository() {
        Genre romance = new Genre("Romance");
        Genre mistery = new Genre("Mistery");
        entities.add(romance);
        entities.add(mistery);
    }
    public static GenreRepository getInstance(){
        return instance;
    }

    public Genre getByName(String name) {
        return entities.stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList()).get(0);
    }
}
