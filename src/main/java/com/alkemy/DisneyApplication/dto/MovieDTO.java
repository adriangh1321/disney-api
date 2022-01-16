package com.alkemy.DisneyApplication.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieDTO {

    private Long id;

    @NotBlank(message = "The image url must not be empty")
    @NotNull(message = "The image url must not be null")
    private String image;

    @NotBlank(message = "The title must not be empty")
    @NotNull(message = "The title must not be null")
    @Size(max = 100, message = "The title must be no longer than 100 characters")
    private String title;

    @NotNull(message = "The creation date must not be null")
    private String creationDate;

    @Min(value = 1, message = "The rating must not be less than 1")
    @Max(value = 5, message = "The rating must not be greater than 5")
    private Integer rating;

    @Valid
    @NotNull(message="The Characters list must not be null")
    private List<CharacterDTO> characters = new ArrayList();

    private GenreDTO genre;

    private Long genreId;

}
