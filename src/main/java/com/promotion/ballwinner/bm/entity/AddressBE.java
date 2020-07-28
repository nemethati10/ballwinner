package com.promotion.ballwinner.bm.entity;

import javax.persistence.Embeddable;

/**
 * Entity class representing an Address.
 */
@Embeddable
public class AddressBE {

    private String country;
    private String street;
    private int apartmentNumber;

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(final int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
