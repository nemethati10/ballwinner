package com.promotion.ballwinner.bm.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity class representing a Campaign.
 */
@Entity
@Table(name = "CAMPAIGN")
public class CampaignBE extends BaseEntity {

    private String campaignName;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    @JoinColumn
    private CompanyBE company;

    @OneToMany
    @JoinColumn(name = "CAMPAIGN_ID", foreignKey = @ForeignKey(name = "Fk_CAMPAIGN_TERRITORY_ID"))
    private Set<TerritoryBE> territories = new HashSet<>();

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(final String campaignName) {
        this.campaignName = campaignName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    public CompanyBE getCompany() {
        return company;
    }

    public void setCompany(final CompanyBE company) {
        this.company = company;
    }

    public Set<TerritoryBE> getTerritories() {
        return territories;
    }

    public void setTerritories(final Set<TerritoryBE> territories) {
        this.territories = territories;
    }
}
