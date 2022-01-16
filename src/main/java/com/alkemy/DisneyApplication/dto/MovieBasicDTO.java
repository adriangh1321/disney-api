package com.alkemy.DisneyApplication.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieBasicDTO {

    private Long id;

    private String image;

    private String title;

    private String creationDate;
}
