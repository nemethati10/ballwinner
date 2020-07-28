package com.promotion.ballwinner.bm.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity class representing a Customer.
 */

@Entity
@Table(name = "CUSTOMER")
public class CustomerBE extends BaseEntity {

    private String name;

    private String email;

    @Embedded
    private AddressBE address;

    private int age;

    private String couponCode;

    public String getName() {
        return name;
    }

    public void setName(final String customerName) {
        this.name = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public AddressBE getAddress() {
        return address;
    }

    public void setAddress(final AddressBE address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(final String couponCode) {
        this.couponCode = couponCode;
    }
}
