package com.alkemy.DisneyApplication.services;

import com.alkemy.DisneyApplication.dto.GenreDTO;
import com.alkemy.DisneyApplication.entities.GenreEntity;
import java.util.List;

public interface GenreService {

    public List<GenreDTO> getAll();

    public GenreDTO getDetailsById(Long id);

    public GenreDTO save(GenreDTO dto);

    public GenreDTO update(Long id, GenreDTO dto);

    public void delete(Long id);

    public GenreEntity getEntityById(Long id);

    public Boolean existsById(Long id);

    public void validate(GenreDTO dto, GenreEntity entity);
}
