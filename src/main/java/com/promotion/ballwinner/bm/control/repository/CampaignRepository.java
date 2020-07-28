package com.promotion.ballwinner.bm.control.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.promotion.ballwinner.bm.entity.CampaignBE;

/**
 * Repository for {@link CampaignBE}
 */
@Repository
public interface CampaignRepository extends CrudRepository<CampaignBE, Long> {
}
