package com.alkemy.DisneyApplication.repositories;

import com.alkemy.DisneyApplication.entities.CharacterEntity;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long>, JpaSpecificationExecutor<CharacterEntity> {

    CharacterEntity findByName(String name);

    Boolean existsByName(String name);

    List<CharacterEntity> findAll(Specification<CharacterEntity> spec);
}
