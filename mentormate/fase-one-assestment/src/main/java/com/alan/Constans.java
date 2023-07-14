package com.alan;

public class Constans {
    public static final String MESSAGE_OK = "OK";
    public static final String MESSAGE_WRONG = "Bad Request";
    public static final String MESSAGE_BOOK_BORROW_EXPIRES = "Another Book that you Borrow Expire Day to return";
    public static final String MESSAGE_BOOK_NOT_DISPONIBLE = "There are no available copy";
    public static final String MESSAGE_BOOK_DONT_EXIST = "The book you requested dont exist";
    public static final String MESSAGE_BOOK_NOT_FOUNDED = "There are no book that meet this characteristics";
    public static final String MESSAGE_USER_NOT_ACCEPT_ERUGDPR = "The user have not accepted ERUGDPR";
    public static final String MESSAGE_USER_DONT_EXIST = "The user dont exist";
    public static final String MESSAGE_MAX_POSTPONE_EXCEEDED = "The book return cant be extended more than 14 days";
    public static final int CODE_OK = 200;
    public static final int CODE_WRONG = 400;
    public static final int DEFAULT_CUANTITY_DAYS_TO_RETURN_BOOK = 7;
    public static final int MAX_CUANTITY_DAYS_TO_RETURN_BOOK = 14;
}
