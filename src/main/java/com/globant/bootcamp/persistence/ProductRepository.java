package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.Category;
import com.globant.bootcamp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p from Product p where p.name = :name")
	Product findByName (@Param("name")String name);

	@Query("select p from Product p where :category IN p.categories")
	List<Product> findByCategory(@Param ("category") Category category);
}
