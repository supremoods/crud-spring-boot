package com.j_lappay.finals;

import com.j_lappay.finals.languages.Languages;
import com.j_lappay.finals.languages.LanguagesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class LanguagesRepositoryTests {
    @Autowired
    private LanguagesRepository repo;

    @Test
    public void testAddNew() {
        Languages language = new Languages();
        language.setName("JavaScript");
        language.setCreator("Brendad Eich");
        language.setCurrentVersion("3.6");

        Languages savedLanguage = repo.save(language);

        Assertions.assertNotNull(savedLanguage);
        Assertions.assertEquals(language.getName(), savedLanguage.getName());
        Assertions.assertEquals(language.getCreator(), savedLanguage.getCreator());
        Assertions.assertEquals(language.getCurrentVersion(), savedLanguage.getCurrentVersion());
    }

    @Test
    public void testListAll(){
        Iterable<Languages> languages = repo.findAll();
        Assertions.assertNotNull(languages);
        for(Languages language : languages) {
            System.out.println(language);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<Languages> optionalLanguage = repo.findById(userId);
        Languages language = optionalLanguage.get();
        language.setName("JavaScript");
        language.setCreator("Richard Yu");
        language.setCurrentVersion("10");
        repo.save(language);

        Languages updatedLanguage = repo.findById(userId).isPresent() ? repo.findById(userId).get() : null;
        Assertions.assertNotNull(updatedLanguage);
        Assertions.assertEquals(language.getName(), updatedLanguage.getName());
        Assertions.assertEquals(language.getCreator(), updatedLanguage.getCreator());
        Assertions.assertEquals(language.getCurrentVersion(), updatedLanguage.getCurrentVersion());
    }


    // TEST FOR GETTING A LANGUAGE BY ID
    @Test
    public void testGet(){
        Integer userId = 6;
        Optional<Languages> optionalLanguage = repo.findById(userId);
        Assertions.assertTrue(optionalLanguage.isPresent());
        System.out.println(optionalLanguage.get());
    }


    // TEST FOR DELETING A LANGUAGE BY ID
    @Test
    public void testDelete(){
        Integer userId = 1;
        repo.deleteById(userId);
        Optional<Languages> optionalLanguage = repo.findById(userId);
        Assertions.assertFalse(optionalLanguage.isPresent());
    }

}
