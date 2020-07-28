package com.promotion.ballwinner.bm.control.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.promotion.ballwinner.bm.entity.RuleBE;

/**
 * Repository for {@link RuleBE}
 */
@Repository
public interface RuleRepository extends CrudRepository<RuleBE, Long> {
}
