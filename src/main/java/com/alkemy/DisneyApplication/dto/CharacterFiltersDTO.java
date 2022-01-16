package com.alkemy.DisneyApplication.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CharacterFiltersDTO {

    private String name;
    private Integer age;
    private Set<Long> idMovies;
    private String order;

    public CharacterFiltersDTO(String name, Integer age, Set<Long> idMovies, String order) {
        this.name = name;
        this.age = age;
        this.idMovies = idMovies;
        this.order = order;
    }

    public Boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public Boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
