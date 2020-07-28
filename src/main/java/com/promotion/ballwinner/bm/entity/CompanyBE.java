package com.promotion.ballwinner.bm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity class representing a Company.
 */
@Entity
@Table(name = "COMPANY")
public class CompanyBE extends BaseEntity {

    private String companyName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "COMPANY_ID", foreignKey = @ForeignKey(name = "Fk_COMPANY_CAMPAIGN_ID"))
    private Set<CampaignBE> campaigns = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "COMPANY_ID", foreignKey = @ForeignKey(name = "Fk_COMPANY_PRODUCT_ID"))
    private Set<ProductBE> products = new HashSet<>();

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    public Set<CampaignBE> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(final Set<CampaignBE> campaigns) {
        this.campaigns = campaigns;
    }

    public Set<ProductBE> getProducts() {
        return products;
    }

    public void setProducts(final Set<ProductBE> products) {
        this.products = products;
    }
}
