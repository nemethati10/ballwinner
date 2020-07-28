package com.promotion.ballwinner.bm.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity class representing a Rule.
 */
@Entity
@Table(name = "RULE")
public class RuleBE extends BaseEntity {

    private int maximumWinnersPerDay;

    private int numberOfProducts;

    private int nthEntry;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TERRITORY_ID",  foreignKey = @ForeignKey(name = "Fk_RULE_TERRITORY_ID"))
    private TerritoryBE territory;

    public int getMaximumWinnersPerDay() {
        return maximumWinnersPerDay;
    }

    public void setMaximumWinnersPerDay(final int maximumWinnersPerDay) {
        this.maximumWinnersPerDay = maximumWinnersPerDay;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(final int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public int getNthEntry() {
        return nthEntry;
    }

    public void setNthEntry(final int nthEntry) {
        this.nthEntry = nthEntry;
    }

    public TerritoryBE getTerritory() {
        return territory;
    }

    public void setTerritory(final TerritoryBE territory) {
        this.territory = territory;
    }
}
