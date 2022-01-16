package com.alkemy.DisneyApplication.services;

import com.alkemy.DisneyApplication.dto.CharacterBasicDTO;
import com.alkemy.DisneyApplication.dto.CharacterDTO;
import com.alkemy.DisneyApplication.entities.CharacterEntity;
import java.util.List;
import java.util.Set;

public interface CharacterService {

    public List<CharacterBasicDTO> getAll();

    public CharacterDTO getDetailsById(Long id);

    public CharacterDTO save(CharacterDTO dto);

    public CharacterDTO update(Long id, CharacterDTO dto);

    public void delete(Long id);

    public CharacterEntity getEntityById(Long id);

    public List<CharacterDTO> getDetailsByFilters(String name, Integer age, Set<Long> idMovies, String order);

    public void validate(CharacterDTO dto,CharacterEntity entity);
    
    public Boolean existsByName(String name);

}
