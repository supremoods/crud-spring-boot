package com.j_lappay.finals.languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    private LanguagesRepository repo;

    public List<Languages> listAll() {
        return (List<Languages>) repo.findAll();
    }

    public void save(Languages language) {
        repo.save(language);
    }

    public Languages get(Integer id) throws LanguageNotFoundException{
        Optional<Languages> language = repo.findById(id);
        if(language.isPresent()){
            return language.get();
        }else{
            throw new LanguageNotFoundException("Language could not find with id: " + id);
        }
    }

    public void delete(Integer id) throws LanguageNotFoundException {
        Long count = repo.countById(id);
        if(count > 0) {
            repo.deleteById(id);
        } else {
            throw new LanguageNotFoundException("Language could not find with id: " + id);
        }

    }
}
