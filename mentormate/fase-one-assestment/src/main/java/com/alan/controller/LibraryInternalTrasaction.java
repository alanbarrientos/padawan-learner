package com.alan.controller;

import com.alan.entities.*;

import java.util.Date;
import java.util.List;

public class LibraryInternalTrasaction {
    public void addPaperBook(List<Book> books, String title, Author author,
                             Genre genre, String summary, int codeISBN,
                             List<Tag> tags, int copies){
        Book book = new Book(title, author, genre, summary, codeISBN, tags, null, null, copies);
        books.add(book);
    }

    public void addEbook(List<Book> books, String title, Author author,
                         Genre genre, String summary, int codeISBN,
                         List<Tag> tags, String readLink, String downloadLink){
        Book book = new Book(title, author, genre, summary, codeISBN, tags, readLink, downloadLink, 0);
        books.add(book);
    }

    public void addAuthor(List<Author> authors, String name, Country country, Date dateOfBirth, Date dateOfDeath){
        Author author = new Author(name, country, dateOfBirth, dateOfDeath);
        authors.add(author);
    }
}
