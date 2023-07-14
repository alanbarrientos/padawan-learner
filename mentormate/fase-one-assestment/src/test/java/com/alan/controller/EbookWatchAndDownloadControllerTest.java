package com.alan.controller;

import com.alan.controller.util.DateProvider;
import com.alan.repository.BookRepository;
import com.alan.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EbookWatchAndDownloadControllerTest {
    @Mock
    DateProvider dateProvider;
    EbookWatchAndDownloadController ewadc;
    BookRepository bookRepository = BookRepository.getInstance();
    UserRepository userRepository = UserRepository.getInstance();
    @BeforeEach
    public void initializeController(){
        ewadc =  new EbookWatchAndDownloadController(dateProvider);
    }
    @Test
    public void shouldGetLinkToDownloadABook(){
        when(dateProvider.now()).thenReturn(new Date());
        String linkDownload = ewadc.getLinkToDownloadEbook(
                                    bookRepository.getByTitle("The Da Vinci Code"),
                                    userRepository.getByName("Alan")
                            ).getBody();
        Assertions.assertEquals("https://www.amazon.com/Vinci-Code-Robert-Langdon/dp/0307474275", linkDownload);
    }
    @Test
    public void shouldGetOnlineReadLinkOfABook(){
        when(dateProvider.now()).thenReturn(new Date());
        String linkReadOnline = ewadc.getLinkToReadOnline(
                bookRepository.getByTitle("The Da Vinci Code"),
                userRepository.getByName("Alan")
        ).getBody();
        Assertions.assertEquals("https://davincicode.bib.bz/", linkReadOnline);
    }
}
