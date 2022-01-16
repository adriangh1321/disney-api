package com.alkemy.DisneyApplication.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenreDTO {

    private Long id;

    @NotBlank(message = "The name must not be empty")
    @NotNull(message = "The name must not be null")
    @Size(max = 50, message = "The name must be no longer than 50 characters")
    private String name;

    @NotBlank(message = "The image url must not be empty")
    @NotNull(message = "The image url must not be empty")
    @Size(max = 50, message = "The image url must be no longer than 255 characters")
    private String image;

}
