package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByUrl(@Param("url") String url);

}
