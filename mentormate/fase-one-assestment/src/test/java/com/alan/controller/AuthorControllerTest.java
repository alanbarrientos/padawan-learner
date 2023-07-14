package com.alan.controller;

import com.alan.entity.Author;
import com.alan.repository.AuthorRepository;
import com.alan.repository.CountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuthorControllerTest {
    Author authorDto;
    AuthorController authorController;
    CountryRepository countryRepository = CountryRepository.getInstance();
    AuthorRepository authorRepository = AuthorRepository.getInstance();
    @BeforeEach
    public void initializateAuthorController() throws ParseException {
        String dateBirthString = "20-12-1949";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateBirth  = formatter.parse(dateBirthString);
        authorDto = new Author(
                "Susy Delgado",
                countryRepository.getByName("Paraguay").orElseThrow(),
                dateBirth,
                null
        );
        authorController = new AuthorController();
    }
    @Test
    public void shouldCreateAuthor(){
        Author authorCreated = authorController.createAuthor(authorDto).getBody();
        Assertions.assertTrue(authorController.getAuthors().getBody().contains(authorCreated));
    }


    @Test
    public void shouldDeleteAuthor(){
        Author authorCreated = authorController.createAuthor(authorDto).getBody();
        Assertions.assertTrue(authorController.getAuthors().getBody().contains(authorCreated));
        authorController.deleteAuthor(authorCreated.getId());
        Assertions.assertFalse(authorController.getAuthors().getBody().contains(authorCreated));
    }
    @Test
    public void shouldReturnResponseUnsuccessfulTryingToDeleteThatDontExist(){
        Assertions.assertFalse(authorController.deleteAuthor(authorDto.getId()).isSuccessful());
    }


    @Test
    public void shouldModifyAuthor(){
        Author authorCreated = authorController.createAuthor(authorDto).getBody();
        Assertions.assertTrue(authorController.getAuthors().getBody().contains(authorCreated));
        authorCreated.setDateOfDeath(new Date());
        authorController.modifyAuthor(authorCreated.getId(), authorCreated);
        Assertions.assertEquals(
                authorCreated,
                authorRepository.getById(authorCreated.getId()).orElse(authorDto)
        );
    }
    @Test
    public void shouldReturnResponseUnsuccessfulTryingToModifyAuthorThatDontExist(){
        Author authorCreated = authorController.createAuthor(authorDto).getBody();
        Assertions.assertTrue(authorController.getAuthors().getBody().contains(authorCreated));
        authorCreated.setDateOfDeath(new Date());
        authorController.deleteAuthor(authorCreated.getId());
        Assertions.assertFalse(authorController.modifyAuthor(authorCreated.getId(), authorCreated).isSuccessful());

    }
}
