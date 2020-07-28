package com.promotion.ballwinner.bm.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Helper class for coupons.
 */
public final class CouponUtil {

    private static final int COUPON_LENGTH = 10;

    private CouponUtil() {
    }

    /**
     * Generates a coupon code.
     *
     * @return
     */
    public static String generateCouponCode() {
        return RandomStringUtils.randomAlphanumeric(COUPON_LENGTH);
    }

}
