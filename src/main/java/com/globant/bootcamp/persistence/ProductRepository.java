package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.Category;
import com.globant.bootcamp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByUrl (@Param("url")String url);

	List<Product> findByCategories(@Param ("category") Category category);
}
