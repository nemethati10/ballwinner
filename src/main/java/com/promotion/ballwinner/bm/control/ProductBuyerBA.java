package com.promotion.ballwinner.bm.control;

import org.springframework.stereotype.Service;

import com.promotion.ballwinner.bm.control.dao.ManageProductBA;
import com.promotion.ballwinner.bm.entity.ProductBE;

/**
 * Class simulating buying a product.
 */
@Service
public class ProductBuyerBA {

    private ManageProductBA manageProductBA;

    public ProductBuyerBA(final ManageProductBA manageProductBA) {
        this.manageProductBA = manageProductBA;
    }

    /**
     * Simulates buying a product.
     *
     * @param name
     *         name of the product
     *
     * @return the coupon code of the product, null if no product is bought
     */
    public String buyProduct(final String name) {
        final ProductBE product = manageProductBA.findByName(name);

        if (product != null) {
            manageProductBA.remove(product);
            return product.getCouponCode();
        }
        return null;
    }

}
