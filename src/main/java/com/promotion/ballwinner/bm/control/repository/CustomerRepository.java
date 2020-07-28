package com.promotion.ballwinner.bm.control.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.promotion.ballwinner.bm.entity.CustomerBE;

/**
 * Repository for {@link CustomerBE}
 */
@Repository
public interface CustomerRepository extends CrudRepository<CustomerBE, Long> {
}
