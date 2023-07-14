package com.alan.repository;

import com.alan.entity.Book;
import com.alan.entity.Genre;
import com.alan.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class BookRepository extends Repository<Book>{
    private static final BookRepository instance = new BookRepository();

    private BookRepository() {
        AuthorRepository authorRepository = AuthorRepository.getInstance();
        GenreRepository genreRepository = GenreRepository.getInstance();
        TagRepository tagRepository = TagRepository.getInstance();

        String goneWithTheWindSummary = "Presented as originally released in 1939. Includes themes and character depictions" +
                " which may be offensive and problematic to contemporary audiences. Epic Civil War drama focuses on the life " +
                "of petulant Southern belle Scarlett O'Hara. Starting with her idyllic life on a sprawling plantation, the film " +
                "traces her survival through the tragic history of the South during the Civil War and Reconstruction, and her tangled" +
                " love affairs with Ashley Wilkes and Rhett Butler.";

        String theDaVinciCodeSummary = "Summaries. A murder inside the Louvre, and clues in Da Vinci paintings, lead to the discovery of a" +
                " religious mystery protected by a secret society for two thousand years, which could shake the foundations of Christianity.";

        List<Tag> goneWithTheWindTags = new ArrayList<>();
        goneWithTheWindTags.add(tagRepository.getByName("After Jesus"));
        goneWithTheWindTags.add(tagRepository.getByName("HardBack"));
        List<Tag> theDaVinciCodeTags = new ArrayList<>();
        theDaVinciCodeTags.add(tagRepository.getByName("After Jesus"));
        theDaVinciCodeTags.add(tagRepository.getByName("Detective"));

        Book goneWithTheWind = new Book("Gone With The Wind", authorRepository.getByName("Margaret Mitchell"),
                genreRepository.getByName("Romance"), goneWithTheWindSummary, 12345678,
                goneWithTheWindTags,null, null, 4);
        Book theDaVinciCode = new Book("The Da Vinci Code", authorRepository.getByName("Dan Brown"),
                genreRepository.getByName("Mistery"), theDaVinciCodeSummary, 87654321,
                theDaVinciCodeTags, "https://davincicode.bib.bz/",
                "https://www.amazon.com/Vinci-Code-Robert-Langdon/dp/0307474275", 0);

        entities.add(goneWithTheWind);
        entities.add(theDaVinciCode);
    }
    public static BookRepository getInstance(){
        return instance;
    }

    public List<Book> searchBookByTitle(String title){
        List<Book> bookList = new ArrayList<>();
        for (Book book : entities) {
            if(book.getTitle().contains(title)){
                bookList.add(book);
            }
        }
        return bookList;
    }
    public List<Book> searchBookByGenre(Genre genre){
        List<Book> bookList = new ArrayList<>();
        for (Book book : entities) {
            if(book.getGenre().equals(genre)){
                bookList.add(book);
            }
        }
        return bookList;

    }
    public List<Book> searchBookByTags(List<Tag> tags){
        List<Book> bookList = new ArrayList<>();
        for (Book book : entities) {
            for (Tag tag : book.getTags()) {
                if(tags.contains(tag)){
                    if(!bookList.contains(book)){
                        bookList.add(book);
                    }
                    continue;
                }
                bookList.remove(book);
            }
        }
        return bookList;

    }
    public List<Book> searchBookByAuthorsNameOrLastName(String name){
        List<Book> bookList = new ArrayList<>();
        for (Book book : entities) {
            if(book.getAuthor().getName().contains(name)){
                bookList.add(book);
            }
        }
        return bookList;
    }

    public Book getByTitle(String name){
        return entities.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(name))
                .findFirst().get();
    }
    public void proccessBorrow(Book book){
        book.setBorrowed(book.getBorrowed()+1);
        book.setDisponibleCopies(book.getCuantityCopies()- book.getBorrowed());
        modifyById(book.getId(), book);
    }
    public void proccessReturn(Book book){
        book.setBorrowed(book.getBorrowed()-1);
        book.setDisponibleCopies(book.getCuantityCopies()- book.getBorrowed());
        modifyById(book.getId(), book);
    }
}
