package com.promotion.ballwinner.bm.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity class representing a Result.
 */
@Entity
@Table(name = "LOG_ENTRY")
public class LogEntryBE extends BaseEntity {

    private LocalDate submittedDate;

    private boolean isWinner;

    private String territory;

    private String couponCode;

    private String email;

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(final LocalDate submittedDate) {
        this.submittedDate = submittedDate;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(final boolean winner) {
        isWinner = winner;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(final String territory) {
        this.territory = territory;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(final String couponCode) {
        this.couponCode = couponCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LogEntryBE{" + "submittedDate=" + submittedDate + ", isWinner=" + isWinner + ", territory='" + territory
                + '\'' + ", couponCode='" + couponCode + '\'' + ", email='" + email + '\'' + '}';
    }
}
