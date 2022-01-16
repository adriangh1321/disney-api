package com.alkemy.DisneyApplication.services;

import com.alkemy.DisneyApplication.dto.MovieBasicDTO;
import com.alkemy.DisneyApplication.dto.MovieDTO;
import com.alkemy.DisneyApplication.entities.MovieEntity;
import java.util.List;
import java.util.Set;

public interface MovieService {

    public List<MovieBasicDTO> getAll();

    public MovieDTO getDetailsById(Long id);

    public MovieDTO save(MovieDTO dto);

    public MovieDTO update(Long id, MovieDTO dto);

    public void delete(Long id);

    public MovieEntity getEntityById(Long id);

    public void addCharacter(Long idMovie, Long idCharacter);

    public void removeCharacter(Long idMovie, Long idCharacter);

    public List<MovieDTO> getDetailsByFilters(String title, Set<Long> idGenres, String order);

    public void validate(MovieDTO dto, MovieEntity entity);

    public void removeAllGenre(Long idGenre);

}
