package com.promotion.ballwinner.bm.control.dao;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.promotion.ballwinner.bm.control.repository.CampaignRepository;
import com.promotion.ballwinner.bm.entity.CampaignBE;
import com.promotion.ballwinner.bm.entity.CompanyBE;

/**
 * Business activity for managing {@link CampaignBE}
 */
@Service
public class ManageCampaignBA {

    private CampaignRepository campaignRepository;

    public ManageCampaignBA(final CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public CampaignBE create(final String name, final LocalDate startDate, final LocalDate endDate,
            final CompanyBE company) {
        final CampaignBE campaign = new CampaignBE();
        campaign.setCampaignName(name);
        campaign.setStartDate(startDate);
        campaign.setEndDate(endDate);
        campaign.setCompany(company);

        return campaignRepository.save(campaign);
    }
}
