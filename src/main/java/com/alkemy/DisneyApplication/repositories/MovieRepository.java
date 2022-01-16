package com.alkemy.DisneyApplication.repositories;

import com.alkemy.DisneyApplication.entities.MovieEntity;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, JpaSpecificationExecutor<MovieEntity> {

    Boolean existsByTitle(String title);

    List<MovieEntity> findAll(Specification<MovieEntity> spec);

    @Modifying
    @Query(value = "UPDATE movies SET genre_id = NULL WHERE genre_id =?;", nativeQuery = true)
    void removeAllGenre(Long idGenre);

}
