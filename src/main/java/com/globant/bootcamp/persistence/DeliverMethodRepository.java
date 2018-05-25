package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.DeliverMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface DeliverMethodRepository extends JpaRepository<DeliverMethod, Long> {
}
