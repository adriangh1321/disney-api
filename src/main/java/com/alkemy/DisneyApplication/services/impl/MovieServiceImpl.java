package com.alkemy.DisneyApplication.services.impl;

import com.alkemy.DisneyApplication.dto.CharacterDTO;
import com.alkemy.DisneyApplication.dto.MovieBasicDTO;
import com.alkemy.DisneyApplication.dto.MovieDTO;
import com.alkemy.DisneyApplication.dto.MovieFiltersDTO;
import com.alkemy.DisneyApplication.entities.CharacterEntity;
import com.alkemy.DisneyApplication.entities.MovieEntity;
import com.alkemy.DisneyApplication.exceptions.DuplicateValueException;
import com.alkemy.DisneyApplication.exceptions.NotFoundException;
import com.alkemy.DisneyApplication.mapper.MovieMapper;
import com.alkemy.DisneyApplication.repositories.MovieRepository;
import com.alkemy.DisneyApplication.repositories.specifications.MovieSpecification;
import com.alkemy.DisneyApplication.services.CharacterService;
import com.alkemy.DisneyApplication.services.GenreService;
import com.alkemy.DisneyApplication.services.MovieService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private GenreService genreService;
    private MovieMapper movieMapper;
    private MovieSpecification movieSpecification;
    private CharacterService characterService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, @Lazy CharacterService characterService, MovieMapper movieMapper, MovieSpecification movieSpecification, GenreService genreService) {
        this.movieRepository = movieRepository;
        this.characterService = characterService;
        this.movieMapper = movieMapper;
        this.movieSpecification = movieSpecification;
        this.genreService = genreService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<MovieBasicDTO> getAll() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieBasicDTO> dtos = movieMapper.movieEntityList2BasicDTOList(entities);
        return dtos;
    }

    @Transactional(readOnly = true)
    @Override
    public MovieDTO getDetailsById(Long id) {
        MovieEntity entity = this.getEntityById(id);
        MovieDTO dto = this.movieMapper.movieEntity2DTO(entity, true, true);
        return dto;
    }

    @Transactional
    @Override
    public MovieDTO save(MovieDTO dto) {
        validate(dto, null);
        MovieEntity entity = movieMapper.DTO2MovieEntity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved, true, false);
        return result;
    }

    @Transactional
    @Override
    public MovieDTO update(Long id, MovieDTO dto) {
        MovieEntity entity = this.getEntityById(id);
        validate(dto, entity);
        this.movieMapper.movieEntityRefreshValues(entity, dto);
        MovieEntity entitySaved = this.movieRepository.save(entity);
        MovieDTO result = this.movieMapper.movieEntity2DTO(entitySaved, true, false);
        return result;
    }

    @Transactional
    @Override
    public void delete(Long id) {

        if (this.movieRepository.existsById(id)) {
            this.movieRepository.deleteById(id);
        } else {
            throw new NotFoundException("The movie to delete with id " + id + " does not exist");
        }

    }

    @Transactional(readOnly = true)
    @Override
    public MovieEntity getEntityById(Long id) {
        Optional<MovieEntity> entity = this.movieRepository.findById(id);
        if (!entity.isPresent()) {
            throw new NotFoundException("The movie with id " + id + " does not exist");
        }
        return entity.get();

    }

    @Transactional
    @Override
    public void addCharacter(Long idMovie, Long idCharacter) {
        MovieEntity movieEntity = this.getEntityById(idMovie);
        CharacterEntity characterEntity = this.characterService.getEntityById(idCharacter);
        movieEntity.getCharacters();
        movieEntity.addCharacter(characterEntity);

        this.movieRepository.save(movieEntity);
    }

    @Transactional
    @Override
    public void removeCharacter(Long idMovie, Long idCharacter) {
        MovieEntity movieEntity = this.getEntityById(idMovie);
        CharacterEntity characterEntity = this.characterService.getEntityById(idCharacter);
        movieEntity.getCharacters();
        movieEntity.removeCharacter(characterEntity);

        this.movieRepository.save(movieEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MovieDTO> getDetailsByFilters(String title, Set<Long> idGenres, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(title, idGenres, order);
        List<MovieEntity> entities = this.movieRepository.findAll(this.movieSpecification.getByFilters(filtersDTO));
        List<MovieDTO> dtos = this.movieMapper.movieEntityList2DTOList(entities, false, false);
        return dtos;
    }

    @Transactional(readOnly = true)
    @Override
    public void validate(MovieDTO dto, MovieEntity entity) {
        if (this.movieRepository.existsByTitle(dto.getTitle()) && (entity == null || !entity.getTitle().equalsIgnoreCase(dto.getTitle()))) {
            throw new DuplicateValueException("There is already a movie with the title '" + dto.getTitle() + "'");
        }
        if (dto.getGenreId() != null && !this.genreService.existsById(dto.getGenreId())) {
            throw new NotFoundException("Does not exist a genre with id " + dto.getGenreId() + ". The genre must be created before");
        }
        if (!dto.getCharacters().isEmpty()) {

            for (CharacterDTO dtoCharacter : dto.getCharacters()) {

                this.characterService.validate(dtoCharacter, null);
            }
        }
    }

    @Transactional
    @Override
    public void removeAllGenre(Long idGenre) {
        this.movieRepository.removeAllGenre(idGenre);
    }

}
