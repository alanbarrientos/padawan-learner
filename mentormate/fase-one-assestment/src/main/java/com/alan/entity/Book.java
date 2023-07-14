package com.alan.entity;

import java.util.List;

public class Book extends Entity {
    private String title;
    private Author author;
    private Genre genre;
    private String summary;
    private int codeISBN;
    private List<Tag> tags;
    private String readLink;
    private String downloadLink;
    private int cuantityCopies;
    private int borrowed = 0;
    private int disponibleCopies;

    public Book(String title, Author author, Genre genre, String summary, int codeISBN, List<Tag> tags, String readLink, String downloadLink, int cuantityCopies) {
        super();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.summary = summary;
        this.codeISBN = codeISBN;
        this.tags = tags;
        this.readLink = readLink;
        this.downloadLink = downloadLink;
        this.cuantityCopies = cuantityCopies;
        this.disponibleCopies = cuantityCopies;
    }

    public int getDisponibleCopies() {
        return disponibleCopies;
    }

    public void setDisponibleCopies(int disponibleCopies) {
        this.disponibleCopies = disponibleCopies;
    }

    public int getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(int borrowed) {
        this.borrowed = borrowed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getCodeISBN() {
        return codeISBN;
    }

    public void setCodeISBN(int codeISBN) {
        this.codeISBN = codeISBN;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getReadLink() {
        return readLink;
    }

    public void setReadLink(String readLink) {
        this.readLink = readLink;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public int getCuantityCopies() {
        return cuantityCopies;
    }

    public void setCuantityCopies(int cuantityCopies) {
        this.cuantityCopies = cuantityCopies;
    }
}
