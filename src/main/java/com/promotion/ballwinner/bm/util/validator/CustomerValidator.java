package com.promotion.ballwinner.bm.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.promotion.ballwinner.bm.entity.CustomerBE;

/**
 * Validator class for {@link CustomerBE}
 */
public final class CustomerValidator {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final int MIN_AGE = 13;

    private CustomerValidator() {
    }

    /**
     * Validates a given customer. (email, age)
     *
     * @param customer
     *
     * @return
     */
    public static boolean isCustomerValid(final CustomerBE customer) {
        if (customer == null) {
            return false;
        }

        return isEmailValid(customer.getEmail()) && isAgeValid(customer.getAge());
    }

    private static boolean isEmailValid(final String email) {
        final Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private static boolean isAgeValid(final int age) {
        return age >= MIN_AGE;
    }
}
