package com.alkemy.DisneyApplication.services.impl;

import com.alkemy.DisneyApplication.dto.GenreDTO;
import com.alkemy.DisneyApplication.entities.GenreEntity;
import com.alkemy.DisneyApplication.exceptions.DuplicateValueException;
import com.alkemy.DisneyApplication.exceptions.NotFoundException;
import com.alkemy.DisneyApplication.mapper.GenreMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.DisneyApplication.services.GenreService;
import com.alkemy.DisneyApplication.repositories.GenreRepository;
import com.alkemy.DisneyApplication.services.MovieService;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;
    private GenreMapper genreMapper;
    private MovieService movieService;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper, @Lazy MovieService movieService) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
        this.movieService = movieService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<GenreDTO> getAll() {
        List<GenreEntity> entities = this.genreRepository.findAll();
        List<GenreDTO> dtos = this.genreMapper.genreEntityList2DTOList(entities);
        return dtos;
    }

    @Transactional(readOnly = true)
    @Override
    public GenreDTO getDetailsById(Long id) {
        GenreEntity entity = this.getEntityById(id);
        GenreDTO dto = this.genreMapper.genreEntity2DTO(entity);
        return dto;
    }

    @Transactional
    @Override
    public GenreDTO save(GenreDTO dto) {
        validate(dto, null);
        GenreEntity entity = this.genreMapper.DTO2GenreEntity(dto);
        GenreEntity entitySaved = this.genreRepository.save(entity);
        GenreDTO result = this.genreMapper.genreEntity2DTO(entitySaved);
        return result;
    }

    @Transactional
    @Override
    public GenreDTO update(Long id, GenreDTO dto) {
        GenreEntity entity = this.getEntityById(id);
        validate(dto, entity);
        this.genreMapper.genreEntityRefreshValues(entity, dto);
        GenreEntity entitySaved = this.genreRepository.save(entity);
        GenreDTO result = this.genreMapper.genreEntity2DTO(entitySaved);
        return result;
    }

    @Transactional
    @Override
    public void delete(Long id) {

        if (this.genreRepository.existsById(id)) {
            this.movieService.removeAllGenre(id);
            this.genreRepository.deleteById(id);
        } else {
            throw new NotFoundException("The genre to delete with id " + id + " does not exist");

        }

    }

    @Transactional(readOnly = true)
    @Override
    public GenreEntity getEntityById(Long id) {
        Optional<GenreEntity> entity = this.genreRepository.findById(id);
        if (!entity.isPresent()) {
            throw new NotFoundException("The genre with id " + id + " does not exist");
        }
        return entity.get();
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean existsById(Long id) {
        return this.genreRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public void validate(GenreDTO dto, GenreEntity entity) {
        if (this.genreRepository.existsByName(dto.getName()) && (entity == null || !entity.getName().equalsIgnoreCase(dto.getName()))) {
            throw new DuplicateValueException("There is already a genre with the name '" + dto.getName() + "'");
        }
    }

}
