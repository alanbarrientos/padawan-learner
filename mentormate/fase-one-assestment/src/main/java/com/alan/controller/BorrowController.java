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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BorrowController {
    private DateProvider dateProvider;
    public BorrowController(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    private HistoryRepository historyRepository = HistoryRepository.getInstance();
    private BookRepository bookRepository = BookRepository.getInstance();
    private UserRepository userRepository = UserRepository.getInstance();

    public Response<History> borrowPaperBook(int idUser, int idBook){
        Optional<Book> book = bookRepository.getById(idBook);
        Optional<User> user = userRepository.getById(idUser);
        if(book.isEmpty() || !bookRepository.have(book.get())){
            return new Response(false, Constans.MESSAGE_BOOK_DONT_EXIST, Constans.CODE_WRONG);
        }
        if(user.isEmpty() || !userRepository.have(user.get())){
            return new Response(false, Constans.MESSAGE_USER_DONT_EXIST, Constans.CODE_WRONG);
        }
        if(book.get().getBorrowed() == book.get().getCuantityCopies()){
            return new Response(false, Constans.MESSAGE_BOOK_NOT_DISPONIBLE, Constans.CODE_WRONG);
        }
        List<History> histories = historyRepository.getHistoryByUserWhereIsNotReturned(user.get());
        if(histories.size()>0){
            for (History history:histories) {
                if(daysBetween(history.getDateBorrow(), history.getDateToReturn())<0){
                    return new Response(false, Constans.MESSAGE_BOOK_BORROW_EXPIRES, Constans.CODE_WRONG);
                }
            }
        }
        bookRepository.proccessBorrow(book.get());
        History history = new History(dateProvider.now(), getDateToReturn(), null, user.get(), book.get());
        historyRepository.add(history);
        return new Response(true, Constans.MESSAGE_OK, Constans.CODE_OK, history);
    }


    public Response<History> postponeReturnOfBook(int id, int days){
        History history = historyRepository.getById(id).get();
        Date newDateToReturn = getDateToReturn(history.getDateToReturn(), days);
        long actualPlaceBetweenBorrowDateAndDateToReturn = daysBetween(history.getDateBorrow(), newDateToReturn);
        if(actualPlaceBetweenBorrowDateAndDateToReturn > Constans.MAX_CUANTITY_DAYS_TO_RETURN_BOOK){
            return new Response(false, Constans.MESSAGE_MAX_POSTPONE_EXCEEDED, Constans.CODE_WRONG);
        }
        try{
            history.setDateToReturn(newDateToReturn);
            historyRepository.modifyById(history.getId(), history);
            return new Response(true, Constans.MESSAGE_OK, Constans.CODE_OK, history);
        }catch (Exception e){
            return new Response(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response returnBook(int id){
        try{
            History history = historyRepository.getById(id).orElseThrow(RuntimeException::new);
            bookRepository.proccessReturn(history.getBook());
            history.setDateReturned(dateProvider.now());
            historyRepository.modifyById(history.getId(), history);
            return new Response(true, Constans.MESSAGE_OK, Constans.CODE_OK, history);
        }catch (Exception e){
            return new Response(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response<List<History>> getHistoryNotReturnedByUser(User user){
        return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK, historyRepository.getHistoryByUserWhereIsNotReturned(user));
    }


    private long daysBetween(Date one, Date two) {
        return Math.abs(one.getTime()-two.getTime())/86400000;
    }
    private Date getDateToReturn() {
        Calendar dayToReturn = Calendar.getInstance();
        dayToReturn.setTime(dateProvider.now());
        dayToReturn.add(Calendar.DAY_OF_MONTH, Constans.DEFAULT_CUANTITY_DAYS_TO_RETURN_BOOK);
        return dayToReturn.getTime();
    }

    private Date getDateToReturn(Date date, int days){
        Calendar dayToReturn = Calendar.getInstance();
        dayToReturn.setTime(date);
        dayToReturn.add(Calendar.DAY_OF_MONTH, days);
        return dayToReturn.getTime();
    }

}
