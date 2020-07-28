package com.promotion.ballwinner.bm.boundary;

import org.springframework.stereotype.Service;

import com.promotion.ballwinner.bm.control.CouponRedeemerBA;
import com.promotion.ballwinner.bm.control.ProductBuyerBA;
import com.promotion.ballwinner.bm.control.dao.ManageCustomerBA;
import com.promotion.ballwinner.bm.entity.CustomerBE;
import com.promotion.ballwinner.bm.entity.RedeemResult;

/**
 * Boundary class simulating the process of  a coupon submission.
 */
@Service
public class CouponRedeemerSimulatorBF {

    private CouponRedeemerBA couponRedeemerBA;
    private ProductBuyerBA productBuyerBA;
    private ManageCustomerBA manageCustomerBA;

    public CouponRedeemerSimulatorBF(final CouponRedeemerBA couponRedeemerBA, final ProductBuyerBA productBuyerBA,
            final ManageCustomerBA manageCustomerBA) {
        this.couponRedeemerBA = couponRedeemerBA;
        this.productBuyerBA = productBuyerBA;
        this.manageCustomerBA = manageCustomerBA;
    }

    /**
     * Simulates a coupon submit, return the result of the redeeming process.
     *
     * @param customer
     * @param territoryName
     * @param product
     *
     * @return
     */
    // TODO ANE: write tests
    public RedeemResult redeemCouponSimulation(final CustomerBE customer, final String territoryName,
            final String product) {

        // buy product
        final String coupon = productBuyerBA.buyProduct(product);
        // register
        final CustomerBE registeredCustomer = manageCustomerBA.save(customer);
        registeredCustomer.setCouponCode(coupon);
        // submit coupon
        return couponRedeemerBA.redeemCoupon(registeredCustomer, territoryName);
    }
}
