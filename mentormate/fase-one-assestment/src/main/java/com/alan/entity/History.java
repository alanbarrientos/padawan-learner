package com.alan.entity;

import java.util.Date;

public class History extends Entity {
    private Date dateBorrow;
    private Date dateToReturn;
    private Date dateReturned;
    private User user;
    private Book book;

    // todo hdavid falta historial de solicitudes de extension

    public History(Date dateBorrow, Date dateToReturn, Date dateReturned, User user, Book book) {
        super();
        this.dateBorrow = dateBorrow;
        this.dateToReturn = dateToReturn;
        this.dateReturned = dateReturned;
        this.user = user;
        this.book = book;
    }

    public Date getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(Date dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public Date getDateToReturn() {
        return dateToReturn;
    }

    public void setDateToReturn(Date dateToReturn) {
        this.dateToReturn = dateToReturn;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
