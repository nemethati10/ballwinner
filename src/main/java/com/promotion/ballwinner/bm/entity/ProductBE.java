package com.promotion.ballwinner.bm.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity representing a Product class.
 */
@Entity
@Table(name = "PRODUCT")
public class ProductBE extends BaseEntity {

    private String productName;

    private String couponCode;

    @ManyToOne
    @JoinColumn
    private CompanyBE company;

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(final String couponCode) {
        this.couponCode = couponCode;
    }

    public CompanyBE getCompany() {
        return company;
    }

    public void setCompany(final CompanyBE company) {
        this.company = company;
    }
}
