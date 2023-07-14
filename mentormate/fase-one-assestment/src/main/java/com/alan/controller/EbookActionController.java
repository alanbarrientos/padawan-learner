package com.alan.controller;

import com.alan.Constans;
import com.alan.DateProvider;
import com.alan.Response;
import com.alan.entity.Book;
import com.alan.entity.History;
import com.alan.entity.User;
import com.alan.repository.BookRepository;
import com.alan.repository.HistoryRepository;
import com.alan.repository.UserRepository;

import java.util.Optional;

public class EbookActionController {

    private DateProvider dateProvider;
    private HistoryRepository historyRepository = HistoryRepository.getInstance();
    private UserRepository userRepository = UserRepository.getInstance();
    private BookRepository bookRepository = BookRepository.getInstance();


    public EbookActionController(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }
    public Response<String> getLinkToDownloadEbook(int idBook, int idUser){
        Optional<Book> book = bookRepository.getById(idBook);
        Optional<User> user = userRepository.getById(idUser);
        if(book.isEmpty() || !bookRepository.have(book.get()) || book.get().getDownloadLink() == null){
            return new Response<> (false, Constans.MESSAGE_BOOK_DONT_EXIST, Constans.CODE_WRONG);
        }
        if(user.isEmpty() || !userRepository.have(user.get())){
            return new Response<> (false, Constans.MESSAGE_USER_DONT_EXIST, Constans.CODE_WRONG);
        }
            History history = new History(dateProvider.now(), null, null, user.get(), book.get());
            historyRepository.add(history);
            return new Response<> (true, Constans.MESSAGE_OK, Constans.CODE_OK, book.get().getDownloadLink());
    }
    public Response<String> getLinkToReadOnline(int idBook, int idUser){
        Optional<Book> book = bookRepository.getById(idBook);
        Optional<User> user = userRepository.getById(idUser);
        if(book.isEmpty() || !bookRepository.have(book.get()) || book.get().getReadLink() == null){
            return new Response<> (false, Constans.MESSAGE_BOOK_DONT_EXIST, Constans.CODE_WRONG);
        }
        if(user.isEmpty() || !userRepository.have(user.get())){
            return new Response<> (false, Constans.MESSAGE_USER_DONT_EXIST, Constans.CODE_WRONG);
        }
            History history = new History(dateProvider.now(), null, null, user.get(), book.get());
            historyRepository.add(history);
            return new Response<> (true, Constans.MESSAGE_OK, Constans.CODE_OK, book.get().getReadLink());
    }

}
