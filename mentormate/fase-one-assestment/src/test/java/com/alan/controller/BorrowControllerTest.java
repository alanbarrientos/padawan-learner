package com.alan.controller;

import com.alan.DateProvider;
import com.alan.entity.*;
import com.alan.repository.AuthorRepository;
import com.alan.repository.BookRepository;
import com.alan.repository.GenreRepository;
import com.alan.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BorrowControllerTest {
    @Mock
    DateProvider dateProvider;
    BorrowController borrowController;
    BookRepository bookRepository = BookRepository.getInstance();
    UserRepository userRepository = UserRepository.getInstance();
    AuthorRepository authorRepository = AuthorRepository.getInstance();
    GenreRepository genreRepository = GenreRepository.getInstance();
    @BeforeEach
    public void initializeController(){
        borrowController =  new BorrowController(dateProvider);
    }
    @Test
    public void shouldBorrowABook() throws ParseException {
        String dateBorrowedString = "01-01-2023";
        String dateToRetundString = "08-01-2023";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateBorrowed = formatter.parse(dateBorrowedString);
        Date dateToReturn = formatter.parse(dateToRetundString);

        when(dateProvider.now()).thenReturn(dateBorrowed);
        History history = borrowController.borrowPaperBook(
                userRepository.getByName("Alan").getId(),
                bookRepository.getByTitle("Gone With The Wind").getId()
        ).getBody();
        
        Assertions.assertEquals(dateBorrowed, history.getDateBorrow());
        Assertions.assertEquals(dateToReturn, history.getDateToReturn());
        Assertions.assertNull(history.getDateReturned());
        Assertions.assertEquals(userRepository.getByName("Alan"), history.getUser());
        Assertions.assertEquals(bookRepository.getByTitle("Gone With The Wind"), history.getBook());
        borrowController.returnBook(history.getId());
    }
    @Test
    public void shouldFailsTryingToBorrowMoreBooksThanCuantityOfBooks() {
        final int moreCuantityThanDisponibleOfBooks = 6;
        when(dateProvider.now()).thenReturn(new Date());
        boolean isSuccessful = true;
        for(int i=0; i<moreCuantityThanDisponibleOfBooks; i++){
            isSuccessful = borrowController.borrowPaperBook(
                    userRepository.getByName("Alan").getId(),
                    bookRepository.getByTitle("Gone With The Wind").getId()
            ).isSuccessful();
        }

        Assertions.assertFalse(isSuccessful);
        List<History> histories = borrowController.getHistoryNotReturnedByUser(userRepository.getByName("Alan")).getBody();
        for (History h:histories) {
            borrowController.returnBook(h.getId());
        }
    }

    @Test
    public void shouldFailsWhenBorrowABookWithBorrowExpire() throws ParseException {
        String dateBorrowedString = "01-01-2023";
        String dateBorrowAnotherBookButExpiresBorrowBeforeString = "20-01-2023";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateBorrowed = formatter.parse(dateBorrowedString);
        Date dateBorrowAnotherBookButExpiresBorrowBefore = formatter.parse(dateBorrowAnotherBookButExpiresBorrowBeforeString);
        when(dateProvider.now()).thenReturn(dateBorrowed,dateBorrowAnotherBookButExpiresBorrowBefore);
        History history = borrowController.borrowPaperBook(
                userRepository.getByName("Alan").getId(),
                bookRepository.getByTitle("Gone With The Wind").getId()
        ).getBody();

        borrowController.postponeReturnOfBook(history.getId(), 7);

        boolean isSuccessful = borrowController.borrowPaperBook(
                userRepository.getByName("Alan").getId(),
                bookRepository.getByTitle("The Da Vinci Code").getId()
        ).isSuccessful();
        Assertions.assertFalse(isSuccessful);
        borrowController.returnBook(history.getId());
    }
    @Test
    public void shouldFailsWhenBorrowABookWithUserThatDontExist(){
        Book bookDontExist = new Book("Something", authorRepository.getByName("Margaret Mitchell"),
                genreRepository.getByName("Romance"),
                "summary",
                26637378,
                new ArrayList<Tag>(),
                null,
                null,
                5
        );
        boolean isSuccessful = borrowController.borrowPaperBook(
                userRepository.getByName("Alan").getId(),
                bookDontExist.getId()
        ).isSuccessful();
        Assertions.assertFalse(isSuccessful);
    }
    @Test
    public void shouldFailsWhenBorrowABookThatDontExist() {
        User someone = new User("Someone", 22, Gender.MALE,
                new ArrayList<Address>(),new ArrayList<String>(), true);
        boolean isSuccessful = borrowController.borrowPaperBook(
                someone.getId(),
                bookRepository.getByTitle("Gone With The Wind").getId()
        ).isSuccessful();
        Assertions.assertFalse(isSuccessful);
    }
    @Test
    public void shouldPostponeReturnOfBook() throws ParseException {
        String dateBorrowedString = "01-01-2023";
        String dateToRetundString = "10-01-2023";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateBorrowed = formatter.parse(dateBorrowedString);
        Date dateToReturn = formatter.parse(dateToRetundString);
        when(dateProvider.now()).thenReturn(dateBorrowed);
        History history = borrowController.borrowPaperBook(
                userRepository.getByName("Alan").getId(),
                bookRepository.getByTitle("Gone With The Wind").getId()
        ).getBody();

        History historyPostponed = borrowController.postponeReturnOfBook(history.getId(),2).getBody();

        Assertions.assertEquals(dateBorrowed, historyPostponed.getDateBorrow());
        Assertions.assertEquals(dateToReturn, historyPostponed.getDateToReturn());
        Assertions.assertNull(historyPostponed.getDateReturned());
        Assertions.assertEquals(userRepository.getByName("Alan"), historyPostponed.getUser());
        Assertions.assertEquals(bookRepository.getByTitle("Gone With The Wind"), historyPostponed.getBook());
        borrowController.returnBook(history.getId());
    }
    @Test
    public void shouldFailPostponeReturnOfBookForExceed() throws ParseException {
        String dateBorrowedString = "01-01-2023";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateBorrowed = formatter.parse(dateBorrowedString);
        when(dateProvider.now()).thenReturn(dateBorrowed);
        History history = borrowController.borrowPaperBook(
                userRepository.getByName("Alan").getId(),
                bookRepository.getByTitle("Gone With The Wind").getId()
        ).getBody();

        boolean isSuccessful = borrowController.postponeReturnOfBook(history.getId(),9).isSuccessful();
        Assertions.assertFalse(isSuccessful);
        borrowController.returnBook(history.getId());
    }

    @Test
    public void shouldReturnBook() throws ParseException {
        String dateBorrowedString = "01-01-2023";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateBorrowed = formatter.parse(dateBorrowedString);
        Date dateReturned = new Date();
        when(dateProvider.now()).thenReturn(dateBorrowed, dateBorrowed, dateReturned);
        History history = borrowController.borrowPaperBook(
                userRepository.getByName("Alan").getId(),
                bookRepository.getByTitle("Gone With The Wind").getId()
        ).getBody();
        Assertions.assertTrue(borrowController.returnBook(history.getId()).isSuccessful());
    }
}
