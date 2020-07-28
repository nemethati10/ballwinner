package com.promotion.ballwinner.bm.control.dao;

import org.springframework.stereotype.Service;

import com.promotion.ballwinner.bm.control.repository.RuleRepository;
import com.promotion.ballwinner.bm.entity.RuleBE;
import com.promotion.ballwinner.bm.entity.TerritoryBE;

/**
 * Business activity for managing {@link RuleBE}
 */
@Service
public class ManageRuleBA {

    private RuleRepository ruleRepository;

    public ManageRuleBA(final RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public RuleBE create(final int maximumWinnersPerDay, final int numberOfProducts, final int nthEntry,
            final TerritoryBE territory) {
        final RuleBE rule = new RuleBE();
        rule.setMaximumWinnersPerDay(maximumWinnersPerDay);
        rule.setNumberOfProducts(numberOfProducts);
        rule.setNthEntry(nthEntry);
        rule.setTerritory(territory);

        return ruleRepository.save(rule);
    }

}
