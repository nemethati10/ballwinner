package com.promotion.ballwinner.bm.control.dao;

import org.springframework.stereotype.Service;

import com.promotion.ballwinner.bm.control.repository.TerritoryRepository;
import com.promotion.ballwinner.bm.entity.CampaignBE;
import com.promotion.ballwinner.bm.entity.TerritoryBE;

/**
 * Business activity for managing {@link TerritoryBE}
 */
@Service
public class ManageTerritoryBA {

    private TerritoryRepository territoryRepository;

    public ManageTerritoryBA(final TerritoryRepository territoryRepository) {
        this.territoryRepository = territoryRepository;
    }

    public TerritoryBE create(final String name, final CampaignBE campaign) {
        final TerritoryBE territory = new TerritoryBE();
        territory.setTerritoryName(name.toUpperCase());
        territory.setCampaign(campaign);

        return territoryRepository.save(territory);
    }

    public TerritoryBE findByName(final String name) {
        return territoryRepository.findByTerritoryName(name);
    }
}
