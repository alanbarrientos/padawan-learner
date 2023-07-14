package com.alan.controller;

import com.alan.DateProvider;
import com.alan.entity.*;
import com.alan.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EbookActionControllerTest {
    @Mock
    DateProvider dateProvider;
    EbookActionController ebookActionController;
    BookRepository bookRepository = BookRepository.getInstance();
    UserRepository userRepository = UserRepository.getInstance();
    GenreRepository genreRepository = GenreRepository.getInstance();
    TagRepository tagRepository = TagRepository.getInstance();
    AuthorRepository authorRepository = AuthorRepository.getInstance();
    @BeforeEach
    public void initializeController(){
        ebookActionController =  new EbookActionController(dateProvider);
    }
    @Test
    public void shouldGetLinkToDownloadABook(){
        when(dateProvider.now()).thenReturn(new Date());
        String linkDownload = ebookActionController.getLinkToDownloadEbook(
                                    bookRepository.getByTitle("The Da Vinci Code").getId(),
                                    userRepository.getByName("Alan").getId()
                            ).getBody();
        Assertions.assertEquals("https://www.amazon.com/Vinci-Code-Robert-Langdon/dp/0307474275", linkDownload);
    }
    @Test
    public void shouldFailToGetLinkToDownloadABookThatDontExist(){
        List<Tag> tags = new ArrayList<>();
        tags.add(tagRepository.getByName("90's"));
        Book bookThatDontExist = new Book(
                "Lost Laysen",
                authorRepository.getByName("Margaret Mitchell"),
                genreRepository.getByName("Romance"),
                "summary",
                765432456,
                tags,
                null,
                null,
                4
        );
        boolean isSuccessful = ebookActionController.getLinkToDownloadEbook(
                bookThatDontExist.getId(),
                userRepository.getByName("Alan").getId()
        ).isSuccessful();
        Assertions.assertFalse(isSuccessful);
    }

    @Test
    public void shouldFailToGetLinkToDownloadABookWithUserThatDontExist(){
        User someone = new User("Someone", 22, Gender.MALE,
                new ArrayList<Address>(),new ArrayList<String>(), true);
        boolean isSuccessful = ebookActionController.getLinkToDownloadEbook(
                bookRepository.getByTitle("The Da Vinci Code").getId(),
                someone.getId()
        ).isSuccessful();
        Assertions.assertFalse(isSuccessful);
    }
    @Test
    public void shouldGetOnlineReadLinkOfABook(){
        when(dateProvider.now()).thenReturn(new Date());
        String linkReadOnline = ebookActionController.getLinkToReadOnline(
                bookRepository.getByTitle("The Da Vinci Code").getId(),
                userRepository.getByName("Alan").getId()
        ).getBody();
        Assertions.assertEquals("https://davincicode.bib.bz/", linkReadOnline);
    }
    @Test
    public void shouldFailToGetOnlineReadLinkABookThatDontExist(){
        List<Tag> tags = new ArrayList<>();
        tags.add(tagRepository.getByName("90's"));
        Book bookThatDontExist = new Book(
                "Lost Laysen",
                authorRepository.getByName("Margaret Mitchell"),
                genreRepository.getByName("Romance"),
                "summary",
                765432456,
                tags,
                null,
                null,
                4
        );
        boolean isSuccessful = ebookActionController.getLinkToReadOnline(
                bookThatDontExist.getId(),
                userRepository.getByName("Alan").getId()
        ).isSuccessful();
        Assertions.assertFalse(isSuccessful);
    }

    @Test
    public void shouldFailToGetOnlineReadLinkABookWithUserThatDontExist(){
        User someone = new User("Someone", 22, Gender.MALE,
                new ArrayList<Address>(),new ArrayList<String>(), true);
        boolean isSuccessful = ebookActionController.getLinkToReadOnline(
                bookRepository.getByTitle("The Da Vinci Code").getId(),
                someone.getId()
        ).isSuccessful();
        Assertions.assertFalse(isSuccessful);
    }
}
