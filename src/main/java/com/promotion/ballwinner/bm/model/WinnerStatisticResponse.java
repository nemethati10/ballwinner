package com.promotion.ballwinner.bm.model;

/**
 * Model class representing a projected view of {@link com.promotion.ballwinner.bm.entity.LogEntryBE}
 */
public class WinnerStatisticResponse {

    private String email;
    private String couponCode;
    private String territory;

    public WinnerStatisticResponse(final String email, final String couponCode, final String territory) {
        this.email = email;
        this.couponCode = couponCode;
        this.territory = territory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(final String couponCode) {
        this.couponCode = couponCode;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(final String territory) {
        this.territory = territory;
    }

    @Override
    public String toString() {
        return "WinnerStatisticResponse{" + "email='" + email + '\'' + ", couponCode='" + couponCode + '\''
                + ", territory='" + territory + '\'' + '}';
    }
}
