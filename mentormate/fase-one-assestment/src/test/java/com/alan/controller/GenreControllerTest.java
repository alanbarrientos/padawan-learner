package com.alan.controller;

import com.alan.entity.Genre;
import com.alan.repository.GenreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenreControllerTest {
    Genre genreDto;
    GenreController genreController;
    GenreRepository genreRepository = GenreRepository.getInstance();
    @BeforeEach
    public void initializateGenreController(){
        genreDto = new Genre("Action");
        genreController = new GenreController();
    }
    @Test
    public void shouldCreateGenre(){
        Genre genreCreated = genreController.createGenre(genreDto).getBody();
        Assertions.assertTrue(genreController.getGenres().getBody().contains(genreCreated));
    }


    @Test
    public void shouldDeleteGenre(){
        Genre genreCreated = genreController.createGenre(genreDto).getBody();
        Assertions.assertTrue(genreController.getGenres().getBody().contains(genreCreated));
        genreController.deleteGenre(genreCreated);
        Assertions.assertFalse(genreController.getGenres().getBody().contains(genreCreated));
    }
    @Test
    public void shouldReturnResponseUnsuccessfulTryingToDeleteThatDontExist(){
        Assertions.assertFalse(genreController.deleteGenre(genreDto).isSuccessful());
    }


    @Test
    public void shouldModifyGenre(){
        Genre genreCreated = genreController.createGenre(genreDto).getBody();
        Assertions.assertTrue(genreController.getGenres().getBody().contains(genreCreated));
        genreCreated.setName("Monkeys");
        genreController.modifyGenre(genreCreated.getId(), genreCreated);
        Assertions.assertEquals("Monkeys",
                genreRepository.getById(genreCreated.getId()).orElse( new Genre("Not Found")).getName()
        );
    }
    @Test
    public void shouldReturnResponseUnsuccessfulTryingToModifyGenreThatDontExist(){
        Genre genreCreated = genreController.createGenre(genreDto).getBody();
        Assertions.assertTrue(genreController.getGenres().getBody().contains(genreCreated));
        genreCreated.setName("Monkeys");
        genreController.deleteGenre(genreCreated);
        Assertions.assertFalse(genreController.modifyGenre(genreCreated.getId(), genreCreated).isSuccessful());
    }
}
