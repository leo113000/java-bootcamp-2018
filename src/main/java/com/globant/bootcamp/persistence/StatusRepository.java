package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.DeliveryMethod;
import com.globant.bootcamp.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface StatusRepository extends JpaRepository<Status, Long> {
}
