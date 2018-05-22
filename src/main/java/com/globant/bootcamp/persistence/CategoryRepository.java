package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.Category;
import com.globant.bootcamp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName (@Param("name")String name);

}
