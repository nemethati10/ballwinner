package com.promotion.ballwinner.bm.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity class for {@link TerritoryBE}
 */
@Entity
@Table(name = "TERRITORY")
public class TerritoryBE extends BaseEntity {

    private String territoryName;

    @OneToOne(mappedBy = "territory")
    private RuleBE rule;

    @ManyToOne
    @JoinColumn
    private CampaignBE campaign;

    public String getTerritoryName() {
        return territoryName;
    }

    public void setTerritoryName(final String territoryName) {
        this.territoryName = territoryName;
    }

    public RuleBE getRule() {
        return rule;
    }

    public void setRule(final RuleBE rule) {
        this.rule = rule;
    }

    public CampaignBE getCampaign() {
        return campaign;
    }

    public void setCampaign(final CampaignBE campaign) {
        this.campaign = campaign;
    }
}
