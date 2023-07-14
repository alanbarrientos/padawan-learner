package com.alan.repository;

import com.alan.entity.Author;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

public class AuthorRepository extends Repository<Author>{
    private static final AuthorRepository instance = new AuthorRepository();

    private AuthorRepository() {
        CountryRepository countryRepository = CountryRepository.getInstance();
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
            Author danBrown = new Author("Dan Brown", countryRepository.getByName("United Estates").orElseThrow(RuntimeException::new)
                    , dateBirthDanBrown, null);
            Author margaretMitchell = new Author("Margaret Mitchell", countryRepository.getByName("United Estates").orElseThrow(RuntimeException::new)
                    , dateBirthMargaretMitchell, dateDeathMargaretMitchell);
            entities.add(danBrown);
            entities.add(margaretMitchell);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static AuthorRepository  getInstance(){
        return instance;
    }

    public Author getByName(String name){
        return entities.stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList()).get(0);
    }
}
