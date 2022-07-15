package com.j_lappay.finals.languages;

import org.springframework.data.repository.CrudRepository;

public interface LanguagesRepository extends CrudRepository<Languages, Integer> {
    public Long countById(Integer id);
}
