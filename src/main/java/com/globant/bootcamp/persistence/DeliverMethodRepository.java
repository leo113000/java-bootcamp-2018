package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.Category;
import com.globant.bootcamp.model.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository public interface DeliverMethodRepository extends JpaRepository<DeliveryMethod, Long> {
}
