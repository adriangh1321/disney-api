package com.alkemy.DisneyApplication.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieFiltersDTO {

    private String title;
    private Set<Long> idGenres;
    private String order;

    public MovieFiltersDTO(String title, Set<Long> idGenres, String order) {
        this.title = title;
        this.idGenres = idGenres;
        this.order = order;
    }

    public Boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public Boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
