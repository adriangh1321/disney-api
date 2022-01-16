package com.alkemy.DisneyApplication.repositories.specifications;

import com.alkemy.DisneyApplication.dto.MovieFiltersDTO;
import com.alkemy.DisneyApplication.entities.GenreEntity;
import com.alkemy.DisneyApplication.entities.MovieEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filterDTO) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList();

            if (StringUtils.hasLength(filterDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filterDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }
            
            if (!CollectionUtils.isEmpty(filterDTO.getIdGenres())) {
               
                Join<MovieEntity, GenreEntity> join = root.join("genre", JoinType.INNER);
                Expression<String> genresId = join.get("id");
                predicates.add(genresId.in(filterDTO.getIdGenres()));

            }

            //remove duplicates
            query.distinct(true);

            //order resolver
            String orderByField = "title";
            query.orderBy(
                    filterDTO.isASC()
                    ? criteriaBuilder.asc(root.get(orderByField))
                    : criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
