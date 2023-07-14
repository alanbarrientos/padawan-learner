package com.alan.controller;

import com.alan.Constans;
import com.alan.Response;
import com.alan.entity.Author;
import com.alan.repository.AuthorRepository;
import com.alan.repository.CountryRepository;

import java.util.List;

public class AuthorController {
    private AuthorRepository authorRepository = AuthorRepository.getInstance();
    private CountryRepository countryRepository = CountryRepository.getInstance();
    public Response<Author> createAuthor(Author author){
        try{
            Author authorToSave = new Author(author.getName(),
                    countryRepository.getById(author.getCountry().getId()).orElseThrow(RuntimeException::new),
                    author.getDateOfBirth(), author.getDateOfDeath());
            authorRepository.add(authorToSave);
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK, authorToSave);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response modifyAuthor(int id, Author author){
        try{
            authorRepository.modifyById(id, author);
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response deleteAuthor(int id){
        try{
            authorRepository.deleteById(id);
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }
    public Response<List<Author>> getAuthors(){
        return new Response<>(true ,Constans.MESSAGE_OK, Constans.CODE_OK, authorRepository.getEntities());
    }
}
