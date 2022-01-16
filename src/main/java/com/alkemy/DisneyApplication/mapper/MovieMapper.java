package com.alkemy.DisneyApplication.mapper;

import com.alkemy.DisneyApplication.dto.CharacterDTO;
import com.alkemy.DisneyApplication.dto.MovieBasicDTO;
import com.alkemy.DisneyApplication.dto.MovieDTO;
import com.alkemy.DisneyApplication.entities.CharacterEntity;
import com.alkemy.DisneyApplication.entities.MovieEntity;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    private CharacterMapper characterMapper;
    private GenreMapper genreMapper;

    public MovieMapper(@Lazy CharacterMapper characterMapper, GenreMapper genreMapper) {
        this.characterMapper = characterMapper;
        this.genreMapper = genreMapper;
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity, Boolean loadCharacters, Boolean loadGenre) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setRating(entity.getRating());
        if (loadGenre) {
            dto.setGenre(this.genreMapper.genreEntity2DTO(entity.getGenre()));
        }
        dto.setGenreId(entity.getGenreId());

        if (loadCharacters) {
            List<CharacterDTO> charactersDTO = characterMapper.characterEntityList2DTOList(entity.getCharacters(), false);
            dto.setCharacters(charactersDTO);
        }
        return dto;
    }

    public List<MovieDTO> movieEntityList2DTOList(Collection<MovieEntity> entities, Boolean loadCharacters, Boolean loadGenre) {
        List<MovieDTO> dtos = new ArrayList<>();

        for (MovieEntity entity : entities) {
            dtos.add(this.movieEntity2DTO(entity, loadCharacters, loadGenre));
        }
        return dtos;
    }

    public LocalDate string2LocalDate(String stringDate) {
        if (!stringDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new DateTimeException("The date format must be yyyy-MM-dd");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        LocalDate date;

        try {
            date = LocalDate.parse(stringDate, formatter);
        } catch (DateTimeException e) {
            throw new DateTimeException("That date does not exist");

        }

        return date;
    }

    public MovieBasicDTO movieEntity2BasicDTO(MovieEntity entity) {
        MovieBasicDTO dto = new MovieBasicDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        return dto;
    }

    public List<MovieBasicDTO> movieEntityList2BasicDTOList(List<MovieEntity> entities) {
        List<MovieBasicDTO> dtos = new ArrayList();
        for (MovieEntity entity : entities) {
            dtos.add(this.movieEntity2BasicDTO(entity));
        }
        return dtos;
    }

    public MovieEntity DTO2MovieEntity(MovieDTO dto) {
        MovieEntity entity = new MovieEntity();
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(this.string2LocalDate(dto.getCreationDate()));
        entity.setRating(dto.getRating());
        List<CharacterEntity> characters = this.characterMapper.DTOList2CharacterEntityList(dto.getCharacters());
        entity.setCharacters(characters);
        entity.setGenreId(dto.getGenreId());
        return entity;
    }

    public void movieEntityRefreshValues(MovieEntity entity, MovieDTO dto) {
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(this.string2LocalDate(dto.getCreationDate()));
        entity.setRating(dto.getRating());
//        List<CharacterEntity> characters = this.characterMapper.DTOList2CharacterEntityList(dto.getCharacters());
//        entity.setCharacters(characters);
        entity.setGenreId(dto.getGenreId());
    }

}
