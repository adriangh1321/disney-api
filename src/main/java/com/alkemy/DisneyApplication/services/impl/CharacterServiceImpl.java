package com.alkemy.DisneyApplication.services.impl;

import com.alkemy.DisneyApplication.dto.CharacterBasicDTO;
import com.alkemy.DisneyApplication.dto.CharacterDTO;
import com.alkemy.DisneyApplication.dto.CharacterFiltersDTO;
import com.alkemy.DisneyApplication.entities.CharacterEntity;
import com.alkemy.DisneyApplication.exceptions.DuplicateValueException;
import com.alkemy.DisneyApplication.exceptions.NotFoundException;
import com.alkemy.DisneyApplication.mapper.CharacterMapper;
import com.alkemy.DisneyApplication.repositories.CharacterRepository;
import com.alkemy.DisneyApplication.repositories.MovieRepository;
import com.alkemy.DisneyApplication.repositories.specifications.CharacterSpecification;
import com.alkemy.DisneyApplication.services.CharacterService;
import com.alkemy.DisneyApplication.services.MovieService;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacterServiceImpl implements CharacterService {

    private CharacterRepository characterRepository;
    private CharacterMapper characterMapper;
    private MovieService movieService;
    private MovieRepository movieRepository;
    private CharacterSpecification characterSpecification;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository, CharacterMapper characterMapper, MovieService movieService, MovieRepository movieRepository, CharacterSpecification characterSpecification) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.characterSpecification = characterSpecification;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CharacterBasicDTO> getAll() {
        List<CharacterEntity> entities = this.characterRepository.findAll();
        List<CharacterBasicDTO> dtos = this.characterMapper.characterEntityList2BasicDTOList(entities);
        return dtos;
    }

    @Transactional(readOnly = true)
    @Override
    public CharacterDTO getDetailsById(Long id) {
        CharacterEntity entity = this.getEntityById(id);
        CharacterDTO dto = this.characterMapper.characterEntity2DTO(entity, true);
        return dto;
    }

    @Transactional
    @Override
    public CharacterDTO save(CharacterDTO dto) {
        validate(dto, null);
        CharacterEntity entity = this.characterMapper.DTO2CharacterEntity(dto);
        CharacterEntity entitySaved = this.characterRepository.save(entity);
        CharacterDTO result = this.characterMapper.characterEntity2DTO(entitySaved, false);
        return result;
    }

    @Transactional
    @Override
    public CharacterDTO update(Long id, CharacterDTO dto) {

        CharacterEntity entity = this.getEntityById(id);
        validate(dto, entity);
        this.characterMapper.characterEntityRefreshValues(entity, dto);
        CharacterEntity entitySaved = this.characterRepository.save(entity);
        CharacterDTO result = this.characterMapper.characterEntity2DTO(entitySaved, false);
        return result;
    }

    @Transactional
    @Override
    public void delete(Long id) {

        if (this.characterRepository.existsById(id)) {
            this.characterRepository.deleteById(id);
        } else {
            throw new NotFoundException("The character to delete with id " + id + " does not exist");
        }

    }

    @Transactional(readOnly = true)
    @Override
    public CharacterEntity getEntityById(Long id) {
        Optional<CharacterEntity> entity = this.characterRepository.findById(id);
        if (!entity.isPresent()) {
            throw new NotFoundException("The character with id " + id + " does not exist");
        }
        return entity.get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CharacterDTO> getDetailsByFilters(String name, Integer age, Set<Long> idMovies, String order) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, idMovies, order);
        List<CharacterEntity> entities = this.characterRepository.findAll(this.characterSpecification.getByFilters(filtersDTO));
        List<CharacterDTO> dtos = this.characterMapper.characterEntityList2DTOList(entities, true);
        return dtos;
    }

    @Override
    public void validate(CharacterDTO dto, CharacterEntity entity) {
        if (existsByName(dto.getName()) && (entity == null || !entity.getName().equalsIgnoreCase(dto.getName()))) {
            throw new DuplicateValueException("There is already a character with the name '" + dto.getName() + "'");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean existsByName(String name) {
        return this.characterRepository.existsByName(name);
    }

}
