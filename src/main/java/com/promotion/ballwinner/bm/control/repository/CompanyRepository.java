package com.promotion.ballwinner.bm.control.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.promotion.ballwinner.bm.entity.CompanyBE;

/**
 * Repository for {@link CompanyBE}
 */
@Repository
public interface CompanyRepository extends CrudRepository<CompanyBE, Long> {
}
