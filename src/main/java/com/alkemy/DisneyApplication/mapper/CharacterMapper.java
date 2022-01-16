package com.alkemy.DisneyApplication.mapper;

import com.alkemy.DisneyApplication.dto.CharacterBasicDTO;
import com.alkemy.DisneyApplication.dto.CharacterDTO;
import com.alkemy.DisneyApplication.dto.MovieDTO;
import com.alkemy.DisneyApplication.entities.CharacterEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;

    public CharacterEntity DTO2CharacterEntity(CharacterDTO dto) {
        CharacterEntity entity = new CharacterEntity();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());
        return entity;

    }

    public CharacterBasicDTO characterEntity2BasicDTO(CharacterEntity entity) {
        CharacterBasicDTO dto = new CharacterBasicDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        return dto;
    }

    public CharacterEntity BasicDTO2characterEntity(CharacterBasicDTO dto) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        return characterEntity;
    }

    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<CharacterEntity> entities) {
        List<CharacterBasicDTO> dtos = new ArrayList();

        for (CharacterEntity entity : entities) {
            dtos.add(this.characterEntity2BasicDTO(entity));
        }
        return dtos;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity, Boolean loadMovies) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setStory(entity.getStory());
        if (loadMovies) {
            List<MovieDTO> moviesDTO = movieMapper.movieEntityList2DTOList(entity.getMovies(), false, true);
            dto.setMovies(moviesDTO);
        }
        return dto;
    }

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities, Boolean loadMovies) {
        List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(this.characterEntity2DTO(entity, loadMovies));
        }
        return dtos;
    }

    public void characterEntityRefreshValues(CharacterEntity entity, CharacterDTO dto) {
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());
    }

    public List<CharacterEntity> DTOList2CharacterEntityList(List<CharacterDTO> dtos) {
        List<CharacterEntity> entities = new ArrayList();
        for (CharacterDTO dto : dtos) {
            entities.add(this.DTO2CharacterEntity(dto));
        }
        return entities;
    }

}
