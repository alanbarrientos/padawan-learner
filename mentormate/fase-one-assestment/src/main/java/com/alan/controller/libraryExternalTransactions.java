package com.alan.controller;

import com.alan.entities.Book;
import com.alan.entities.User;
import com.alan.entities.History;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class libraryExternalTransactions {
    public void borrowPaperBook(List<History> histories, User user, Book book){
        Calendar dayToReturn = Calendar.getInstance();
        dayToReturn.setTime(new Date());
        dayToReturn.add(Calendar.DAY_OF_MONTH, 7);

        History history = new History(new Date(), dayToReturn.getTime(), null, user, book);
        histories.add(history);
    }

    public void registerEbook(List<History> histories, User user, Book book){
        History history = new History(new Date(),null, null, user, book);
        histories.add(history);
    }

    public void requestPostment(List<History> histories, History history, int days){
        Calendar dayToReturn = Calendar.getInstance();
        dayToReturn.setTime(history.getDateToReturn());
        dayToReturn.add(Calendar.DAY_OF_MONTH, days);
        if(daysBetween(history.getDateBorrow(), dayToReturn.getTime()) <= 14){
            histories.
        }
    }
    public void SearchBook(){

    }

    private static long daysBetween(Date one, Date two) {
        long difference = (one.getTime()-two.getTime())/86400000;
        return Math.abs(difference);
    }

}
