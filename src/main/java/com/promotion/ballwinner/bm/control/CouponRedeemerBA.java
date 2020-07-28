package com.promotion.ballwinner.bm.control;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.promotion.ballwinner.bm.control.dao.ManageLogEntryBA;
import com.promotion.ballwinner.bm.control.dao.ManageTerritoryBA;
import com.promotion.ballwinner.bm.entity.CustomerBE;
import com.promotion.ballwinner.bm.entity.LogEntryBE;
import com.promotion.ballwinner.bm.entity.RedeemResult;
import com.promotion.ballwinner.bm.entity.RuleBE;
import com.promotion.ballwinner.bm.entity.TerritoryBE;
import com.promotion.ballwinner.bm.util.validator.CouponValidator;
import com.promotion.ballwinner.bm.util.validator.CustomerValidator;

@Service
public class CouponRedeemerBA {

    private ManageLogEntryBA manageLogEntry;
    private ManageTerritoryBA manageTerritoryBA;

    public CouponRedeemerBA(final ManageLogEntryBA manageLogEntry, final ManageTerritoryBA manageTerritoryBA) {
        this.manageLogEntry = manageLogEntry;
        this.manageTerritoryBA = manageTerritoryBA;
    }

    /**
     * Redeems a coupon. Returns if it is winner or not.
     *
     * @param customer
     *
     * @return
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public RedeemResult redeemCoupon(final CustomerBE customer, final String territoryName) {

        final boolean isSubmittedDataValid = validateSubmittedData(customer, territoryName);

        if (!isSubmittedDataValid) {
            // TODO ANE: create specific exceptions
            throw new IllegalStateException("Invalid input data!");
        }

        final TerritoryBE territory = manageTerritoryBA.findByName(territoryName.toUpperCase());

        if (territory == null) {
            throw new IllegalStateException("Territory was not found!");
        }

        final LogEntryBE logEntry = manageLogEntry.create(customer, territoryName);
        final boolean isCouponWinner = isCouponWinner(territory);
        if (isCouponWinner) {
            logEntry.setWinner(true);
            return RedeemResult.WINNER;
        }

        return RedeemResult.BETTER_LUCK_NEXT_TIME;
    }

    private boolean validateSubmittedData(final CustomerBE customer, final String territoryName) {

        if (territoryName == null || territoryName.isEmpty()) {
            return false;
        }

        final boolean isCustomerValid = CustomerValidator.isCustomerValid(customer);

        if (!isCustomerValid) {
            return false;
        }

        final String code = customer.getCouponCode();
        final boolean isCouponRedeemed = manageLogEntry.existsByCode(code);

        return CouponValidator.validateCoupon(code, isCouponRedeemed);
    }

    private boolean isCouponWinner(final TerritoryBE territory) {

        final RuleBE rule = territory.getRule();
        if (rule == null) {
            throw new IllegalStateException("Rule is null!");
        }

        // TODO ANE: create a scheduler which will run at the end of every day and will subtract the number of winners from the total products

        final int totalProducts = rule.getNumberOfProducts();
        final int nthEntry = rule.getNthEntry();
        final int numberOfWinnersPerDay = rule.getMaximumWinnersPerDay();

        final String territoryName = territory.getTerritoryName().toUpperCase();

        final long todayEntriesByTerritory = manageLogEntry.countTodayEntriesByTerritory(territoryName);
        final long todayWinnersByTerritory = manageLogEntry.countTodayWinnersByTerritory(territoryName);

        final boolean areProductsAvailable = checkIfProductsAreAvailable(todayWinnersByTerritory, totalProducts);
        final boolean areProductsAvailableForToday =
                checkIfProductsAreAvailable(todayWinnersByTerritory, numberOfWinnersPerDay);
        final boolean isNthEntry = isNthEntry(todayEntriesByTerritory, nthEntry);

        return (isNthEntry && areProductsAvailable && areProductsAvailableForToday);
    }

    private boolean isNthEntry(final long entries, final int nthEntry) {
        return (entries % nthEntry) == 0;
    }

    private boolean checkIfProductsAreAvailable(final long entries, final int products) {
        return entries <= products;
    }

}
