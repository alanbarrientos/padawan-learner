package com.alan.controller;

import com.alan.Constans;
import com.alan.Response;
import com.alan.entity.*;
import com.alan.repository.*;

import java.util.List;

public class BookController {
    private AuthorRepository authorRepository = AuthorRepository.getInstance();
    private GenreRepository genreRepository = GenreRepository.getInstance();
    private TagRepository tagRepository = TagRepository.getInstance();
    private BookRepository bookRepository = BookRepository.getInstance();
    public Response<Book> createBook(Book book){
        for (Tag t: book.getTags()) {
            if(!tagRepository.have(t)){
                return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
            }
        }
        Book bookToSave = new Book(book.getTitle(),
                authorRepository.getById(book.getAuthor().getId()).orElseThrow(RuntimeException::new),
                genreRepository.getById(book.getGenre().getId()).orElseThrow(RuntimeException::new),
                book.getSummary(), book.getCodeISBN(), book.getTags(), book.getReadLink(),
                book.getDownloadLink(), book.getCuantityCopies());
        bookRepository.add(bookToSave);
        return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK, bookToSave);
    }

    public Response modifyBook(int id, Book book){
        try{
            bookRepository.modifyById(id, book);
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response deleteBook(int id){
        try{
            bookRepository.deleteById(id);
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response<List<Book>> getBooks(){
        return new Response<>(true ,Constans.MESSAGE_OK, Constans.CODE_OK, bookRepository.getEntities());
    }

    public Response<List<Book>> searchBookByTitle(String title){
        List<Book> books = bookRepository.searchBookByTitle(title);
        return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK, books);
    }
    public Response<List<Book>> searchBookByGenre(Genre genre){
        List<Book> books = bookRepository.searchBookByGenre(genre);
        return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK, books);
    }
    public Response<List<Book>> searchBookByTags(List<Tag> tags){
        List<Book> books = bookRepository.searchBookByTags(tags);
        return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK, books);
    }
    public Response<List<Book>> searchBookByAuthorsNameOrLastName(String name){
        List<Book> books = bookRepository.searchBookByAuthorsNameOrLastName(name);
        return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK, books);
    }
}
