package com.promotion.ballwinner.bm.control.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.promotion.ballwinner.bm.entity.TerritoryBE;

/**
 * Repository for {@link TerritoryBE}
 */
@Repository
public interface TerritoryRepository extends CrudRepository<TerritoryBE, Long> {

    TerritoryBE findByTerritoryName(final String name);
}
