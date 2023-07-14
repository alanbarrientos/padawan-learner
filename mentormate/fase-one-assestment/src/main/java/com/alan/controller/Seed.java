package com.alan.controller;

import com.alan.entities.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Seed {
    private static final Seed instance = new Seed();
    private Country paraguay;
    private Country bulgaria;
    private Country unitedEstates;
    private City asuncion;
    private City caacupe;
    private City sofia;
    private Author danBrown;
    private Author margaretMitchell;
    private Genre romance;
    private Genre mistery;

    private Tag fictionTag;
    private Tag vintageTag;
    private Tag detectiveTag;

    private Book goneWithTheWind;
    private Book theDaVinciCode;
    User alan;
    User julia;

    private Seed() {
        fillAll();
    }

    private void fillAll(){
        paraguay = new Country("Paraguay");
        bulgaria = new Country("Bulgaria");
        unitedEstates = new Country("United Estates");

        asuncion = new City(paraguay, "Asuncion");
        caacupe = new City(paraguay, "Caacupe");
        sofia = new City(bulgaria, "Sofia");

        Date dateBirthDanBrown = null;
        Date dateBirthMargaretMitchell = null;
        Date dateDeathMargaretMitchell = null;
        try{
            String dateBirthDanBrownString = "22-06-1964";
            String dateBirthMargaretMitchellString = "08-11-1900";
            String dateDeathMargaretMitchellString = "16-08-1949";
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            dateBirthDanBrown  = formatter.parse(dateBirthDanBrownString);
            dateBirthMargaretMitchell  = formatter.parse(dateBirthMargaretMitchellString);
            dateDeathMargaretMitchell  = formatter.parse(dateDeathMargaretMitchellString);
        } catch (Exception e){
            e.printStackTrace();
        }
        danBrown = new Author("Dan Brown", unitedEstates, dateBirthDanBrown, null);
        margaretMitchell = new Author("Margaret Mitchell", unitedEstates, dateBirthMargaretMitchell,
                dateDeathMargaretMitchell);

        romance = new Genre("Romance");
        mistery = new Genre("Mistery");

        String goneWithTheWindSummary = "Presented as originally released in 1939. Includes themes and character depictions" +
                " which may be offensive and problematic to contemporary audiences. Epic Civil War drama focuses on the life " +
                "of petulant Southern belle Scarlett O'Hara. Starting with her idyllic life on a sprawling plantation, the film " +
                "traces her survival through the tragic history of the South during the Civil War and Reconstruction, and her tangled" +
                " love affairs with Ashley Wilkes and Rhett Butler.";
        String theDaVinciCodeSummary = "Summaries. A murder inside the Louvre, and clues in Da Vinci paintings, lead to the discovery of a" +
                " religious mystery protected by a secret society for two thousand years, which could shake the foundations of Christianity.";

        fictionTag = new Tag("Fiction");
        vintageTag = new Tag("Vintage");
        detectiveTag = new Tag("Detective");

        List<Tag> goneWithTheWindTags = new ArrayList<Tag>();
        goneWithTheWindTags.add(fictionTag);
        goneWithTheWindTags.add(vintageTag);
        List<Tag> theDaVinciCodeTags = new ArrayList<Tag>();
        theDaVinciCodeTags.add(fictionTag);
        theDaVinciCodeTags.add(vintageTag);
        theDaVinciCodeTags.add(detectiveTag);

        goneWithTheWind = new Book("Gone With The Wind", margaretMitchell, romance,
                goneWithTheWindSummary, 12345678, goneWithTheWindTags,null, null, 4);
        theDaVinciCode = new Book("The Da Vinci Code", danBrown, mistery, theDaVinciCodeSummary, 87654321,
                theDaVinciCodeTags, "https://davincicode.bib.bz/",
                "https://www.amazon.com/Vinci-Code-Robert-Langdon/dp/0307474275", 0);

        Address italia1234 = new Address(asuncion, "Italia casi Proceres de Mayo", 1234);
        List<Address> addressesAlan = new ArrayList<Address>();
        addressesAlan.add(italia1234);

        Address generalRoa4321 = new Address(caacupe, "General Roa casi Ira Proyectada", 4321);
        Address zorrilla3214 = new Address(asuncion, "Zorrilla casi Campo Via", 3214);
        List<Address> addressesJulia = new ArrayList<Address>();
        addressesJulia.add(generalRoa4321);
        addressesJulia.add(zorrilla3214);

        List<String> emailsAlan = new ArrayList<String>();
        emailsAlan.add("alanbarrientosdh@gmail.com");
        List<String> emailsJulia = new ArrayList<String>();
        emailsJulia.add("juliaperes@gmail.com");
        emailsJulia.add("peresjulia@gmail.com");
        boolean alanAcceptEUGDPR = true;
        boolean juliaAcceptEUGDPR = true;


        try {
            alan = new User("alan", 19, Gender.MALE, addressesAlan, emailsAlan, alanAcceptEUGDPR);
            julia = new User("julia", 23, Gender.FEMALE, addressesJulia, emailsJulia, juliaAcceptEUGDPR);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Author> getAuthors(){
        List<Author> authors = new ArrayList<Author>();
        authors.add(danBrown);
        authors.add(margaretMitchell);
        return authors;
    }

    public List<Book> getBooks(){
        List<Book> books = new ArrayList<Book>();
        books.add(goneWithTheWind);
        books.add(theDaVinciCode);
        return books;
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<User>();
        users.add(alan);
        users.add(julia);
        return users;
    }

    public List<Tag> getTags(){
        List<Tag> tags = new ArrayList<Tag>();
        tags.add(fictionTag);
        tags.add(vintageTag);
        tags.add(detectiveTag);
        return tags;
    }

    public List<Genre> getGenres(){
        List<Genre> genres = new ArrayList<Genre>();
        genres.add(romance);
        genres.add(mistery);
        return genres;
    }

    public static Seed getInstance(){
        return instance;
    }
}
