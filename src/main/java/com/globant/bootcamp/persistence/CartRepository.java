package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.Cart;
import com.globant.bootcamp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository public interface CartRepository extends JpaRepository<Cart, Long> {

	Cart findByUserId(@Param("user_id")Long userId);

}
