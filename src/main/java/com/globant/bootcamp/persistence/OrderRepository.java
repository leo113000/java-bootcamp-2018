package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.Order;
import com.globant.bootcamp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findAllByUser(@Param("user") User user);
}
