package com.alan.controller;

import com.alan.entity.Book;
import com.alan.entity.Tag;
import com.alan.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BookControllerTest {
    Book bookDto;
    BookController bookController;
    BookRepository bookRepository = BookRepository.getInstance();
    AuthorRepository authorRepository = AuthorRepository.getInstance();
    GenreRepository genreRepository = GenreRepository.getInstance();
    TagRepository tagRepository = TagRepository.getInstance();
    @BeforeEach
    public void initializateBookController(){
        String summary = "Written by Mitchell in 1916 when she was" +
                " just shy of her 16th birthday, Lost Laysen is a story" +
                " of a spirited young woman determined to be \"a missionary\"" +
                " to the people on the island of Laysen and the two men who loved her.";
        List<Tag> tags = new ArrayList<>();
        tags.add(tagRepository.getByName("90's"));
        bookDto = new Book(
                "Lost Laysen",
                authorRepository.getByName("Margaret Mitchell"),
                genreRepository.getByName("Romance"),
                summary,
                765432456,
                tags,
                null,
                null,
                4
        );
        bookController = new BookController();
    }
    @Test
    public void shouldCreateBook(){
        Book bookCreated = bookController.createBook(bookDto).getBody();
        Assertions.assertTrue(bookController.getBooks().getBody().contains(bookCreated));
    }


    @Test
    public void shouldDeleteBook(){
        Book bookCreated = bookController.createBook(bookDto).getBody();
        Assertions.assertTrue(bookController.getBooks().getBody().contains(bookCreated));
        bookController.deleteBook(bookCreated.getId());
        Assertions.assertFalse(bookController.getBooks().getBody().contains(bookCreated));
    }
    @Test
    public void shouldReturnResponseUnsuccessfulTryingToDeleteThatDontExist(){
        Assertions.assertFalse(bookController.deleteBook(bookDto.getId()).isSuccessful());
    }


    @Test
    public void shouldModifyBook(){
        Book bookCreated = bookController.createBook(bookDto).getBody();
        Assertions.assertTrue(bookController.getBooks().getBody().contains(bookCreated));
        bookCreated.setReadLink("SomeLink");
        bookController.modifyBook(bookCreated.getId(), bookCreated);
        Assertions.assertEquals(
                bookCreated,
                bookRepository.getById(bookCreated.getId()).orElse(bookDto)
        );
    }
    @Test
    public void shouldReturnResponseUnsuccessfulTryingToModifyBookThatDontExist(){
        Book bookCreated = bookController.createBook(bookDto).getBody();
        Assertions.assertTrue(bookController.getBooks().getBody().contains(bookCreated));
        bookCreated.setReadLink("SomeLink");
        bookController.deleteBook(bookCreated.getId());
        Assertions.assertFalse(bookController.modifyBook(bookCreated.getId(), bookCreated).isSuccessful());
    }

    @Test
    public void shouldGetBookByTitle(){
        List<Book> books = bookController.searchBookByTitle("Gone With The Wind").getBody();
        Assertions.assertEquals(1, books.size());
        Assertions.assertTrue(books.contains(bookRepository.getByTitle("Gone With The Wind")));
    }
    @Test
    public void shouldGetBookByGenre(){
        List<Book> books = bookController.searchBookByGenre(genreRepository.getByName("Romance")).getBody();
        Assertions.assertEquals(1, books.size());
        Assertions.assertTrue(books.contains(bookRepository.getByTitle("Gone With The Wind")));
    }
    @Test
    public void shouldGetBookByTag(){
        List<Tag> tags = new ArrayList<>();
        tags.add(tagRepository.getByName("Detective"));
        tags.add(tagRepository.getByName("After Jesus"));
        List<Book> books = bookController.searchBookByTags(tags).getBody();
        Assertions.assertEquals(1, books.size());
        Assertions.assertTrue(books.contains(bookRepository.getByTitle("The Da Vinci Code")));
    }
    @Test
    public void shouldGetBookByAuthorNameOrLastName(){
        List<Book> books = bookController.searchBookByAuthorsNameOrLastName("Dan").getBody();
        Assertions.assertEquals(1, books.size());
        Assertions.assertTrue(books.contains(bookRepository.getByTitle("The Da Vinci Code")));
        List<Book> books2 = bookController.searchBookByAuthorsNameOrLastName("Brown").getBody();
        Assertions.assertEquals(1, books2.size());
        Assertions.assertTrue(books2.contains(bookRepository.getByTitle("The Da Vinci Code")));
    }
}
