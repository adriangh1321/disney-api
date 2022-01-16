package com.alkemy.DisneyApplication.mapper;

import com.alkemy.DisneyApplication.dto.GenreDTO;
import com.alkemy.DisneyApplication.entities.GenreEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreDTO genreEntity2DTO(GenreEntity entity) {
        GenreDTO dto = new GenreDTO();
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setImage(entity.getImage());
            dto.setName(entity.getName());
            return dto;
        }
        return null;

    }

    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> entities) {
        List<GenreDTO> dtos = new ArrayList();
        for (GenreEntity entity : entities) {
            dtos.add(this.genreEntity2DTO(entity));
        }
        return dtos;
    }

    public GenreEntity DTO2GenreEntity(GenreDTO dto) {
        GenreEntity entity = new GenreEntity();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        return entity;
    }

    public void genreEntityRefreshValues(GenreEntity entity, GenreDTO dto) {
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
    }

}
