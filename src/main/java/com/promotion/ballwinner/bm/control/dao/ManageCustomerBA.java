package com.promotion.ballwinner.bm.control.dao;

import org.springframework.stereotype.Service;

import com.promotion.ballwinner.bm.control.repository.CustomerRepository;
import com.promotion.ballwinner.bm.entity.AddressBE;
import com.promotion.ballwinner.bm.entity.CustomerBE;

/**
 * Business activity for managing {@link CustomerBE}
 */
@Service
public class ManageCustomerBA {

    private CustomerRepository customerRepository;

    public ManageCustomerBA(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerBE create(final String email, final String name, final int age, final String couponCode,
            final AddressBE address) {

        final CustomerBE customer = new CustomerBE();
        customer.setEmail(email);
        customer.setName(name);
        customer.setAge(age);
        customer.setCouponCode(couponCode);
        customer.setAddress(address);

        return save(customer);
    }

    public CustomerBE save(final CustomerBE customer) {
        return customerRepository.save(customer);
    }
}
