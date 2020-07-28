package com.promotion.ballwinner.bm.util.validator;

/**
 * Validator for Coupons.
 */
public final class CouponValidator {

    private static final int COUPON_LENGTH = 10;

    private CouponValidator() {
    }

    /**
     * Validates a couponCode
     *
     * @param code
     * @param isRedeemed
     *
     * @return
     */
    public static boolean validateCoupon(final String code, final boolean isRedeemed) {
        if (code == null || code.length() != COUPON_LENGTH || isRedeemed) {
            return false;
        }
        return true;
    }
}
