package com.promotion.ballwinner.bm.control.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.promotion.ballwinner.bm.entity.ProductBE;

/**
 * Repository for {@link ProductBE}
 */
@Repository
public interface ProductRepository extends CrudRepository<ProductBE, Long> {

    ProductBE findByProductName(final String name);
}
