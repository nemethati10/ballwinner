package com.promotion.ballwinner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.promotion.ballwinner.bm.control.dao.ManageCampaignBA;
import com.promotion.ballwinner.bm.control.dao.ManageCompanyBA;
import com.promotion.ballwinner.bm.control.dao.ManageProductBA;
import com.promotion.ballwinner.bm.control.dao.ManageRuleBA;
import com.promotion.ballwinner.bm.control.dao.ManageTerritoryBA;
import com.promotion.ballwinner.bm.entity.CampaignBE;
import com.promotion.ballwinner.bm.entity.CompanyBE;
import com.promotion.ballwinner.bm.entity.ProductBE;
import com.promotion.ballwinner.bm.entity.RuleBE;
import com.promotion.ballwinner.bm.entity.TerritoryBE;

/**
 * Initializes the database at startup.
 */
@Component
@Profile("test")
public class DatabaseInitializer implements CommandLineRunner {

    private static final int PRODUCT_NUMBERS = 1000;
    private ManageCompanyBA manageCompanyBA;
    private ManageCampaignBA manageCampaignBA;
    private ManageProductBA manageProductBA;
    private ManageRuleBA manageRuleBA;
    private ManageTerritoryBA manageTerritoryBA;

    public DatabaseInitializer(final ManageCompanyBA manageCompanyBA, final ManageCampaignBA manageCampaignBA,
            final ManageProductBA manageProductBA, final ManageRuleBA manageRuleBA,
            final ManageTerritoryBA manageTerritoryBA) {
        this.manageCompanyBA = manageCompanyBA;
        this.manageCampaignBA = manageCampaignBA;
        this.manageProductBA = manageProductBA;
        this.manageRuleBA = manageRuleBA;
        this.manageTerritoryBA = manageTerritoryBA;
    }

    @Override
    public void run(final String... args) {
        init();
    }

    private void init() {
        createCompanyData();
    }

    private void createCompanyData() {
        final CompanyBE company = manageCompanyBA.create("COMPANY-1");

        final Set<CampaignBE> campaigns = new HashSet<>();
        campaigns.add(createCampaignData(company));

        final Set<ProductBE> products = createProductData(company);
        company.setCampaigns(campaigns);
        company.setProducts(products);

    }

    private CampaignBE createCampaignData(final CompanyBE company) {

        final LocalDate startDate = LocalDate.of(2020, 6, 20);
        final LocalDate endDate = startDate.plusMonths(2);

        final CampaignBE campaign = manageCampaignBA.create("EURO-2020-CAMPAIGN", startDate, endDate, company);

        final Set<TerritoryBE> territories = createTerritoriesWithRules(campaign);
        campaign.setTerritories(territories);

        return campaign;
    }

    private Set<ProductBE> createProductData(final CompanyBE company) {
        final Set<ProductBE> products = new HashSet<>();

        for (int i = 0; i < PRODUCT_NUMBERS; i++) {
            final ProductBE product = manageProductBA.create("PRODUCT" + i, company);
            products.add(product);
        }

        return products;
    }

    private Set<TerritoryBE> createTerritoriesWithRules(final CampaignBE campaign) {
        final Set<TerritoryBE> territories = new HashSet<>();

        final TerritoryBE hungary = manageTerritoryBA.create(TerritoryEnum.HUNGARY.toString(), campaign);
        final TerritoryBE germany = manageTerritoryBA.create(TerritoryEnum.GERMANY.toString(), campaign);

        final RuleBE hungaryRule = manageRuleBA.create(100, 5000, 80, hungary);
        final RuleBE germanyRule = manageRuleBA.create(250, 10000, 40, germany);

        hungary.setRule(hungaryRule);
        germany.setRule(germanyRule);

        territories.add(hungary);
        territories.add(germany);

        return territories;
    }

    private enum TerritoryEnum {
        HUNGARY,
        GERMANY
    }
}
