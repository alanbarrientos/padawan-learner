package com.alan.controller;

import com.alan.Constans;
import com.alan.Response;
import com.alan.entity.Genre;
import com.alan.repository.GenreRepository;

import java.util.List;

public class GenreController {
    private GenreRepository genreRepository = GenreRepository.getInstance();
    public Response<Genre> createGenre(Genre genre){
        try{
            Genre genreToSave = new Genre(genre.getName());
            genreRepository.add(genreToSave);
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK, genreToSave);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response modifyGenre(int id, Genre genre){
        try{
            genreRepository.modifyById(id, genre);
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response deleteGenre(Genre genre){
        try{
            genreRepository.deleteById(genre.getId());
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }
    public Response<List<Genre>> getGenres(){
        return new Response<>(true ,Constans.MESSAGE_OK, Constans.CODE_OK, genreRepository.getEntities());
    }
}
