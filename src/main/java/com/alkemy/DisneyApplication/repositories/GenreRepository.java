package com.alkemy.DisneyApplication.repositories;

import com.alkemy.DisneyApplication.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    Boolean existsByName(String name);
}
